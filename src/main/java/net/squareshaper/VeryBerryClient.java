package net.squareshaper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.squareshaper.registry.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class VeryBerryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        List<Block> berryBlocks = new ArrayList<>();
        berryBlocks.add(ModBlocks.RIMEBERRY_BUSH);
        berryBlocks.add(ModBlocks.FIRESHINE_BERRY_BODY);
        berryBlocks.add(ModBlocks.FIRESHINE_BERRY_HEAD);

        for (Block berry : berryBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(berry, RenderLayer.getCutout());
        }
    }
}
