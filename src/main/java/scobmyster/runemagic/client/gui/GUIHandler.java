package scobmyster.runemagic.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import scobmyster.runemagic.container.ContainerNaturalTable;

public class GUIHandler implements IGuiHandler
{

    public static final int NATURAL_TABLE = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == NATURAL_TABLE)
        {
            return new ContainerNaturalTable(player.inventory, world, new BlockPos(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == NATURAL_TABLE)
        {
            return new GUINaturalTable(player.inventory, world, x, y, z);
        }
        return null;
    }
}
