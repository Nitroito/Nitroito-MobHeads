package pt.nitroito.mobheads.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.utils.MobHeadsRandom;
import pt.nitroito.mobheads.MobHeadsConfig;

@Mixin(Sheep.class)
public abstract class SheepMixin {
    @Shadow public abstract void setColor(DyeColor dyeColor);
    @Unique private Entity asEntity() {return (Entity)(Object)this;}

    @Inject(method="finalizeSpawn", at=@At("TAIL"))
    private void finalizeSpawn(ServerLevelAccessor level,DifficultyInstance difficulty,EntitySpawnReason reason,SpawnGroupData data,CallbackInfoReturnable<SpawnGroupData> cir) {
        if (MobHeadsConfig.canSpawnJebSheep()){
           this.asEntity().setCustomName(Component.literal("jeb_"));
        }else if (MobHeadsConfig.canSpawnColoredSheep()){
            this.setColor(MobHeadsRandom.nextColor());
        }
    }
}
