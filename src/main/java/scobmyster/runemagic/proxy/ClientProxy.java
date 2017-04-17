package scobmyster.runemagic.proxy;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import scobmyster.runemagic.RuneMagic;
import scobmyster.runemagic.client.gui.GUIHandler;
import scobmyster.runemagic.init.ModBlocks;
import scobmyster.runemagic.init.ModItems;

public class ClientProxy extends CommonProxy
{


    @Override
    public void init()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(RuneMagic.instance, new GUIHandler());
    }

    @Override
    public void registerRenders()
    {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
    }
}
