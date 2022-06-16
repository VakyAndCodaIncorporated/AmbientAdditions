package coda.kingdomanimalia.common.registry;

import coda.kingdomanimalia.KingdomAnimalia;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;

public class KATags {
    public static final TagKey<Block> STRIPPABLE_LOGS = blockTag("strippable_logs");
    public static final TagKey<EntityType<?>> CRATE_ACCEPTABLE = entityTag("crate_acceptable");

    private static TagKey<Block> blockTag(String path) {
        return BlockTags.create(new ResourceLocation(KingdomAnimalia.MOD_ID, path));
    }

    // will this work?
    private static TagKey<EntityType<?>> entityTag(String path) {
        return EntityTypeTags.create(new ResourceLocation(KingdomAnimalia.MOD_ID, path).toString());
    }
}
