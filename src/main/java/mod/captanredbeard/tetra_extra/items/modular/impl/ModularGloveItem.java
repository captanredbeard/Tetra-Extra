package mod.captanredbeard.tetra_extra.items.modular.impl;

        import javax.annotation.Nullable;
        import net.minecraft.entity.LivingEntity;
        import net.minecraft.item.ItemStack;
        import net.minecraft.item.Item.Properties;
        import net.minecraftforge.api.distmarker.Dist;
        import net.minecraftforge.api.distmarker.OnlyIn;
        import net.minecraftforge.registries.ObjectHolder;
        import se.mickelus.tetra.ConfigHandler;
        import se.mickelus.tetra.data.DataManager;
        import se.mickelus.tetra.gui.GuiModuleOffsets;
        import se.mickelus.tetra.items.modular.ItemModularHandheld;
        import se.mickelus.tetra.module.SchematicRegistry;
        import se.mickelus.tetra.module.schematic.RemoveSchematic;
        import se.mickelus.tetra.module.schematic.RepairSchematic;
        import se.mickelus.tetra.network.PacketHandler;

        public class ModularGloveItem extends ItemModularHandheld {
        public static final String headKey = "single/brace";
        public static final String handleKey = "single/wrist";
        public static final String backKey = "single/back";
        public static final String bindingKey = "single/binding";
        public static final String digitKey = "single/digits";

        private static final String unlocalizedName = "modular_single";
        private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(new int[]{1, -3, -11, 21});
        private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(new int[]{-14, 0});
        @ObjectHolder("tetra_extra:modular_glove")
        public static ModularGloveItem instance;

        public ModularGloveItem() {
        super((new Properties()).maxStackSize(1).isImmuneToFire());
        this.setRegistryName("modular_single");
        this.entityHitDamage = 1;
        this.majorModuleKeys = new String[]{"single/head", "single/handle"};
        this.minorModuleKeys = new String[]{"single/binding"};
        this.requiredModules = new String[]{"single/handle", "single/head"};
        this.updateConfig((Integer)ConfigHandler.honeSingleBase.get(), (Integer)ConfigHandler.honeSingleIntegrityMultiplier.get());
        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this));
        RemoveSchematic.registerRemoveSchematics(this);
        }

        public void init(PacketHandler packetHandler) {
        DataManager.synergyData.onReload(() -> {
        this.synergies = DataManager.instance.getSynergyData("single/");
        });
        }

        public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
        }

        public String getModelCacheKey(ItemStack itemStack, LivingEntity entity) {
        return this.isThrowing(itemStack, entity) ? super.getModelCacheKey(itemStack, entity) + ":throwing" : super.getModelCacheKey(itemStack, entity);
        }

        @OnlyIn(Dist.CLIENT)
        public String getTransformVariant(ItemStack itemStack, @Nullable LivingEntity entity) {
        return this.isThrowing(itemStack, entity) ? "throwing" : null;
        }

        @OnlyIn(Dist.CLIENT)
        public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
        }

        @OnlyIn(Dist.CLIENT)
        public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
        }
        }
