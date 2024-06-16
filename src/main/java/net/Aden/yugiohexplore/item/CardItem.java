package net.Aden.yugiohexplore.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;


public class CardItem extends Item {
    private final String cardName;
    private final String imagePath;

    public CardItem(String cardName, String imagePath, Properties properties) {
        super(properties.tab(CreativeModeTabs.TAB_MISC));
        this.cardName = cardName;
        this.imagePath = imagePath;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        // Determine rarity based on your logic
        return super.getRarity(stack);
    }

    public String getCardName() {
        return cardName;
    }

    public String getImagePath() {
        return imagePath;
    }
}