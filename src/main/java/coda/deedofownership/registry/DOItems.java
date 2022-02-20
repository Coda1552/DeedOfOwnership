package coda.deedofownership.registry;

import coda.deedofownership.DeedOfOwnership;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DOItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeedOfOwnership.MOD_ID);

    public static final RegistryObject<Item> DEED_OF_OWNERSHIP = ITEMS.register("deed_of_ownership", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).tab(CreativeModeTab.TAB_MISC)));
}
