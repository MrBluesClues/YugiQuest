package net.Aden.yugiquest;

import net.Aden.yugiquest.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(YugiQuest.MOD_ID)
public class YugiQuest {
    public static final String MOD_ID = "yugiquest";
    public static final Logger LOGGER = LogManager.getLogger();

    public YugiQuest() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register mod-specific event listeners
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::doClientStuff);

        // Register the mod's item registry
        ModItems.register(modEventBus);

        // Register this class to receive Forge events
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Perform setup tasks
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Perform common setup tasks
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Perform client-specific setup tasks
    }

    @SubscribeEvent
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            ModItems.ITEMS.getEntries().forEach(entry -> {
                event.accept(entry.get());
            });
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Handle server starting event
    }
}
