package net.squareshaper.veryberry.registry;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.squareshaper.veryberry.VeryBerry;
import net.squareshaper.veryberry.block.*;

import java.util.function.Function;

public class ModBlocks {
    public static final Block RIMEBERRY_BUSH = registerBlockNoItem("rimeberry_bush",
            properties -> new RimeBerryBushBlock(properties.strength(1).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly()
                    .pistonBehavior(PistonBehavior.DESTROY).nonOpaque().noCollision().luminance(state -> state.get(RimeBerryBushBlock.AGE) == 3 ? 4 : 0)
                    .mapColor(MapColor.DIAMOND_BLUE).breakInstantly()));

    public static final Block FIRESHINE_BERRY_BODY = registerBlockNoItem("fireshine_berry_body",
            properties -> new FireShineBerryBody(properties
                    .mapColor(MapColor.DARK_RED)
                    .noCollision()
                    .luminance(NetherVines.getLuminanceSupplier(13))
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block FIRESHINE_BERRY_HEAD = registerBlockNoItem("fireshine_berry_head",
            properties -> new FireShineBerryHead(properties
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
            properties -> new VoidBerryMoss(properties
                    .mapColor(MapColor.PURPLE)
                    .ticksRandomly()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block THORNBERRY_BRANCH = registerBlockNoItem("thornberry_branch",
            properties -> new ThornBerryBranch(properties.breakInstantly().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly()
                    .pistonBehavior(PistonBehavior.DESTROY).noCollision().mapColor(MapColor.DARK_GREEN)));

    //two tall berry bush
//    public static final Block CHRONOBERRY_PLANT = registerBlockNoItem("chronoberry_plant",
//            properties -> new ChronoberryPlant(properties.breakInstantly().sounds(BlockSoundGroup.CROP).ticksRandomly()
//                    .pistonBehavior(PistonBehavior.DESTROY).noCollision().mapColor(MapColor.EMERALD_GREEN)));

//the Copper block that chronoberries spawn from
//    public static final Block NOTCHED_COPPER_BLOCK = registerBlock("notched_copper_block",
//            properties -> new OxidizableNotchedBlock(Oxidizable.OxidationLevel.UNAFFECTED,properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool()));
//
//    public static final Block EXPOSED_NOTCHED_COPPER_BLOCK = registerBlock("exposed_notched_copper_block",
//            properties -> new OxidizableNotchedBlock(Oxidizable.OxidationLevel.EXPOSED,properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)));
//
//    public static final Block WEATHERED_NOTCHED_COPPER_BLOCK = registerBlock("weathered_notched_copper_block",
//            properties -> new OxidizableNotchedBlock(Oxidizable.OxidationLevel.WEATHERED,properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool().mapColor(MapColor.DARK_AQUA)));
//
//    public static final Block OXIDIZED_NOTCHED_COPPER_BLOCK = registerBlock("oxidized_notched_copper_block",
//            properties -> new OxidizableNotchedBlock(Oxidizable.OxidationLevel.OXIDIZED,properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool().mapColor(MapColor.TEAL)));
//
//    public static final Block WAXED_NOTCHED_COPPER_BLOCK = registerBlock("waxed_notched_copper_block",
//            properties -> new NotchedBlock(properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F).requiresTool()));
//
//    public static final Block WAXED_EXPOSED_NOTCHED_COPPER_BLOCK = registerBlock("waxed_exposed_notched_copper_block",
//            properties -> new NotchedBlock(properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)));
//
//    public static final Block WAXED_WEATHERED_NOTCHED_COPPER_BLOCK = registerBlock("waxed_weathered_notched_copper_block",
//            properties -> new NotchedBlock(properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool().mapColor(MapColor.DARK_AQUA)));
//
//    public static final Block WAXED_OXIDIZED_NOTCHED_COPPER_BLOCK = registerBlock("waxed_oxidized_notched_copper_block",
//            properties -> new NotchedBlock(properties.ticksRandomly()
//                    .sounds(BlockSoundGroup.COPPER).mapColor(Blocks.COPPER_BLOCK.getDefaultMapColor()).strength(3.0F, 6.0F)
//                    .requiresTool().mapColor(MapColor.TEAL)));

    //Helper functions
    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, VeryBerry.id(name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, VeryBerry.id(name), toRegister);
    }

    private static Block registerBlockNoItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, VeryBerry.id(name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, VeryBerry.id(name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, VeryBerry.id(name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, VeryBerry.id(name)))));
    }

    public static void registerModBlocks() {
        VeryBerry.LOGGER.info("Registering Blocks for " + VeryBerry.MOD_ID + "...");
    }
}
