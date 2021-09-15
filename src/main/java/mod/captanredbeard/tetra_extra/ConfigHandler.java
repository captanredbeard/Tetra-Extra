package mod.captanredbeard.tetra_extra;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import se.mickelus.tetra.items.modular.impl.ModularBladedItem;
import se.mickelus.tetra.items.modular.impl.ModularDoubleHeadedItem;
import se.mickelus.tetra.items.modular.impl.ModularSingleHeadedItem;
import se.mickelus.tetra.items.modular.impl.bow.ModularBowItem;
import se.mickelus.tetra.items.modular.impl.crossbow.ModularCrossbowItem;
import se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem;


@Mod.EventBusSubscriber(
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ConfigHandler {
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec spec;
    public static ForgeConfigSpec.BooleanValue development;
    public static ForgeConfigSpec.BooleanValue toolbeltCurioOnly;
    public static ForgeConfigSpec.IntValue geodeDensity;
    public static ForgeConfigSpec.BooleanValue generateFeatures;
    public static ForgeConfigSpec.IntValue maxFeatureDepth;
    public static ForgeConfigSpec.BooleanValue moduleProgression;
    public static ForgeConfigSpec.IntValue settleLimitBase;
    public static ForgeConfigSpec.DoubleValue settleLimitLevelMultiplier;
    public static ForgeConfigSpec.DoubleValue settleLimitDurabilityMultiplier;
    public static ForgeConfigSpec.DoubleValue magicCapacityMultiplier;
    public static ForgeConfigSpec.IntValue honeSwordBase;
    public static ForgeConfigSpec.IntValue honeSwordIntegrityMultiplier;
    public static ForgeConfigSpec.IntValue honedoubleBase;
    public static ForgeConfigSpec.IntValue honedoubleIntegrityMultiplier;
    public static ForgeConfigSpec.IntValue honeSingleBase;
    public static ForgeConfigSpec.IntValue honeSingleIntegrityMultiplier;
    public static ForgeConfigSpec.IntValue honeBowBase;
    public static ForgeConfigSpec.IntValue honeBowIntegrityMultiplier;
    public static ForgeConfigSpec.IntValue honeCrossbowBase;
    public static ForgeConfigSpec.IntValue honeCrossbowIntegrityMultiplier;
    public static ForgeConfigSpec.IntValue honeShieldBase;
    public static ForgeConfigSpec.IntValue honeShieldIntegrityMultiplier;
    public static ForgeConfigSpec.BooleanValue enableBow;
    public static ForgeConfigSpec.BooleanValue enableCrossbow;
    public static ForgeConfigSpec.BooleanValue enableSingle;
    public static ForgeConfigSpec.BooleanValue enableShield;
    public static ForgeConfigSpec.BooleanValue enableGlint;
    public static ForgeConfigSpec.BooleanValue enableStonecutter;
    public static ForgeConfigSpec.BooleanValue enableExtractor;
    public static ForgeConfigSpec.BooleanValue enableLookTrigger;
    public static ForgeConfigSpec.BooleanValue enableReach;

    public ConfigHandler() {
    }

    public static void setup() {
        CommentedFileConfig configData = (CommentedFileConfig)CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve("tetra.toml")).sync().autosave().preserveInsertionOrder().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(ModConfig.Loading configEvent) {
        ModularBladedItem.instance.updateConfig((Integer)honeSwordBase.get(), (Integer)honeSwordIntegrityMultiplier.get());
        ModularDoubleHeadedItem.instance.updateConfig((Integer)honedoubleBase.get(), (Integer)honedoubleIntegrityMultiplier.get());
        if (ModularBowItem.instance != null) {
            ModularBowItem.instance.updateConfig((Integer)honeBowBase.get(), (Integer)honeBowIntegrityMultiplier.get());
        }

        if (ModularCrossbowItem.instance != null) {
            ModularCrossbowItem.instance.updateConfig((Integer)honeCrossbowBase.get(), (Integer)honeCrossbowIntegrityMultiplier.get());
        }

        if (ModularSingleHeadedItem.instance != null) {
            ModularSingleHeadedItem.instance.updateConfig((Integer)honeSingleBase.get(), (Integer)honeSingleIntegrityMultiplier.get());
        }

        if (ModularShieldItem.instance != null) {
            ModularShieldItem.instance.updateConfig((Integer)honeShieldBase.get(), (Integer)honeShieldIntegrityMultiplier.get());
        }

    }

    @SubscribeEvent
    public static void onReload(ModConfig.Reloading configEvent) {
        ModularBladedItem.instance.updateConfig((Integer)honeSwordBase.get(), (Integer)honeSwordIntegrityMultiplier.get());
        ModularDoubleHeadedItem.instance.updateConfig((Integer)honedoubleBase.get(), (Integer)honedoubleIntegrityMultiplier.get());
        if (ModularBowItem.instance != null) {
            ModularBowItem.instance.updateConfig((Integer)honeBowBase.get(), (Integer)honeBowIntegrityMultiplier.get());
        }

        if (ModularCrossbowItem.instance != null) {
            ModularCrossbowItem.instance.updateConfig((Integer)honeCrossbowBase.get(), (Integer)honeCrossbowIntegrityMultiplier.get());
        }

        if (ModularSingleHeadedItem.instance != null) {
            ModularSingleHeadedItem.instance.updateConfig((Integer)honeSingleBase.get(), (Integer)honeSingleIntegrityMultiplier.get());
        }

        if (ModularShieldItem.instance != null) {
            ModularShieldItem.instance.updateConfig((Integer)honeShieldBase.get(), (Integer)honeShieldIntegrityMultiplier.get());
        }

    }

    static {
        builder.push("misc");
        enableBow = builder.comment("Enable modular bows").worldRestart().define("bow", true);
        enableSingle = builder.comment("Enable modular single headed implements").worldRestart().define("single_headed", true);
        enableShield = builder.comment("Enable modular shields").worldRestart().define("shield", true);
        enableGlint = builder.comment("Enables the enchantment glint rendering on modular items").define("glint", true);
        development = builder.comment("Enables commands & data reloading functionality useful for development, has a negative impact on performance").worldRestart().define("development", false);
        toolbeltCurioOnly = builder.comment("If enabled and Curios is installed, Toolbelts will only work in the Curio belt slot").define("toolbelt_curio_only", false);
        magicCapacityMultiplier = builder.comment("Multiplier for magic capacity gains, increasing this may be useful when having a large set enchantments added by other mods").defineInRange("magic_cap_multiplier", 1.0D, 0.0D, 1.7976931348623157E308D);
        enableLookTrigger = builder.comment("Enable the look advancement trigger, used for some advancements. ").worldRestart().define("look_trigger", true);
        enableReach = builder.comment("Allow the reach attribute to modify the distance at which players can hit entities").worldRestart().define("entity_reach", true);
        builder.pop();
        builder.comment("World generation settings").push("worldgen");
        geodeDensity = builder.comment("The number of geodes that should generate per chunk, set to 0 to disable").worldRestart().defineInRange("geode_density", 120, 0, 65536);
        generateFeatures = builder.comment("Generates features in the world, further configuration available in \"tetra/data/structures/\"").worldRestart().define("features", true);
        maxFeatureDepth = builder.comment("Used to limit how deep the feature generator will recurse into feature children, helps to avoid recursive loops and cascading worldgen in 3d-party generation features").defineInRange("feature_depth", 8, 0, 64);
        builder.pop();
        builder.comment("Allows tetra items to \"level up\" after being used a certain amount of times, allowing the player to choose from different ways to \"hone\" 1 module on the item. Major modules also settle after some time, increasing its integrity").push("module_progression");
        moduleProgression = builder.define("enabled", true);
        settleLimitBase = builder.comment("The base value for number of uses required for a module to settle").defineInRange("settle_base", 270, -2147483648, 2147483647);
        settleLimitLevelMultiplier = builder.comment("Level multiplier for settling limit, a value of 3 would cause a module that has settled once to require 3x as many uses before it settles again").defineInRange("settle_level_multiplier", 3.0D, 4.9E-324D, 1.7976931348623157E308D);
        settleLimitDurabilityMultiplier = builder.comment("Durability multiplier for settling limit, a value of 1 would cause a module with 75 durability to require an additional 75 uses before it settles").defineInRange("settle_durability_multiplier", 0.5D, 4.9E-324D, 1.7976931348623157E308D);
        honeSwordBase = builder.comment("The base value for number of uses required before a sword can be honed").defineInRange("hone_sword_base", 110, -2147483648, 2147483647);
        honeSwordIntegrityMultiplier = builder.comment("Integrity multiplier for sword honing, a value of 2 would cause a sword which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_sword_integrity_multiplier", 65, -2147483648, 2147483647);
        honedoubleBase = builder.comment("The base value for number of uses required before a tool can be honed").defineInRange("hone_double_base", 140, -2147483648, 2147483647);
        honedoubleIntegrityMultiplier = builder.comment("Integrity multiplier for tool honing, a value of 2 would cause a sword which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_double_integrity_multiplier", 75, -2147483648, 2147483647);
        honeBowBase = builder.comment("The base value for number of uses required before a bow can be honed").defineInRange("hone_bow_base", 48, -2147483648, 2147483647);
        honeBowIntegrityMultiplier = builder.comment("Integrity multiplier for bow honing, a value of 2 would cause a bow which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_bow_integrity_multiplier", 32, -2147483648, 2147483647);
        honeShieldBase = builder.comment("The base value for number of uses required before a shield can be honed").defineInRange("hone_shield_base", 48, -2147483648, 2147483647);
        honeShieldIntegrityMultiplier = builder.comment("Integrity multiplier for shield honing, a value of 2 would cause a shield which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_shield_integrity_multiplier", 32, -2147483648, 2147483647);
        honeCrossbowBase = builder.comment("The base value for number of uses required before a crossbow can be honed").defineInRange("hone_crossbow_base", 48, -2147483648, 2147483647);
        honeCrossbowIntegrityMultiplier = builder.comment("Integrity multiplier for crossbow honing, a value of 2 would cause a crossbow which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_crossbow_integrity_multiplier", 32, -2147483648, 2147483647);
        honeSingleBase = builder.comment("The base value for number of uses required before a single headed implement can be honed").defineInRange("hone_single_headed_base", 48, -2147483648, 2147483647);
        honeSingleIntegrityMultiplier = builder.comment("Integrity multiplier for single headed implement honing, a value of 2 would cause an implement which uses 3 integrity to require 2*3 times as many uses before it can be honed").defineInRange("hone_single_headed_integrity_multiplier", 32, -2147483648, 2147483647);
        builder.pop();
        builder.comment("Toggles & config for experimental features").push("experimental");
        enableStonecutter = builder.comment("Enable the stonecutter module for swords, the stonecutter has to be removed from loot tables if this is disabled").worldRestart().define("stonecutter", true);
        enableExtractor = builder.comment("Enable the extractor bedrock functionality").worldRestart().define("extractor", true);
        enableCrossbow = builder.comment("Enable modular crossbows").worldRestart().define("crossbow", true);
        builder.pop();
        spec = builder.build();
    }
}
