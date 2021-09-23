package mod.captanredbeard.tetra_extra;

import mod.captanredbeard.tetra_extra.items.TetraExtraItemGroup;
import mod.captanredbeard.tetra_extra.items.modular.impl.ModularGloveItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.mickelus.tetra.blocks.forged.container.ForgedContainerRenderer;
import se.mickelus.tetra.blocks.forged.hammer.HammerBaseRenderer;
import se.mickelus.tetra.blocks.scroll.ScrollRenderer;
import se.mickelus.tetra.client.model.ModularModelLoader;
import se.mickelus.tetra.compat.curios.CuriosCompat;
import se.mickelus.tetra.data.provider.ModuleProvider;
import se.mickelus.tetra.generation.FeatureEntry;
import se.mickelus.tetra.generation.TGenCommand;
import se.mickelus.tetra.module.ModuleDevCommand;
import se.mickelus.tetra.properties.TetraAttributes;

import java.util.Objects;
import java.util.stream.Stream;

@EventBusSubscriber(
        bus = Bus.MOD
)
@Mod("tetra_extra")
public class TetraExtraMod {
    private static final Logger logger = LogManager.getLogger();
    public static final String MOD_ID = "tetra_extra";

    public static TetraExtraMod instance;
    private static Item[] items;

    public TetraExtraMod() {
      //  FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CuriosCompat::enqueueIMC);
        TetraAttributes.registry.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);

        ConfigHandler.setup();
        new TetraExtraItemGroup();

        if ((Boolean)ConfigHandler.enableGlove.get()) {
            items = (Item[])ArrayUtils.addAll(items, new Item[]{new ModularGloveItem()});
        }



    }

    @SubscribeEvent
    public void serverStarting(FMLServerAboutToStartEvent event) {
        FeatureEntry.instance.setup(event.getServer());
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {
        ModuleDevCommand.register(event.getServer().getCommandManager().getDispatcher());
        TGenCommand.register(event.getServer().getCommandManager().getDispatcher());
    }

    /*
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void provideTextures(Pre event) {
        if (AtlasTexture.LOCATION_BLOCKS_TEXTURE.equals(event.getMap().getTextureLocation())) {
            Stream var10000 = Minecraft.getInstance().getResourceManager().getAllResourceLocations("textures/items/module", (s) -> {
                return s.endsWith(".png");
            }).stream().filter((resourceLocation) -> {
                return "tetra".equals(resourceLocation.getNamespace());
            }).map((rl) -> {
                return new ResourceLocation(rl.getNamespace(), rl.getPath().substring(9, rl.getPath().length() - 4));
            });
            Objects.requireNonNull(event);

            event.addSprite(ForgedContainerRenderer.material.getTextureLocation());
            event.addSprite(HammerBaseRenderer.material.getTextureLocation());
            event.addSprite(ScrollRenderer.material.getTextureLocation());
        }

    }
    */
    /*
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void modelRegistryReady(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation("tetra", "modular_loader"), new ModularModelLoader());
    }
    */
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        if (event.includeServer()) {
            dataGenerator.addProvider(new ModuleProvider(dataGenerator, event.getExistingFileHelper()));
        }

    }
    @EventBusSubscriber(
            bus = Bus.MOD
    )
    public static class RegistryEvents {
        public RegistryEvents() {
        }

        @SubscribeEvent
        public static void registerFeatures(Register<Feature<?>> event) {
            event.getRegistry().register(FeatureEntry.instance);
        }
        @SubscribeEvent
        public static void registerItems(Register<Item> event) {
            event.getRegistry().registerAll(TetraExtraMod.items);
        }
    }
}
