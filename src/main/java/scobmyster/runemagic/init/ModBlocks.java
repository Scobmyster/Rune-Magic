package scobmyster.runemagic.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scobmyster.runemagic.Reference;
import scobmyster.runemagic.blocks.BlockLightBox;
import scobmyster.runemagic.blocks.BlockNaturalTable;
import scobmyster.runemagic.util.Utils;

public class ModBlocks
{

    public static Block lightbox;
    public static Block naturaltable;

    public static void init()
    {
        lightbox = new BlockLightBox("light_box");
        naturaltable = new BlockNaturalTable("natural_table");
    }

    public static void register()
    {
        registerBlock(lightbox);
        registerBlock(naturaltable);
    }

    public static void registerRenders()
    {
        registerRender(lightbox);
        registerRender(naturaltable);
    }

    public static void registerBlock(Block block)
    {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        Utils.getLogger().info("Registering block: " + block.getUnlocalizedName().substring(5));
    }

    public static void registerRender(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Registering render for: " + block.getUnlocalizedName().substring(5));
    }

}
