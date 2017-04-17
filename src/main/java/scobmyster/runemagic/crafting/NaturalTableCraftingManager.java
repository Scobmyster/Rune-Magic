package scobmyster.runemagic.crafting;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NaturalTableCraftingManager {

    private static List recipes = new ArrayList();

    private static final NaturalTableCraftingManager instance = new NaturalTableCraftingManager();

    public static final NaturalTableCraftingManager getInstance()
    {
        return instance;
    }

    private NaturalTableCraftingManager()
    {
        recipes = new ArrayList();

        Collections.sort(this.recipes, new NaturalTableRecipeSorter(this));

    }

    public static List getRecipes() {
        return recipes;
    }


    public NaturalTableShapedRecipes addRecipe(ItemStack stack, Object...recipeContent)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if(recipeContent[i] instanceof  String[])
        {
            String[] currentSlots = (String[])((String[])recipeContent[i++]);

            for(int l = 0; l < currentSlots.length; l++)
            {
                String row = currentSlots[l];
                k++;
                j = row.length();
                s = s + row;
            }
        }
        else
        {
            while(recipeContent[i] instanceof String)
            {
                String currentSlots2 = (String)recipeContent[i++];
                k++;
                j = currentSlots2.length();
                s = s + currentSlots2;
            }
        }

        HashMap hashMap;

        for(hashMap = new HashMap(); i < recipeContent.length; i += 2)
        {
            Character character = (Character) recipeContent[i];
            ItemStack itemStack = null;
            if(recipeContent[i + 1] instanceof Item)
            {
                itemStack = new ItemStack((Item) recipeContent[i + 1]);
            }
            else if(recipeContent[i + 1] instanceof Block)
            {
                itemStack = new ItemStack((Block) recipeContent[i + 1], 1, 32767);
            }
            else if(recipeContent[i + 1] instanceof ItemStack)
            {
                itemStack = (ItemStack) recipeContent[i + 1];
            }

            hashMap.put(character, itemStack);
        }

        ItemStack[] items = new ItemStack[j * k];

        for(int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if(hashMap.containsKey(Character.valueOf(c0)))
            {
                items[i1] = ((ItemStack) hashMap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                items[i1] = null;
            }
        }

        NaturalTableShapedRecipes shapedRecipes = new NaturalTableShapedRecipes(j, k, items, stack);
        this.recipes.add(shapedRecipes);
        return shapedRecipes;
    }

    public void addShapelessRecipe(ItemStack stack, Object ... recipeContent)
    {
        ArrayList arraylist = new ArrayList();
        Object[] aobject = recipeContent;
        int i = recipeContent.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.recipes.add(new ShapelessRecipes(stack, arraylist));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting inv, World world)
    {
        int i = 0;
        ItemStack itemStack = null;
        ItemStack itemStack1 = null;
        int j;

        for(j = 0; j < inv.getSizeInventory(); j++)
        {
            ItemStack itemStack2 = inv.getStackInSlot(j);

            if(itemStack2 != null)
            {
                if(i == 0)
                {
                    itemStack = itemStack2;
                }
                if(i == 1)
                {
                    itemStack1 = itemStack2;
                }

                i++;
            }
        }

        if(i == 2 && itemStack.getItem() == itemStack1.getItem() && itemStack.stackSize == 1 && itemStack1.stackSize == 1 && itemStack.getItem().isRepairable())
        {
            Item item = itemStack.getItem();
            int j1 = item.getMaxDamage() - itemStack.getItemDamage();
            int k = item.getMaxDamage() - itemStack1.getItemDamage();
            int l = j1 + k + item.getMaxDamage() * 5 / 100;
            int i1 = item.getMaxDamage() - 1;

            if(i1 < 0)
            {
                i1 = 0;
            }

            return new ItemStack(itemStack.getItem(), 1, i1);
        }
        else
        {
            for(j = 0; j < this.recipes.size(); j++)
            {
                IRecipe iRecipe = (IRecipe)this.recipes.get(j);

                if(iRecipe.matches(inv, world))
                {
                    return iRecipe.getCraftingResult(inv);
                }
            }
            return null;
        }
    }

    public List getRecipeList()
    {
        return this.recipes;
    }

}
