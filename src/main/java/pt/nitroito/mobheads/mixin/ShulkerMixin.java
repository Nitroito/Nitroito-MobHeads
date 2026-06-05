package pt.nitroito.mobheads.mixin;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.utils.MobHeadsRandom;
import pt.nitroito.mobheads.MobHeadsConfig;

import java.util.Optional;

@Mixin(Shulker.class)
public abstract class ShulkerMixin {
    @Shadow public abstract void setVariant(Optional<DyeColor> optional);

    @Inject(method="finalizeSpawn", at=@At("TAIL"))
    private void finalizeSpawn(ServerLevelAccessor level,DifficultyInstance difficulty,EntitySpawnReason reason,SpawnGroupData data,CallbackInfoReturnable<SpawnGroupData> cir) {
        if (MobHeadsConfig.canSpawnColoredShulker()){
            this.setVariant(Optional.of(MobHeadsRandom.nextColor()));
        }
    }
}
