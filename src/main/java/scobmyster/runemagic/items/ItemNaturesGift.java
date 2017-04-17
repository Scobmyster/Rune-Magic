package scobmyster.runemagic.items;

import net.minecraft.client.gui.ChatLine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import scobmyster.runemagic.Reference;
import scobmyster.runemagic.util.Utils;

public class ItemNaturesGift extends Item
{

    private PotionEffect[] effects;

    public ItemNaturesGift(String unlocalizedName, PotionEffect...potionEffects)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.effects = potionEffects;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand)
    {
        Utils.getLogger().info("Right click on natures gift charm");
        addPotionEffects(player);
        player.getHeldItem(hand).stackSize--;
        return super.onItemRightClick(itemStackIn, worldIn, player, hand);
    }

    public void addPotionEffects(EntityPlayer player)
    {
        for(PotionEffect effect : effects)
        {
            Utils.getLogger().info("Adding potion effects");
            player.addPotionEffect(effect);
        }
    }

}
