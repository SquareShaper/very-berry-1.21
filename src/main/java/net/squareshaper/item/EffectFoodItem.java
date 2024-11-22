package net.squareshaper.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.squareshaper.VeryBerry;

import java.util.List;

public class EffectFoodItem extends Item {

    public EffectFoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        VeryBerry.addEffectTooltips(tooltip, stack);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
