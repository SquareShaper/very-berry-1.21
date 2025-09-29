package net.squareshaper.veryberry.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.squareshaper.veryberry.VeryBerry;

import java.util.function.Consumer;

public class EffectFoodItem extends Item {

    public EffectFoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        VeryBerry.addEffectTooltips(stack, context, displayComponent, textConsumer, type);
    }
}
