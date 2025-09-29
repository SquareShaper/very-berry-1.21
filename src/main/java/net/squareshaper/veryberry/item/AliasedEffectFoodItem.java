package net.squareshaper.veryberry.item;

import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.squareshaper.veryberry.VeryBerry;

import java.util.function.Consumer;

public class AliasedEffectFoodItem extends BlockItem {
    public AliasedEffectFoodItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        VeryBerry.addEffectTooltips(stack, context, displayComponent, textConsumer, type);
    }
}
