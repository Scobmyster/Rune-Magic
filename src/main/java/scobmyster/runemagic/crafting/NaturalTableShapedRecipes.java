package scobmyster.runemagic.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;

public class NaturalTableShapedRecipes implements IRecipe
{

    public final int recipeWidth;

    public final int recipeHeight;

    public final ItemStack[] recipeItems;

    private ItemStack recipeOutput;
    private boolean someBool;


    public NaturalTableShapedRecipes(int width, int height, ItemStack[] recipeItems, ItemStack output)
    {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = recipeItems;
        this.recipeOutput = output;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {
        for(int i = 0; i <= 5 - this.recipeWidth; i++)
        {
            for(int j = 0; j <= 5 - this.recipeHeight; j++)
            {
                if(this.checkMatch(inv, i, j, true))
                {
                    return true;
                }

                if(this.checkMatch(inv, i, j, false))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkMatch(InventoryCrafting inv, int i, int j, boolean checks)
    {
        for(int k = 0; k < 5; k++)
        {
            for(int l = 0; l < 5; l++)
            {
                int x = k - i;
                int y = l - j;
                ItemStack itemStack = null;

                if(x >= 0 && y >= 0 && x < this.recipeWidth && y < this.recipeHeight)
                {
                    if(checks)
                    {
                        itemStack = this.recipeItems[this.recipeWidth - x - 1 + y * this.recipeWidth];
                    }
                    else
                    {
                        itemStack = this.recipeItems[x + y * this.recipeWidth];
                    }
                }

                ItemStack stack = inv.getStackInRowAndColumn(k, l);

                if(stack != null || itemStack != null)
                {
                    if(stack == null && itemStack != null || stack != null && itemStack == null)
                    {
                        return false;
                    }

                    if(itemStack.getItem() != stack.getItem())
                    {
                        return false;
                    }

                    if(itemStack.getItemDamage() != 32767 && itemStack.getItemDamage() != stack.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack stack = this.getRecipeOutput().copy();

        if(this.someBool)
        {
            for(int i = 0; i < inv.getSizeInventory(); i++)
            {
                ItemStack itemStack = inv.getStackInSlot(i);

                if(itemStack != null && itemStack.hasTagCompound())
                {
                    stack.setTagCompound(itemStack.getTagCompound().copy());

                }
            }
        }

        return stack;
    }

    @Override
    public int getRecipeSize()
    {
        return this.recipeWidth * this.recipeHeight;
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv)
    {
        ItemStack[] stack = new ItemStack[inv.getSizeInventory()];

        for(int i = 0; i < stack.length; i++)
        {
            ItemStack itemStack = inv.getStackInSlot(i);
            stack[i] = ForgeHooks.getContainerItem(itemStack);
        }

        return stack;
    }
}
