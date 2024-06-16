package net.Aden.yugiohexplore.item;

import net.minecraft.ChatFormatting;

public enum Rarity {
    COMMON("Common", ChatFormatting.WHITE),
    RARE("Rare", ChatFormatting.YELLOW),
    ULTRA_RARE("Ultra Rare", ChatFormatting.GOLD);

    private final String name;
    private final ChatFormatting color;

    Rarity(String name, ChatFormatting color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getColor() {
        return color;
    }
}