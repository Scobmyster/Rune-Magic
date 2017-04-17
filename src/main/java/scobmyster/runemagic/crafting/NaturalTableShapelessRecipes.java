package scobmyster.runemagic.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NaturalTableShapelessRecipes implements IRecipe
{

    private final ItemStack recipeOutput;

    public final List recipeItems;

    public NaturalTableShapelessRecipes(ItemStack stack, List recipeItems)
    {
        this.recipeOutput = stack;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {
        ArrayList arraylist = new ArrayList(this.recipeItems);

        for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 5; ++j)
            {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (itemstack != null)
                {
                    boolean flag = false;
                    Iterator iterator = arraylist.iterator();

                    while (iterator.hasNext())
                    {
                        ItemStack itemstack1 = (ItemStack)iterator.next();

                        if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767 || itemstack.getItemDamage() == itemstack1.getItemDamage()))
                        {
                            flag = true;
                            arraylist.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag)
                    {
                        return false;
                    }
                }
            }
        }

        return arraylist.isEmpty();
    }


    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return this.recipeOutput.copy();
    }

    @Override
    public int getRecipeSize()
    {
        return this.recipeItems.size();
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
