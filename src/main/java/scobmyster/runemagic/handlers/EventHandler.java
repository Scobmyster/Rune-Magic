package scobmyster.runemagic.handlers;

import net.minecraftforge.common.MinecraftForge;
import scobmyster.runemagic.events.DropEvents;

public class EventHandler
{

    public void registerEvents()
    {
        MinecraftForge.EVENT_BUS.register(new DropEvents());
    }

}
