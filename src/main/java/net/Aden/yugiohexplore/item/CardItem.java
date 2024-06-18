package net.Aden.yugiohexplore.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;


public class CardItem extends Item {
    private final String cardName;
    private final String imagePath;


    //public CardItem(String cardName, String imagePath, Properties properties) {
    //   super(properties.tab(CreativeModeTabs.TAB_MISC));
    //    this.cardName = cardName;
    //    this.imagePath = imagePath;
    }
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Add to ingredients tab
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ITEM);
            event.accept(BLOCK); // Takes in an ItemLike, assumes block has registered item
        }
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