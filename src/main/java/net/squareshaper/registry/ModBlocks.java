package net.squareshaper.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.squareshaper.VeryBerry;
import net.squareshaper.block.FireShineBerryBody;
import net.squareshaper.block.FireShineBerryHead;
import net.squareshaper.block.NetherVines;
import net.squareshaper.block.RimeBerryBushBlock;

public class ModBlocks {
    public static final Block RIMEBERRY_BUSH = registerBlock("rime_berry_bush",
            new RimeBerryBushBlock(AbstractBlock.Settings.create().strength(1).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly()
                    .pistonBehavior(PistonBehavior.DESTROY).nonOpaque().noCollision().luminance(state -> state.get(RimeBerryBushBlock.AGE) == 3 ? 4 : 0)
                    .mapColor(MapColor.DIAMOND_BLUE)));

    public static final Block FIRESHINE_BERRY_BODY = registerBlock("fireshine_berry_body",
            new FireShineBerryBody(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_RED)
                    .noCollision()
                    .luminance(NetherVines.getLuminanceSupplier(13))
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block FIRESHINE_BERRY_HEAD = registerBlock("fireshine_berry_head",
            new FireShineBerryHead(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_RED)
                    .ticksRandomly()
                    .noCollision()
                    .luminance(NetherVines.getLuminanceSupplier(13))
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)));


    //Helper functions
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, VeryBerry.id(name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, VeryBerry.id(name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        VeryBerry.LOGGER.info("Registering Blocks for " + VeryBerry.MOD_ID + "...");
    }
}
