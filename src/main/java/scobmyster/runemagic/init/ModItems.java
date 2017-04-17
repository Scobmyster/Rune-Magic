package scobmyster.runemagic.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scobmyster.runemagic.Reference;
import scobmyster.runemagic.items.ItemLightCore;
import scobmyster.runemagic.items.ItemNatureCore;
import scobmyster.runemagic.items.ItemNatureStaff;
import scobmyster.runemagic.items.ItemNaturesGift;
import scobmyster.runemagic.util.Utils;

public class ModItems
{

    public static Item lightcore;
    public static Item naturesGift;
    public static Item natureCore;
    public static Item natureStaff;

    public static void init()
    {
        lightcore = new ItemLightCore("light_core");
        naturesGift = new ItemNaturesGift("natures_gift", new PotionEffect(Potion.getPotionById(1), 600,2), new PotionEffect(Potion.getPotionById(3), 600, 2));
        natureCore = new ItemNatureCore("nature_core");
        natureStaff = new ItemNatureStaff("nature_staff");
    }

    public static void register()
    {
        registerItem(lightcore);
        registerItem(naturesGift);
        registerItem(natureCore);
        registerItem(natureStaff);
    }

    public static void registerRenders()
    {
        registerRender(lightcore);
        registerRender(naturesGift);
        registerRender(natureCore);
        registerRender(natureStaff);
    }

    public static void registerItem(Item item)
    {
        GameRegistry.register(item);
        Utils.getLogger().info("Registering item: " + item.getUnlocalizedName().substring(5));
    }

    public static void registerRender(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Registering render: " + item.getUnlocalizedName().substring(5));
    }

}
