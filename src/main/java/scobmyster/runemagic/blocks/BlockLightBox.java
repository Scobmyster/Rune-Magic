package scobmyster.runemagic.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import scobmyster.runemagic.Reference;

import javax.annotation.Nullable;
import java.util.Random;


public class BlockLightBox extends Block
{

    public BlockLightBox(String unlocalizedName)
    {
        super(Material.GLASS);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setHardness(1.0f);
        this.setResistance(1.0f);
        this.setLightLevel(6.0f);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
}
