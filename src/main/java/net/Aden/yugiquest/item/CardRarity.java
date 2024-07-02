package net.Aden.yugiquest.item;

public enum CardRarity {
    COMMON("Common", 0xFFFFFF), // Green color for common
    RARE("Rare", 0x72FF66); // Gold color for rare

    private final String displayName;
    private final int color;

    CardRarity(String displayName, int color) {
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getColor() {
        return color;
    }
}
