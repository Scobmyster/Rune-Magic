package scobmyster.runemagic.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scobmyster.runemagic.crafting.NaturalTableCraftingManager;
import scobmyster.runemagic.init.ModBlocks;
import scobmyster.runemagic.init.ModItems;

public class RecipeHandler
{

    public static void registerCraftingRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModItems.lightcore), new Object[]{"GTG", "TGT", "GTG", 'G', Item.getItemFromBlock(Blocks.GLASS), 'T', Item.getItemFromBlock(Blocks.TORCH)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.lightbox), new Object[]{"WGW", "GCG", "WGW", 'W', Item.getItemFromBlock(Blocks.PLANKS), 'G', Item.getItemFromBlock(Blocks.GLASS), 'C', ModItems.lightcore});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.naturaltable), new Object[] {"DSD", "TCT", "PSP", 'D', Item.getItemFromBlock(Blocks.YELLOW_FLOWER), 'S', Item.getItemFromBlock(Blocks.SAPLING), 'T', Item.getItemFromBlock(Blocks.CRAFTING_TABLE), 'C', ModItems.natureCore, 'P', Item.getItemFromBlock(Blocks.RED_FLOWER)});
    }

    public static void registerNaturalTableCraftingRecipes()
    {
        NaturalTableCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.naturesGift), new Object[] {"SSS", "S S", " N ", 'S', Items.STRING, 'N', ModItems.natureCore});
    }

}
