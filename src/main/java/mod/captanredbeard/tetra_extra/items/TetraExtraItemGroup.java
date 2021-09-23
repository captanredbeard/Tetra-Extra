package mod.captanredbeard.tetra_extra.items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import se.mickelus.tetra.blocks.geode.GeodeItem;

public class TetraExtraItemGroup extends ItemGroup {
    public static mod.captanredbeard.tetra_extra.items.TetraExtraItemGroup instance;

    public TetraExtraItemGroup() {
        super("tetra_extra");
        instance = this;
    }

    public ItemStack createIcon() {
        return new ItemStack(GeodeItem.instance);
    }
}
