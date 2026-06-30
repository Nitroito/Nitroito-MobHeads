package pt.nitroito.mobheads.mixin;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.MobHeadsConfig;
import pt.nitroito.mobheads.MobHeadsData;
import pt.nitroito.mobheads.utils.MobHeadsRandom;


@Mixin(AbstractCubeMob.class)
public class AbstractCubeMobMixin {
    @Unique private static String slimeColor = "none";
    @Unique private Entity self(){return (Entity)(Object)this;}

    @Inject(method="finalizeSpawn", at=@At("TAIL"))
    private void finalizeSpawn(final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (this.self() instanceof SulfurCube) return;
        if (MobHeadsConfig.canSpawnColoredSlime()) {
            slimeColor = MobHeadsRandom.nextColor().getName();
        } else slimeColor = "none";
        this.setEntityDataColor(slimeColor);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        if (this.self() instanceof SulfurCube) return;
        builder.define(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR, slimeColor);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        if (this.self() instanceof SulfurCube) return;
        slimeColor = this.getEntityDataColor();
        if (DyeColor.byName(slimeColor, null) != null) {
            valueOutput.putString("Color", slimeColor);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        if (this.self() instanceof SulfurCube) return;
        slimeColor = valueInput.getStringOr("Color", "none");
        this.setEntityDataColor(slimeColor);
    }

    @Unique private String getEntityDataColor() {
        return this.self().getEntityData().get(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR);
    }

    @Unique private void setEntityDataColor(String color) {
        this.self().getEntityData().set(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR, color);
    }
}
