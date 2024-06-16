package net.Aden.yugiohexplore.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PackItem extends Item {
    private final List<Map<String, Object>> cards;
    private final Random random = new Random();

    public PackItem(List<Map<String, Object>> cards) {
        super(new Item.Properties().tab(CreativeModeTabs.TAB_MISC));
        this.cards = cards;
    }

    public void openPack(Player player) {
        for (int i = 0; i < 5; i++) { // Example: 5 cards per pack
            Map<String, Object> card = cards.get(random.nextInt(cards.size()));
            ItemStack cardStack = new ItemStack(ModItems.getCardItem((int) card.get("id"), getRandomRarity()));
            player.getInventory().add(cardStack);
        }
    }

    private Rarity getRandomRarity() {
        // Implement your rarity logic here
        return Rarity.COMMON; // Placeholder
    }
}