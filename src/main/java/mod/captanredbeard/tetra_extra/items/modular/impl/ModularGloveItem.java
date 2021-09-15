package mod.captanredbeard.tetra_extra.items.modular.impl;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.mickelus.tetra.effect.ChargedAbilityEffect;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.TetraItemGroup;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.schematic.RemoveSchematic;
import se.mickelus.tetra.module.schematic.RepairSchematic;

import java.util.Arrays;
import java.util.Optional;

public class ModularGloveItem extends ItemModularHandheld {
    private static final Logger logger = LogManager.getLogger();
    public static final String bracerKey = "glove/bracer";
    public static final String wristKey = "glove/wrist";
    public static final String bindingKey = "glove/binding";
    public static final String accessoryKey = "glove/accessory";
    public static final String backKey = "glove/back";
    public static final String digitKey = "glove/digits";
    private static final String unlocalizedName = "modular_glove";
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(new int[]{-13, -1, 3, 19, -13, 19});
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(new int[]{6, 1,-2, 4,2, 4});
    @ObjectHolder("tetra:modular_glove")
    public static mod.captanredbeard.tetra_extra.items.modular.impl.ModularGloveItem instance;

    public ModularGloveItem() {
        super((new Properties()).maxStackSize(1).group(TetraItemGroup.instance).isImmuneToFire());
        this.setRegistryName("modular_glove");
        this.entityHitDamage = 2;
        this.majorModuleKeys = new String[]{"glove/bracer", "glove/wrist", "glove/back"};
        this.minorModuleKeys = new String[]{"glove/binding","glove/accessory","glove/digits"};
        this.requiredModules = new String[]{"glove/handle", "glove/head_left", "glove/head_right"};
        //this.updateConfig((Integer)ConfigHandler.honedoubleBase.get(), (Integer)ConfigHandler.honedoubleIntegrityMultiplier.get());
        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this));
        RemoveSchematic.registerRemoveSchematics(this);
    }
/*
    public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
    }
*/
  /*
    public void init(PacketHandler packetHandler) {
        DataManager.synergyData.onReload(() -> {
            this.synergies = DataManager.instance.getSynergyData("glove/");
        });
    }
*/
/*
    public String getDisplayNamePrefixes(ItemStack itemStack) {
        String modulePrefix = (String)Optional.ofNullable(this.getModuleFromSlot(itemStack, "glove/wrist")).map((module) -> {
            return module.getItemPrefix(itemStack);
        }).map((prefix) -> {
            return prefix + " ";
        }).orElse("");
        return (String)Arrays.stream(this.getImprovements(itemStack)).map((improvement) -> {
            return improvement.key + ".prefix";
        }).filter(I18n::hasKey).map((x$0) -> {
            return I18n.format(x$0, new Object[0]);
        }).findFirst().map((prefix) -> {
            return prefix + " " + modulePrefix;
        }).orElse(modulePrefix);
    }



    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack itemStack) {
        Multimap<Attribute, AttributeModifier> moduleAttributes = (Multimap)Stream.of(this.getModuleFromSlot(itemStack, "glove/head_left"), this.getModuleFromSlot(itemStack, "glove/head_right")).filter(Objects::nonNull).map((module) -> {
            return module.getAttributeModifiers(itemStack);
        }).filter(Objects::nonNull).map((modifiers) -> {
            return (ArrayListMultimap)modifiers.asMap().entrySet().stream().collect(Multimaps.flatteningToMultimap(Entry::getKey, (entry) -> {
                return AttributeHelper.collapse((Collection)entry.getValue()).stream();
            }, ArrayListMultimap::create));
        }).map(Multimap::entries).flatMap(Collection::stream).collect(Multimaps.toMultimap(Entry::getKey, Entry::getValue, ArrayListMultimap::create));
        moduleAttributes = AttributeHelper.retainMax(moduleAttributes, new Attribute[]{Attributes.ATTACK_DAMAGE});
        moduleAttributes = (Multimap)this.getAllModules(itemStack).stream().filter((itemModule) -> {
            return !"glove/head_left".equals(itemModule.getSlot()) && !"glove/head_right".equals(itemModule.getSlot());
        }).map((module) -> {
            return module.getAttributeModifiers(itemStack);
        }).reduce(moduleAttributes, AttributeHelper::merge);
        return (Multimap)Arrays.stream(this.getSynergyData(itemStack)).map((synergy) -> {
            return synergy.attributes;
        }).reduce(moduleAttributes, AttributeHelper::merge);
    }
*/
    /*
    public ToolData getToolDataRaw(ItemStack itemStack) {
        logger.debug("Gathering tool data for {} ({})", this.getDisplayName(itemStack).getString(), this.getDataCacheKey(itemStack));
        ToolData result = ToolData.retainMax((Collection)Stream.of(this.getModuleFromSlot(itemStack, "glove/head_left"), this.getModuleFromSlot(itemStack, "glove/head_right")).filter(Objects::nonNull).map((module) -> {
            return module.getToolData(itemStack);
        }).filter(Objects::nonNull).collect(Collectors.toList()));
        return (ToolData)Stream.concat(this.getAllModules(itemStack).stream().filter((itemModule) -> {
            return !"glove/head_left".equals(itemModule.getSlot()) && !"glove/head_right".equals(itemModule.getSlot());
        }).map((module) -> {
            return module.getToolData(itemStack);
        }), Arrays.stream(this.getSynergyData(itemStack)).map((synergy) -> {
            return synergy.tools;
        })).filter(Objects::nonNull).reduce(result, ToolData::merge);
    }
    */

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
    }

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
    }

    public String getModelCacheKey(ItemStack itemStack, LivingEntity entity) {
        return entity != null && itemStack.equals(entity.getActiveItemStack()) ? (String)Optional.ofNullable(this.getChargeableAbility(itemStack)).map(ChargedAbilityEffect::getModelTransform).map((transform) -> {
            return super.getModelCacheKey(itemStack, entity) + ":" + transform;
        }).orElseGet(() -> {
            return super.getModelCacheKey(itemStack, entity);
        }) : super.getModelCacheKey(itemStack, entity);
    }
/*
    @OnlyIn(Dist.CLIENT)
    public String getTransformVariant(ItemStack itemStack, @Nullable LivingEntity entity) {
        ChargedAbilityEffect ability = this.getChargeableAbility(itemStack);
        return entity != null && ability != null && itemStack.equals(entity.getActiveItemStack()) ? ability.getModelTransform() : null;
    }
*/
}
