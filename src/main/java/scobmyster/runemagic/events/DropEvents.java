package scobmyster.runemagic.events;


import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scobmyster.runemagic.init.ModItems;

public class DropEvents
{

    @SubscribeEvent
    public void playerHarvestEvent(HarvestDropsEvent event)
    {
        if(event.getHarvester() != null)
        {
            if(event.getState().getBlock() == Blocks.TALLGRASS)
            {
                event.setDropChance((float) 0.1);
                event.getDrops().add(new ItemStack(ModItems.natureCore));
            }
        }
    }

}
