package net.Aden.yugiquest.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CardItem extends Item {

    public CardItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        if (stack.hasTag() && stack.getTag().contains("CardRarity")) {
            String rarityName = stack.getTag().getString("CardRarity");
            CardRarity rarity = CardRarity.valueOf(rarityName);
            tooltip.add(Component.literal(rarity.getDisplayName()).withStyle(style -> style.withColor(rarity.getColor())));
        }
    }
}
