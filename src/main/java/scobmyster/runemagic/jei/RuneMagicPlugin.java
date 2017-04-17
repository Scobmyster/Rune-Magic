package scobmyster.runemagic.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import scobmyster.runemagic.crafting.NaturalTableCraftingManager;

@JEIPlugin
public class RuneMagicPlugin implements IModPlugin
{

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
    {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry)
    {

    }

    @Override
    public void register(IModRegistry registry)
    {
        registry.addRecipes(NaturalTableCraftingManager.getRecipes());
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {

    }
}
