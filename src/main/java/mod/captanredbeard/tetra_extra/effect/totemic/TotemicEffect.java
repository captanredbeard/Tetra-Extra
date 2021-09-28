package mod.captanredbeard.tetra_extra.effect.totemic;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.items.modular.ModularItem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class TotemicEffect{
    private static final ItemEffect totemic = ItemEffect.get("tetra_extra.totemic");

    @SubscribeEvent
    public void totemShieldDeathCheck(LivingDeathEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.getShouldBeDead()) {
            ItemStack itemstack = null;
            ModularItem item = null;
            int level = 0;
            for (Hand hand : Hand.values()) {
                itemstack = entity.getHeldItem(hand);
                if (itemstack.getItem() instanceof ItemModularHandheld) {
                    item = (ModularItem) itemstack.getItem();
                    level = item.getEffectLevel(itemstack, totemic);
                    itemstack.shrink(1);
                    break;
                }
            }
            if (level > 0) {
              //  System.out.println(item.getAllModules(itemstack).toArray().toString());
                totemEffect(entity,itemstack);
                event.setCanceled(true);

            }
        }

    }
    private void totemEffect(LivingEntity entity, ItemStack itemstack){
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entity;
            serverplayerentity.addStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING));
            CriteriaTriggers.USED_TOTEM.trigger(serverplayerentity, itemstack);
        }
        entity.setHealth(1.0F);
        entity.clearActivePotions();
        entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 900, 1));
        entity.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 100, 1));
        entity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 800, 0));
        entity.world.setEntityState(entity, (byte) 35);
    }


}
