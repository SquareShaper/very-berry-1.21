package net.squareshaper.veryberry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.squareshaper.veryberry.registry.ModBlocks;
import net.squareshaper.veryberry.registry.ModEffects;
import net.squareshaper.veryberry.registry.ModFoodComponents;
import net.squareshaper.veryberry.registry.ModItems;
import net.squareshaper.veryberry.worldgen.ModBiomeModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

public class VeryBerry implements ModInitializer {
	public static final String MOD_ID = "very-berry";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String name){
		return Identifier.of(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModFoodComponents.registerModFoodComponents();
		ModBiomeModifiers.registerBiomeModifiers();
		ModEffects.registerModEffects();

		CompostingChanceRegistry.INSTANCE.add(ModItems.RIMEBERRIES, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.FIRESHINE_BERRIES, 0.4f);
	}

	public static String addZeroIfOneDigit(int number) {
		if (number > 9) {
			return String.valueOf(number);
		}
		else {
			return "0" + number;
		}
	}

	//conversion logic courtesy of https://stackoverflow.com/a/39411250
	public static String romanNumeralsOrEmpty(int number) {
		if (number == 0) {
			return "";
		}
		else {
			return " " + join("", nCopies(number + 1, "I"))
					.replace("IIIII", "V")
					.replace("IIII", "IV")
					.replace("VV", "X")
					.replace("VIV", "IX")
					.replace("XXXXX", "L")
					.replace("XXXX", "XL")
					.replace("LL", "C")
					.replace("LXL", "XC")
					.replace("CCCCC", "D")
					.replace("CCCC", "CD")
					.replace("DD", "M")
					.replace("DCD", "CM");
		}
	}

	public static String tickToTime(int ticks) {
		int hour = 72000;
		int minute = 1200;
		int second = 20;

		String output = "";

		if (ticks >= hour) {
			int hours = Math.floorDiv(ticks, hour);
			ticks = ticks - hours * hour;
			int minutes = Math.floorDiv(ticks, minute);
			ticks = ticks - minutes * minute;
			int seconds = Math.floorDiv(ticks, second);
			output = hours + ":" + addZeroIfOneDigit(minutes) + ":" +  addZeroIfOneDigit(seconds);
		}
		if (ticks >= minute) {
			int minutes = Math.floorDiv(ticks, minute);
			ticks = ticks - minutes * minute;
			int seconds = Math.floorDiv(ticks, second);
			output = addZeroIfOneDigit(minutes) + ":" +  addZeroIfOneDigit(seconds);
		}
		if (ticks >= second){
			int seconds = Math.floorDiv(ticks, second);
			output = "00:" +  addZeroIfOneDigit(seconds);
		}
		return output;
	}

	public static void addEffectTooltips(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		List<ConsumeEffect> consumeEffects = stack.get(DataComponentTypes.CONSUMABLE).onConsumeEffects();

		if (!consumeEffects.isEmpty()) {
			for (ConsumeEffect consumeEffect : consumeEffects) {
				if (consumeEffect instanceof ApplyEffectsConsumeEffect applyEffectsConsumeEffect) {
					List<StatusEffectInstance> effects = applyEffectsConsumeEffect.effects();

					for (StatusEffectInstance effect : effects) {
						textConsumer.accept(Text.translatable("tooltip.very-berry.effect", Text.translatable(effect.getTranslationKey()),
								romanNumeralsOrEmpty(effect.getAmplifier()),
								VeryBerry.tickToTime(effect.getDuration())).formatted(Formatting.BLUE));
					}
				}
			}
		}
	}
}