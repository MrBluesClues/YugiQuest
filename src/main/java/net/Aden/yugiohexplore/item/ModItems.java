package net.Aden.yugiohexplore.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.Aden.yugiohexplore.YugiohExplore;
import net.Aden.yugiohexplore.util.CardDataLoader;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YugiohExplore.MOD_ID);
    public static final List<Map<String, Object>> CARD_DATA = CardDataLoader.loadCardData();

    static {
        registerItems();
    }

    private static void registerItems() {
        if (CARD_DATA != null) {
            for (Map<String, Object> card : CARD_DATA) {
                int id = (int) card.get("id");
                String name = (String) card.get("name");
                String imagePath = (String) card.get("image");

                ITEMS.register("card_" + id, () -> new CardItem(name, imagePath, new Item.Properties().tab(CreativeModeTabs.TAB_MISC)));
            }
        }
    }

    public static Item getCardItem(int id, Rarity rarity) {
        Optional<RegistryObject<Item>> cardItem = ITEMS.getEntries().stream()
                .filter(item -> item.getId().getPath().equals("card_" + id))
                .findFirst();
        return cardItem.orElseThrow(() -> new IllegalArgumentException("Card item not found for id: " + id)).get();
    }
}