package net.Aden.yugiquest;

import net.Aden.yugiquest.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YugiQuest.MOD_ID);

    public static final RegistryObject<CreativeModeTab> YUGIQUEST_TAB = CREATIVE_MODE_TABS.register("yugiquest_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ITEMS.getEntries().stream()
                            .filter(item -> item.getId().getPath().equals("55144522"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Item with ID 55144522 not found"))
                            .get()))
                    .title(Component.translatable("creativetab.yugiquest_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        ModItems.ITEMS.getEntries().forEach(entry -> pOutput.accept(entry.get()));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
