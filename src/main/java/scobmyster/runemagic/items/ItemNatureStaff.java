package scobmyster.runemagic.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import scobmyster.runemagic.Reference;
import scobmyster.runemagic.api.livingforce.ICreativeLivingForceProvider;
import scobmyster.runemagic.api.livingforce.ILivingForceItem;
import scobmyster.runemagic.api.livingforce.ILivingForceToolTipDisplay;
import scobmyster.runemagic.handlers.SpellHandler;
import scobmyster.runemagic.helpers.ItemNBTHelper;
import scobmyster.runemagic.init.ModItems;
import scobmyster.runemagic.util.Utils;

import java.util.List;

public class ItemNatureStaff extends Item implements ILivingForceItem, ICreativeLivingForceProvider, ILivingForceToolTipDisplay
{

    public static final int MAX_LIVING_FORCE = 50000;

    private static final String TAG_LIVING_FORCE = "living_force";
    private static final String TAG_CREATIVE = "creative";

    private SpellHandler spellHandler = new SpellHandler();

    public ItemNatureStaff(String unlocalizedName)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                if(!player.worldObj.isRemote) {
                    int newSpellID;
                    newSpellID = spellHandler.getSpellID() + 1;
                    Utils.getLogger().info("New Spell ID: " + newSpellID);
                    spellHandler.setSpellID(newSpellID);
                    spellHandler.displaySelectedSpell(player);
                }
            } else {
                spellHandler.handleSpell(itemStack, world, player, hand, this);

        }
        return super.onItemRightClick(itemStack, world, player, hand);
    }


    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> stacks)
    {
        //Empty Nature Staff
        stacks.add(new ItemStack(item));

        //Full Nature Staff
        ItemStack fullPower = new ItemStack(item);
        setLivingForce(fullPower, MAX_LIVING_FORCE);
        stacks.add(fullPower);

        //Creative Nature Staff
        ItemStack creative = new ItemStack(item);
        setLivingForce(creative, MAX_LIVING_FORCE);
        setStackCreative(creative);
        stacks.add(creative);
    }

    @Override
    public int getDamage(ItemStack stack)
    {
        if(super.getDamage(stack) != 0)
        {
            super.setDamage(stack, 0);
        }
        return 0;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        if(isStackCreative(stack))
        {
            tooltip.add(I18n.format("runemagicmisc.creative"));
        }
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        return Integer.MAX_VALUE;
    }

    public static void setLivingForce(ItemStack stack, int livingForce)
    {
        ItemNBTHelper.setInt(stack, TAG_LIVING_FORCE, livingForce);
    }

    public static void setStackCreative(ItemStack stack)
    {
        ItemNBTHelper.setBoolean(stack, TAG_CREATIVE, true);
    }

    public static boolean isStackCreative(ItemStack stack)
    {
        return ItemNBTHelper.getBoolean(stack, TAG_CREATIVE, false);
    }

    @Override
    public int getCurrentLivingForce(ItemStack stack)
    {
        return ItemNBTHelper.getInt(stack, TAG_LIVING_FORCE, 0);
    }

    @Override
    public int getMaxLivingForce(ItemStack stack)
    {
        return isStackCreative(stack) ? MAX_LIVING_FORCE + 1000 : MAX_LIVING_FORCE;
    }

    @Override
    public void addLivingForce(ItemStack stack, int livingForce)
    {
        if(!isStackCreative(stack))
            setLivingForce(stack, Math.min(getCurrentLivingForce(stack) + livingForce, MAX_LIVING_FORCE));
    }

    @Override
    public boolean isCreative(ItemStack stack)
    {
        return isStackCreative(stack);
    }

    @Override
    public float getLivingForceFractionForDisplay(ItemStack stack)
    {
        return (float) getCurrentLivingForce(stack) / (float) getMaxLivingForce(stack);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return !isStackCreative(stack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return 1.0 - getLivingForceFractionForDisplay(stack);
    }





}
