package com.techmonkey.watered_roots.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneBlockMixin {
    private static final int UNDERGROUND_WATER_RANGE = 2;

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void wateredRoots$acceptUndergroundWater(
            BlockState caneState,
            LevelReader level,
            BlockPos canePos,
            CallbackInfoReturnable<Boolean> callback
    ) {
        if (callback.getReturnValue()) {
            return;
        }

        BlockPos soilPos = canePos.below();
        if (!level.getBlockState(soilPos).is(BlockTags.SUPPORTS_SUGAR_CANE)) {
            return;
        }

        for (int x = -UNDERGROUND_WATER_RANGE; x <= UNDERGROUND_WATER_RANGE; x++) {
            for (int z = -UNDERGROUND_WATER_RANGE; z <= UNDERGROUND_WATER_RANGE; z++) {
                BlockPos undergroundPos = soilPos.offset(x, -1, z);
                if (level.getFluidState(undergroundPos).is(FluidTags.WATER)) {
                    callback.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
