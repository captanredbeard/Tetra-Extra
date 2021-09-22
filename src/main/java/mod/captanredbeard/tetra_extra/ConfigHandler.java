package mod.captanredbeard.tetra_extra;


import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import mod.captanredbeard.tetra_extra.items.modular.impl.ModularGloveItem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Loading;
import net.minecraftforge.fml.config.ModConfig.Reloading;
import net.minecraftforge.fml.loading.FMLPaths;

@EventBusSubscriber(
        bus = Bus.MOD
)
public class ConfigHandler {
    private static final Builder builder = new Builder();
    public static ForgeConfigSpec spec;
    public static IntValue honeGloveBase;
    public static IntValue honeGloveIntegrityMultiplier;
    public static BooleanValue enableGlove;
    public static BooleanValue gloveCurioOnly;

    public ConfigHandler() {
    }

    public static void setup() {
        CommentedFileConfig configData = (CommentedFileConfig)CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve("tetra_extra.toml")).sync().autosave().preserveInsertionOrder().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(Loading configEvent) {
        if (ModularGloveItem.instance != null) {
            ModularGloveItem.instance.updateConfig((Integer)honeGloveBase.get(), (Integer)honeGloveIntegrityMultiplier.get());
        }

    }

    @SubscribeEvent
    public static void onReload(Reloading configEvent) {
        if (ModularGloveItem.instance != null) {
            ModularGloveItem.instance.updateConfig((Integer)honeGloveBase.get(), (Integer)honeGloveIntegrityMultiplier.get());
        }

    }

    static {
        builder.push("Modular tool settings");
        enableGlove = builder.comment("Enable modular Glove").worldRestart().define("glove", true);
        gloveCurioOnly = builder.comment("Set to true to limit modular glove usage to curios slots only").define("glove_curio_only", false);
        honeGloveBase = builder.comment("The base value for number of uses required before a sword can be honed").defineInRange("hone_sword_base", 110, -2147483648, 2147483647);
        honeGloveIntegrityMultiplier = builder.comment("Integrity multiplier for sword honing, a value of 2 would cause a sword which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_sword_integrity_multiplier", 65, -2147483648, 2147483647);

        builder.pop();
        spec = builder.build();
    }
}
