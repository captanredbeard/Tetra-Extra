package mod.captanredbeard.tetra_extra.items.modular.impl;

import mod.captanredbeard.tetra_extra.ConfigHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.network.PacketHandler;

public class ModularGloveItem extends ItemModularHandheld {
        public static final String braceKey = "glove/brace";
        public static final String backKey = "glove/back";
        public static final String bindingKey = "glove/binding";
        public static final String mountKey = "glove/mount";
        //public static final String digitKey = "glove/digits";
        private static final String unlocalizedName = "modular_glove";
        private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(new int[]{-12, 20,-16, 3, 2, 20});
        private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(new int[]{-8, -6,5,-1});
        @ObjectHolder("tetra_extra:modular_glove")
        public static ModularGloveItem instance;

        public ModularGloveItem() {
        super((new Properties()).maxStackSize(1).isImmuneToFire());
        this.setRegistryName("modular_glove");
        this.entityHitDamage = 1;
        this.majorModuleKeys = new String[]{"glove/brace","glove/back","glove/mount"};
        this.minorModuleKeys = new String[]{"glove/binding"};
        this.requiredModules = new String[]{"glove/brace","glove/back"};
        this.updateConfig(ConfigHandler.honeGloveBase.get(), (Integer)ConfigHandler.honeGloveIntegrityMultiplier.get());
     //   SchematicRegistry.instance.registerSchematic(new RepairSchematic(this));
       // RemoveSchematic.registerRemoveSchematics(this);
        }

        public void init(PacketHandler packetHandler) {
                DataManager.synergyData.onReload(() -> {
                this.synergies = DataManager.instance.getSynergyData("glove/");
        });
        }

        public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
                if (this.isInGroup(group)) {
                        items.add(this.setupGloveStack("leather", "leather"));
                        items.add(this.setupGloveStack("iron", "iron"));
                }

        }

        private ItemStack setupGloveStack(String braceMaterial, String backMaterial) {
                ItemStack itemStack = new ItemStack(this);
                IModularItem.putModuleInSlot(itemStack, "glove/brace", "glove/brace", "glove/brace", "basic_glove/" + braceMaterial);
                IModularItem.putModuleInSlot(itemStack, "glove/back", "double/back", "double/back", "basic_glove/" + backMaterial);
                IModularItem.updateIdentifier(itemStack);
                return itemStack;
        }

        public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
        }


        @OnlyIn(Dist.CLIENT)
        public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
        }

        @OnlyIn(Dist.CLIENT)
        public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
        }

        /*
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void provideTextures(TextureStitchEvent.Pre event) {
                if (AtlasTexture.LOCATION_BLOCKS_TEXTURE.equals(event.getMap().getTextureLocation())) {
                        Stream var10000 = Minecraft.getInstance().getResourceManager().getAllResourceLocations("textures/items/module", (s) -> {
                                return s.endsWith(".png");
                        }).stream().filter((resourceLocation) -> {
                                return "tetra_extra".equals(resourceLocation.getNamespace());
                        }).map((rl) -> {
                                return new ResourceLocation(rl.getNamespace(), rl.getPath().substring(9, rl.getPath().length() - 4));
                        });
                        Objects.requireNonNull(event);
                        var10000.forEach(event::addSprite);
                        event.addSprite(ForgedContainerRenderer.material.getTextureLocation());
                        event.addSprite(HammerBaseRenderer.material.getTextureLocation());
                        event.addSprite(ScrollRenderer.material.getTextureLocation());
                }

        }

         */
        }
