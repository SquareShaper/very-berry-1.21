package net.squareshaper.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.squareshaper.block.BerryBushBlock;
import net.squareshaper.registry.ModBlocks;
import net.squareshaper.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RIMEBERRY_BUSH, berryDrops(ModBlocks.RIMEBERRY_BUSH, ModItems.RIMEBERRIES, 3, 4, 3));
    }

    public LootTable.Builder berryDrops(Block block, Item item, float minDrops, float maxDrops, int harvestAge) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        return this.applyExplosionDecay(block, LootTable.builder()
                .pool(LootPool.builder().conditionally(
                                BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(BerryBushBlock.AGE, harvestAge)))
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                )
                .pool(LootPool.builder().conditionally(
                                BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(BerryBushBlock.AGE, harvestAge-1)))
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }


}
