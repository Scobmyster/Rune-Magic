package scobmyster.runemagic.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scobmyster.runemagic.crafting.NaturalTableCraftingManager;
import scobmyster.runemagic.init.ModBlocks;
import scobmyster.runemagic.util.Utils;

import javax.annotation.Nullable;

public class ContainerNaturalTable extends Container
{
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World world;
    private int posX;
    private int posY;
    private int posZ;
    private final BlockPos pos;

    public ContainerNaturalTable(InventoryPlayer playerinv, World world, BlockPos pos)
    {
        this.pos = pos;
        this.world = world;
        craftMatrix = new InventoryCrafting(this, 3, 3);
        craftResult = new InventoryCraftResult();


        this.addSlotToContainer(new SlotCrafting(playerinv.player, craftMatrix, craftResult, 0, 120, 31));
        //Crafting grid
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 3; k++)
            {
                this.addSlotToContainer(new Slot(craftMatrix, k + i * 3, 30 + k * 18, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
                this.addSlotToContainer(new Slot(playerinv, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(playerinv, i, 8 + i * 18, 142));
        }

        onCraftMatrixChanged(craftMatrix);

    }


    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
       craftResult.setInventorySlotContents(0, NaturalTableCraftingManager.getInstance().findMatchingRecipe(craftMatrix, world));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return !player.isSpectator();
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

            if (!this.world.isRemote)
            {
                for (int i = 0; i < 9; i++)
                {
                    ItemStack itemStack = this.craftMatrix.removeStackFromSlot(i);

                    if (itemStack != null)
                    {
                        player.dropItem(itemStack, false);
                    }
                }
            }
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}

