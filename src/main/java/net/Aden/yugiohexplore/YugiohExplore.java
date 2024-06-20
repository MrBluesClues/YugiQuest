package net.Aden.yugiohexplore;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.Aden.yugiohexplore.item.ModItems;
import org.slf4j.Logger;

import java.sql.BatchUpdateException;
import java.util.function.Supplier;


@Mod(YugiohExplore.MOD_ID)
public class YugiohExplore {
    public static final String MOD_ID = "yugiohexplore";
    public static final Logger LOGGER = LogUtils.getLogger();


    public YugiohExplore() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModItems.ITEMS.register(modEventBus);


    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept((Supplier<? extends ItemLike>) ModItems.ITEMS);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Do something that can only be done on the client
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }
}