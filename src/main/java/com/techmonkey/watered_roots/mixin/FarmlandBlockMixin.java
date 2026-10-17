package com.techmonkey.watered_roots.mixin;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {
    private static final int UNDERGROUND_WATER_RANGE = 2;

    @Inject(method = "isNearWater", at = @At("RETURN"), cancellable = true)
    private static void wateredRoots$acceptUndergroundWater(
            LevelReader level,
            BlockPos farmlandPos,
            CallbackInfoReturnable<Boolean> callback
    ) {
        if (callback.getReturnValue()) {
            return;
        }

        for (int x = -UNDERGROUND_WATER_RANGE; x <= UNDERGROUND_WATER_RANGE; x++) {
            for (int z = -UNDERGROUND_WATER_RANGE; z <= UNDERGROUND_WATER_RANGE; z++) {
                BlockPos undergroundPos = farmlandPos.offset(x, -1, z);
                if (level.getFluidState(undergroundPos).is(FluidTags.WATER)) {
                    callback.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
