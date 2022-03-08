package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;

public class AATags {
    public static final Tag.Named<Block> STRIPPABLE_LOGS = blockTag("strippable_logs");

    private static Tag.Named<Block> blockTag(String path) {
        return BlockTags.bind(AmbientAdditions.MOD_ID + ":" + path);
    }
}
