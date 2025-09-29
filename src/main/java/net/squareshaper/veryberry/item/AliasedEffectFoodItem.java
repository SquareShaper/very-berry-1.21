package net.squareshaper.veryberry.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.squareshaper.veryberry.VeryBerry;

import java.util.List;

public class AliasedEffectFoodItem extends BlockItem {
    public AliasedEffectFoodItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        VeryBerry.addEffectTooltips(tooltip, stack);
    }
}
