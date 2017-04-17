package scobmyster.runemagic.crafting;

import net.minecraft.item.crafting.IRecipe;

import java.util.Comparator;

public class NaturalTableRecipeSorter implements Comparator
{

    final NaturalTableCraftingManager naturalTable;

    public NaturalTableRecipeSorter(NaturalTableCraftingManager naturalTableCraftingManager)
    {
        this.naturalTable = naturalTableCraftingManager;
    }

    public int compareRecipes(IRecipe iRecipe, IRecipe iRecipe2)
    {
        return iRecipe instanceof NaturalTableShapelessRecipes && iRecipe2 instanceof NaturalTableShapedRecipes ? 1: (iRecipe2 instanceof NaturalTableShapelessRecipes && iRecipe instanceof NaturalTableShapedRecipes ? -1: (iRecipe2.getRecipeSize() < iRecipe.getRecipeSize() ? 1 : 0));
    }

    @Override
    public int compare(Object o1, Object o2)
    {
        return this.compareRecipes((IRecipe) o1, (IRecipe) o2);
    }
}
