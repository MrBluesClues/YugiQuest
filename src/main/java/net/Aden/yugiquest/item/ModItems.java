package net.Aden.yugiquest.item;

import net.Aden.yugiquest.YugiQuest;
import net.Aden.yugiquest.util.CardDataLoader;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModItems {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YugiQuest.MOD_ID);
    public static final List<Map<String, Object>> CARD_DATA = CardDataLoader.loadCardData();
    public static final List<RegistryObject<Item>> REGISTERED_CARDS = new ArrayList<>();

    static {
        if (CARD_DATA != null) {
            LOGGER.info("Number of cards loaded: " + CARD_DATA.size());
            for (Map<String, Object> card : CARD_DATA) {
                int id = ((Number) card.get("id")).intValue();
                String name = (String) card.get("name");
                String imagePath = (String) card.get("image");

                LOGGER.info("Registering card: ID = " + id + ", Name = " + name + ", Image Path = " + imagePath);

                final RegistryObject<Item> cardItem = ITEMS.register(String.valueOf(id), () ->
                        new Item(new Item.Properties()));
                REGISTERED_CARDS.add(cardItem);
            }
        } else {
            LOGGER.error("Failed to load card data. CARD_DATA is null.");
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static List<RegistryObject<Item>> getRegisteredCards() {
        return REGISTERED_CARDS;
    }
}
