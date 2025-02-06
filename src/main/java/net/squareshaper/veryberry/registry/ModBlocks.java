package net.squareshaper.veryberry.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.squareshaper.veryberry.VeryBerry;
import net.squareshaper.veryberry.block.*;

public class ModBlocks {
    public static final Block RIMEBERRY_BUSH = registerBlockNoItem("rimeberry_bush",
            new RimeBerryBushBlock(AbstractBlock.Settings.create().strength(1).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly()
                    .pistonBehavior(PistonBehavior.DESTROY).nonOpaque().noCollision().luminance(state -> state.get(RimeBerryBushBlock.AGE) == 3 ? 4 : 0)
                    .mapColor(MapColor.DIAMOND_BLUE)));

    public static final Block FIRESHINE_BERRY_BODY = registerBlockNoItem("fireshine_berry_body",
            new FireShineBerryBody(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_RED)
                    .noCollision()
                    .luminance(NetherVines.getLuminanceSupplier(13))
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block FIRESHINE_BERRY_HEAD = registerBlockNoItem("fireshine_berry_head",
            new FireShineBerryHead(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_RED)
                    .ticksRandomly()
                    .noCollision()
                    .luminance(NetherVines.getLuminanceSupplier(13))
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    //use glow-lichen generation logic as inspiration for this one
    public static final Block VOID_BERRY_MOSS = registerBlockNoItem("voidberry_moss",
            new VoidBerryMoss(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .ticksRandomly()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block THORNBERRY_BRANCH = registerBlockNoItem("thornberry_branch",
            new ThornBerryBranch(AbstractBlock.Settings.create().strength(1).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly()
                    .pistonBehavior(PistonBehavior.DESTROY).noCollision().luminance(state -> state.get(ThornBerryBranch.AGE) == 3 ? 4 : 0)
                    .mapColor(MapColor.DARK_GREEN)));


    //Helper functions
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, VeryBerry.id(name), block);
    }

    private static Block registerBlockNoItem(String name, Block block) {
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
