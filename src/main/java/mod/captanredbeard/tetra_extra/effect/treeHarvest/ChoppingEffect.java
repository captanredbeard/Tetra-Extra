
package mod.captanredbeard.tetra_extra.effect.treeHarvest;
/*
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.items.modular.ModularItem;

public class ChoppingEffect {
    private static final ItemEffect treeHarvest = ItemEffect.get("tetra_extra.treeHarvest");
    private static boolean deforesting = false;

    @SubscribeEvent
    public void onBlockBrokenModular(BlockEvent.BreakEvent event){
        PlayerEntity player = event.getPlayer();
        ItemStack itemstack = player.getHeldItemMainhand();
        if (itemstack.getItem() instanceof ItemModularHandheld) {
            ModularItem item = (ModularItem) itemstack.getItem();
            int level = item.getEffectLevel(itemstack, treeHarvest);

            //item.applyDamage(0,itemstack,player);
            if(level >= 1){
                ChoppingBreak.findTree(new Blockreader(),event.getWorld(), pos).destroyBlocks(worldIn, player, );
            }
        }
    }
    public static void dropItemFromCutTree(World world, BlockPos breakingPos, BlockPos pos, ItemStack stack) {
      //  float distance = (float) Math.sqrt(pos.distSqr(breakingPos));
        Vector3d dropPos = VecHelper.getCenterOf(pos);
        ItemEntity entity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
    //    entity.setDeltaMovement(fallDirection.scale(distance / 20f));
        //world.addFreshEntity(entity);
    }
}
*/