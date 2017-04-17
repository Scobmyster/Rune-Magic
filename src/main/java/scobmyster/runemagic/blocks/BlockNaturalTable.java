package scobmyster.runemagic.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scobmyster.runemagic.Reference;
import scobmyster.runemagic.RuneMagic;
import scobmyster.runemagic.client.gui.GUIHandler;
import scobmyster.runemagic.util.Utils;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockNaturalTable extends Block
{


    public BlockNaturalTable(String unlocalizedName)
    {
        super(Material.WOOD);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setHardness(2.0f);
        this.setResistance(2.0f);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        Utils.getLogger().info("Right click detected");
       if(!world.isRemote)
       {
           Utils.getLogger().info("About to open");
           try {
               player.openGui(RuneMagic.instance, GUIHandler.NATURAL_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
           }
           catch(Exception e)
           {
              Utils.getLogger().info(e.getStackTrace().toString());
           }
       }
        return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
    }
}
