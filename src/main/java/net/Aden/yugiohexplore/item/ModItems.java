package net.Aden.yugiohexplore.item;

import io.netty.handler.ipfilter.IpSubnetFilter;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.Aden.yugiohexplore.YugiohExplore;
import net.Aden.yugiohexplore.util.CardDataLoader;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static net.minecraft.commands.arguments.ResourceLocationArgument.id;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YugiohExplore.MOD_ID);
    public static final List<Map<String, Object>> CARD_DATA = CardDataLoader.loadCardData();

    private static void register(IEventBus eventBus) {
        if (CARD_DATA != null) {
            for (Map<String, Object> card : CARD_DATA) {
                int id;
                id = (int) card.get("id");
                String name = (String) card.get("name");
                String imagePath = (String) card.get("image");

                final RegistryObject<Item> CARDS = ITEMS.register("card_" + id, () ->
                new Item(new Item.Properties()));
            }
        }
    }
}