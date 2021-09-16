package mod.captanredbeard.tetra_extra;

import mod.captanredbeard.tetra_extra.items.modular.impl.ModularGloveItem;

import net.minecraft.item.Item;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@EventBusSubscriber(
        bus = Bus.MOD
        )
        @Mod("tetra_extra")


        public class TetraExtraMod {
        private static final Logger logger = LogManager.getLogger();
   //     public static final String MOD_ID = "tetra_extra";
   /*     public static IProxy proxy = (IProxy)DistExecutor.runForDist(() -> {
        return ClientProxy::new;
        }, () -> {
        return ServerProxy::new;
        });

    */
        public static TetraExtraMod instance;
        private static Item[] items;
    //    private static Block[] blocks;
      //  public static PacketHandler packetHandler;

        public TetraExtraMod() {

       // FMLJavaModLoadingContext.get().getModEventBus().addListener(CuriosCompat::enqueueIMC);
    //    TetraAttributes.registry.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
     //   MinecraftForge.EVENT_BUS.register(new ItemEffectHandler());
      //  MinecraftForge.EVENT_BUS.register(new TradeHandler());
     //   MinecraftForge.EVENT_BUS.register(new DataManager());
   //     MinecraftForge.EVENT_BUS.register(proxy);
    //    MinecraftForge.EVENT_BUS.register(new VibrationDebuffer());
     // MinecraftForge.EVENT_BUS.register(ServerScheduler.class);
     // MinecraftForge.EVENT_BUS.register(ClientScheduler.class);

        //ConfigHandler.setup();

       // new RepairRegistry();
     //   SchematicRegistry schematicRegistry = new SchematicRegistry();
    //    schematicRegistry.registerSchematic(new BookEnchantSchematic());
    //    new ItemUpgradeRegistry();
      //  ModuleRegistry moduleRegistry = new ModuleRegistry();
       // moduleRegistry.registerModuleType(new ResourceLocation("tetra", "basic_module"), BasicModule::new);
       // moduleRegistry.registerModuleType(new ResourceLocation("tetra", "multi_module"), MultiSlotModule::new);
       // moduleRegistry.registerModuleType(new ResourceLocation("tetra", "basic_major_module"), BasicMajorModule::new);
       // moduleRegistry.registerModuleType(new ResourceLocation("tetra", "multi_major_module"), MultiSlotMajorModule::new);
       // moduleRegistry.registerModuleType(new ResourceLocation("tetra", "toolbelt_module"), ToolbeltModule::new);
      //  new TetraItemGroup();
    //  CriteriaTriggers.register(BlockLookTrigger.instance);
    //    CriteriaTriggers.register(BlockUseCriterion.trigger);
      //  CriteriaTriggers.register(BlockInteractionCriterion.trigger);
     //   CriteriaTriggers.register(ModuleCraftCriterion.trigger);
    //    CriteriaTriggers.register(ImprovementCraftCriterion.trigger);
    //    ScrollBlock scrollRolled = new RolledScrollBlock();

        items = new Item[]{new ModularGloveItem()};


        items = (Item[])ArrayUtils.addAll(items, new Item[]{new ModularGloveItem()});}

        }

