package scobmyster.runemagic;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scobmyster.runemagic.handlers.RecipeHandler;
import scobmyster.runemagic.init.ModBlocks;
import scobmyster.runemagic.init.ModItems;
import scobmyster.runemagic.jei.RuneMagicPlugin;
import scobmyster.runemagic.proxy.CommonProxy;
import scobmyster.runemagic.util.Utils;

@Mod( modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class RuneMagic
{

    scobmyster.runemagic.handlers.EventHandler eventHandler = new scobmyster.runemagic.handlers.EventHandler();

    @Mod.Instance(Reference.MODID)
    public static RuneMagic instance;

    @SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Utils.getLogger().info("PreInitialising");

        ModBlocks.init();
        ModBlocks.register();
        ModItems.init();
        ModItems.register();

        proxy.registerRenders();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Utils.getLogger().info("Initialising");

        proxy.init();

        eventHandler.registerEvents();

        RecipeHandler.registerCraftingRecipes();
        RecipeHandler.registerNaturalTableCraftingRecipes();


    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Utils.getLogger().info("PostInitialising");
    }

}
