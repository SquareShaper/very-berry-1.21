package net.squareshaper.veryberry.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.squareshaper.veryberry.VeryBerry;

import java.util.List;

public class DrinkItem extends Item {
    private static final int MAX_USE_TIME = 32;
    private static Item RETURN_ITEM = Items.GLASS_BOTTLE;

    public DrinkItem(Settings settings, Item returnItem) {
        super(settings);
        RETURN_ITEM = returnItem;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;


        if (playerEntity != null) {
            playerEntity.getHungerManager().eat(stack.get(DataComponentTypes.FOOD));
            if (!world.isClient()) {
                for (FoodComponent.StatusEffectEntry statusEffectEntry : stack.get(DataComponentTypes.FOOD).effects()) {
                    if (playerEntity.getRandom().nextFloat() < statusEffectEntry.probability()) {
                        playerEntity.addStatusEffect(statusEffectEntry.effect());
                    }
                }
            }
        }

        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
        }

        stack.decrementUnlessCreative(1, playerEntity);

        if (!playerEntity.isInCreativeMode()) {
            if (stack.isEmpty()) {
                return new ItemStack(RETURN_ITEM);
            }

            playerEntity.getInventory().insertStack(new ItemStack(RETURN_ITEM));
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        VeryBerry.addEffectTooltips(tooltip, stack);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
