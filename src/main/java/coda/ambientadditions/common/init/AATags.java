package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class AATags {
    public static final ITag.INamedTag<Block> STRIPPABLE_LOGS = blockTag("strippable_logs");

    private static ITag.INamedTag<Block> blockTag(String path) {
        return BlockTags.bind(AmbientAdditions.MOD_ID + ":" + path);
    }
}
