package net.squareshaper.veryberry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.squareshaper.veryberry.registry.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class VeryBerryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        List<Block> berryBlocks = new ArrayList<>();
        berryBlocks.add(ModBlocks.RIMEBERRY_BUSH);
        berryBlocks.add(ModBlocks.FIRESHINE_BERRY_BODY);
        berryBlocks.add(ModBlocks.FIRESHINE_BERRY_HEAD);
        berryBlocks.add(ModBlocks.VOID_BERRY_MOSS);
        berryBlocks.add(ModBlocks.THORNBERRY_BRANCH);
//        berryBlocks.add(ModBlocks.CHRONOBERRY_PLANT);

        for (Block berry : berryBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(berry, RenderLayer.getCutout());
        }
    }
}
