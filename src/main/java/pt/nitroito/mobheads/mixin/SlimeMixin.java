package pt.nitroito.mobheads.mixin;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.MobHeadsData;
import pt.nitroito.mobheads.utils.MobHeadsRandom;
import pt.nitroito.mobheads.MobHeadsConfig;

@Mixin(Slime.class)
public abstract class SlimeMixin{
    @Unique private static String slimeColor = "none";
    @Unique private Entity asEntity() {return (Entity)(Object)this;}

    @Inject(method="finalizeSpawn", at=@At("TAIL"))
    private void finalizeSpawn(ServerLevelAccessor level,DifficultyInstance difficulty,EntitySpawnReason reason,SpawnGroupData data,CallbackInfoReturnable<SpawnGroupData> cir) {
        if (MobHeadsConfig.canSpawnColoredSlime()){
            slimeColor = MobHeadsRandom.nextColor().getName();
        }else slimeColor = "none";
        this.setEntityDataColor(slimeColor);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR, slimeColor);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData(ValueOutput  valueOutput, CallbackInfo ci) {
        slimeColor = this.getEntityDataColor();
        if (DyeColor.byName(slimeColor,null)!=null){
            valueOutput.putString("Color", slimeColor);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        slimeColor = valueInput.getStringOr("Color","none");
        this.setEntityDataColor(slimeColor);
    }

    @Unique private String getEntityDataColor() {
        return this.asEntity().getEntityData().get(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR);
    }

    @Unique private void setEntityDataColor(String color) {
        this.asEntity().getEntityData().set(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR, color);
    }
}
