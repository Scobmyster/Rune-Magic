package scobmyster.runemagic.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import scobmyster.runemagic.init.ModItems;
import scobmyster.runemagic.items.ItemNatureStaff;
import scobmyster.runemagic.util.Utils;

public class SpellHandler
{

    private static int spellID;
    private static String[] spellTome = new String[]{"LightningSpell", "HealSpell"};
    private static int[] spellCosts = new int[]{15000, 10000};

    public static int getSpellID() {
        return spellID;
    }

    public void setSpellID(int newspellID)
    {
        if(newspellID <= 1)
        {
            this.spellID = newspellID;
        }
        else
        {
            this.spellID = 0;
        }
    }

    public static void handleSpell(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand, ItemNatureStaff natureStaff)
    {
        Utils.getLogger().info("Spell ID is: " + spellID);
        if(spellID == 0)
        {
            LightningSpell(itemStack, world, player, hand);
            natureStaff.addLivingForce(new ItemStack(natureStaff), spellCosts[spellID]);
        }
        if(spellID == 1)
        {
            HealSpell(itemStack, world, player, hand);
            natureStaff.addLivingForce(new ItemStack(natureStaff), spellCosts[spellID]);
        }
    }

    public static void displaySelectedSpell(EntityPlayer player)
    {
        if(!player.worldObj.isRemote)
        {
            player.addChatMessage(new TextComponentTranslation("spell." + spellTome[spellID]));
        }
    }


    //Spells
    public static void LightningSpell(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        RayTraceResult result = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(15, 1.0f);

        int x = result.getBlockPos().getX();
        int y = result.getBlockPos().getY();
        int z = result.getBlockPos().getZ();

        if(!world.isAirBlock(result.getBlockPos()))
        {
            world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z, false));
        }
    }

    public static void HealSpell(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        if(player.getHealth() != player.getMaxHealth())
        {
            player.heal(2.0f);
        }
    }

}
