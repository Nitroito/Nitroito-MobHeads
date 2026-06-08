package pt.nitroito.mobheads.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.MobHeads;
import pt.nitroito.mobheads.MobHeadsConfig;
import java.util.Optional;


@Mixin(Rabbit.class)
public abstract class RabbitMixin {
    @Shadow public abstract void setVariant(Rabbit.Variant variant);
    @Shadow public abstract Rabbit.Variant getVariant();
    @Unique private Entity asEntity() {return (Entity)(Object)this;}

    @Inject(method="finalizeSpawn", at=@At("TAIL"))
    private void finalizeSpawn(ServerLevelAccessor level,DifficultyInstance difficulty,EntitySpawnReason reason,SpawnGroupData data,CallbackInfoReturnable<SpawnGroupData> cir) {
        if(getVariant()==Rabbit.Variant.WHITE && MobHeadsConfig.canSpawnKillerRabbit()){
            this.setVariant(Rabbit.Variant.EVIL);
        }
        if(getVariant()==Rabbit.Variant.WHITE_SPLOTCHED && MobHeadsConfig.canSpawnToastRabbit()){
            this.asEntity().getEntityData().set(Entity.DATA_CUSTOM_NAME,Optional.of(Component.literal(MobHeads.EASTER_EGG_TOAST_RABBIT)));
        }
    }
}
