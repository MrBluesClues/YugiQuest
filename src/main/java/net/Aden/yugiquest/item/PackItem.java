package net.Aden.yugiquest.item;

import com.google.gson.Gson;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PackItem extends Item {

    private static final String PACK_DATA_PATH = "/assets/yugiquest/data/";
    private static final Gson GSON = new Gson();

    private final PackType packType;
    private final List<PackEntry> packEntries;

    public PackItem(PackType packType, Properties properties) {
        super(properties);
        this.packType = packType;
        this.packEntries = loadPackEntries(packType.getFileName());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            ItemStack pack = player.getItemInHand(hand);
            Random random = new Random();
            List<ItemStack> cardStacks = new ArrayList<>();

            int numberOfCards = 9; // Default for common packs, adjust as needed for each pack type

            for (int i = 0; i < numberOfCards; i++) {
                PackEntry selectedEntry = selectRandomCardEntry(random);
                if (selectedEntry != null) {
                    RegistryObject<Item> item = ModItems.getRegisteredCard(selectedEntry.getId());
                    if (item != null) {
                        CardRarity rarity = determineRarity(random);
                        ItemStack cardStack = new ItemStack(item.get());
                        cardStack.getOrCreateTag().putString("CardRarity", rarity.name());
                        cardStacks.add(cardStack);
                    }
                }
            }

            for (ItemStack cardStack : cardStacks) {
                player.getInventory().add(cardStack);
            }

            pack.shrink(1); // Decrease the pack count by 1
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    private PackEntry selectRandomCardEntry(Random random) {
        double totalWeight = packEntries.stream().mapToDouble(PackEntry::getChance).sum();
        double randomValue = random.nextDouble() * totalWeight;

        double currentWeight = 0;
        for (PackEntry entry : packEntries) {
            currentWeight += entry.getChance();
            if (randomValue <= currentWeight) {
                return entry;
            }
        }

        return null; // Should never happen if weights are properly defined
    }

    private List<PackEntry> loadPackEntries(String packFileName) {
        List<PackEntry> entries = new ArrayList<>();

        try (InputStream stream = PackItem.class.getResourceAsStream(PACK_DATA_PATH + packFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            entries = (List<PackEntry>) GSON.fromJson(reader, ArrayList.class).stream()
                    .map(obj -> GSON.fromJson(GSON.toJsonTree(obj), PackEntry.class))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    private CardRarity determineRarity(Random random) {
        double rarityChance = random.nextDouble();
        return rarityChance < 0.75 ? CardRarity.COMMON : CardRarity.RARE;
    }

    public enum PackType {
        COMMONPACK("common_pack.json");

        private final String fileName;

        PackType(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
