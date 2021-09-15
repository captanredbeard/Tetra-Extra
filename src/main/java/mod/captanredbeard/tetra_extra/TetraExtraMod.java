package mod.captanredbeard.tetra_extra;

import mod.captanredbeard.tetra_extra.items.modular.impl.ModularGloveItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.mickelus.tetra.advancements.*;
import se.mickelus.tetra.blocks.ITetraBlock;
import se.mickelus.tetra.blocks.forged.*;
import se.mickelus.tetra.blocks.forged.chthonic.ChthonicExtractorBlock;
import se.mickelus.tetra.blocks.forged.chthonic.DepletedBedrockBlock;
import se.mickelus.tetra.blocks.forged.chthonic.FracturedBedrockBlock;
import se.mickelus.tetra.blocks.forged.container.ForgedContainerBlock;
import se.mickelus.tetra.blocks.forged.container.ForgedContainerRenderer;
import se.mickelus.tetra.blocks.forged.extractor.CoreExtractorBaseBlock;
import se.mickelus.tetra.blocks.forged.extractor.CoreExtractorPipeBlock;
import se.mickelus.tetra.blocks.forged.extractor.CoreExtractorPistonBlock;
import se.mickelus.tetra.blocks.forged.extractor.SeepingBedrockBlock;
import se.mickelus.tetra.blocks.forged.hammer.HammerBaseBlock;
import se.mickelus.tetra.blocks.forged.hammer.HammerBaseRenderer;
import se.mickelus.tetra.blocks.forged.hammer.HammerHeadBlock;
import se.mickelus.tetra.blocks.forged.transfer.TransferUnitBlock;
import se.mickelus.tetra.blocks.geode.*;
import se.mickelus.tetra.blocks.rack.RackBlock;
import se.mickelus.tetra.blocks.scroll.*;
import se.mickelus.tetra.blocks.workbench.BasicWorkbenchBlock;
import se.mickelus.tetra.blocks.workbench.WorkbenchTile;
import se.mickelus.tetra.client.model.ModularModelLoader;
import se.mickelus.tetra.compat.curios.CuriosCompat;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.data.UpdateDataPacket;
import se.mickelus.tetra.data.provider.ModuleProvider;
import se.mickelus.tetra.effect.ItemEffectHandler;
import se.mickelus.tetra.effect.LungeEchoPacket;
import se.mickelus.tetra.effect.TruesweepPacket;
import se.mickelus.tetra.effect.howling.HowlingPacket;
import se.mickelus.tetra.effect.revenge.AddRevengePacket;
import se.mickelus.tetra.effect.revenge.RemoveRevengePacket;
import se.mickelus.tetra.generation.FeatureEntry;
import se.mickelus.tetra.generation.TGenCommand;
import se.mickelus.tetra.items.ITetraItem;
import se.mickelus.tetra.items.TetraItemGroup;
import se.mickelus.tetra.items.cell.ItemCellMagmatic;
import se.mickelus.tetra.items.forged.*;
import se.mickelus.tetra.items.loot.DragonSinewItem;
import se.mickelus.tetra.items.modular.ChargedAbilityPacket;
import se.mickelus.tetra.items.modular.SecondaryAbilityPacket;
import se.mickelus.tetra.items.modular.impl.ModularBladedItem;
import se.mickelus.tetra.items.modular.impl.ModularDoubleHeadedItem;
import se.mickelus.tetra.items.modular.impl.ModularSingleHeadedItem;
import se.mickelus.tetra.items.modular.impl.bow.ModularBowItem;
import se.mickelus.tetra.items.modular.impl.bow.ProjectileMotionPacket;
import se.mickelus.tetra.items.modular.impl.crossbow.ModularCrossbowItem;
import se.mickelus.tetra.items.modular.impl.holo.ModularHolosphereItem;
import se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem;
import se.mickelus.tetra.items.modular.impl.toolbelt.ModularToolbeltItem;
import se.mickelus.tetra.items.modular.impl.toolbelt.ToolbeltModule;
import se.mickelus.tetra.loot.ReplaceTableModifier.Serializer;
import se.mickelus.tetra.module.*;
import se.mickelus.tetra.module.improvement.DestabilizationEffect;
import se.mickelus.tetra.module.improvement.HonePacket;
import se.mickelus.tetra.module.improvement.SettlePacket;
import se.mickelus.tetra.module.schematic.BookEnchantSchematic;
import se.mickelus.tetra.module.schematic.CleanseSchematic;
import se.mickelus.tetra.network.PacketHandler;
import se.mickelus.tetra.properties.TetraAttributes;
import se.mickelus.tetra.proxy.ClientProxy;
import se.mickelus.tetra.proxy.IProxy;
import se.mickelus.tetra.proxy.ServerProxy;
import se.mickelus.tetra.trades.TradeHandler;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@EventBusSubscriber(
        bus = Bus.MOD
        )
        @Mod("tetra_extra")


        public class TetraExtraMod {
        private static final Logger logger = LogManager.getLogger();
   //     public static final String MOD_ID = "tetra_extra";
        public static IProxy proxy = (IProxy)DistExecutor.runForDist(() -> {
        return ClientProxy::new;
        }, () -> {
        return ServerProxy::new;
        });
        public static TetraExtraMod instance;
        private static Item[] items;
        private static Block[] blocks;
        public static PacketHandler packetHandler;

        public TetraExtraMod() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(CuriosCompat::enqueueIMC);
        TetraAttributes.registry.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ItemEffectHandler());
        MinecraftForge.EVENT_BUS.register(new TradeHandler());
        MinecraftForge.EVENT_BUS.register(new DataManager());
        MinecraftForge.EVENT_BUS.register(proxy);
        MinecraftForge.EVENT_BUS.register(new VibrationDebuffer());
     // MinecraftForge.EVENT_BUS.register(ServerScheduler.class);
     // MinecraftForge.EVENT_BUS.register(ClientScheduler.class);

        ConfigHandler.setup();

        new RepairRegistry();
        SchematicRegistry schematicRegistry = new SchematicRegistry();
        schematicRegistry.registerSchematic(new BookEnchantSchematic());
        new ItemUpgradeRegistry();
        ModuleRegistry moduleRegistry = new ModuleRegistry();
        moduleRegistry.registerModuleType(new ResourceLocation("tetra", "basic_module"), BasicModule::new);
        moduleRegistry.registerModuleType(new ResourceLocation("tetra", "multi_module"), MultiSlotModule::new);
        moduleRegistry.registerModuleType(new ResourceLocation("tetra", "basic_major_module"), BasicMajorModule::new);
        moduleRegistry.registerModuleType(new ResourceLocation("tetra", "multi_major_module"), MultiSlotMajorModule::new);
        moduleRegistry.registerModuleType(new ResourceLocation("tetra", "toolbelt_module"), ToolbeltModule::new);
        new TetraItemGroup();
    //  CriteriaTriggers.register(BlockLookTrigger.instance);
    //    CriteriaTriggers.register(BlockUseCriterion.trigger);
      //  CriteriaTriggers.register(BlockInteractionCriterion.trigger);
     //   CriteriaTriggers.register(ModuleCraftCriterion.trigger);
    //    CriteriaTriggers.register(ImprovementCraftCriterion.trigger);
    //    ScrollBlock scrollRolled = new RolledScrollBlock();

        items = new Item[]{new ModularBladedItem(), new ModularDoubleHeadedItem(),new ModularGloveItem(), new GeodeItem(), new PristineLapisItem(), new PristineEmeraldItem(), new PristineDiamondItem(), new ModularToolbeltItem(), new ItemCellMagmatic(), new ItemBolt(), new ItemBeam(), new ItemMesh(), new ItemQuickLatch()};


        items = (Item[])ArrayUtils.addAll(items, new Item[]{new ModularGloveItem()});}

        }

