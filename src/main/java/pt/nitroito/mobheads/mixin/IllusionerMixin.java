package pt.nitroito.mobheads.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Illusioner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public class IllusionerMixin {
    @Unique private Entity asEntity() {return (Entity)(Object)this;}

    @Inject(method="thunderHit", at=@At("HEAD"), cancellable=true)
	public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt, CallbackInfo ci) {
        if ((this.asEntity() instanceof Evoker evoker)){
            Illusioner illusioner = EntityTypes.ILLUSIONER.create(evoker.level(), EntitySpawnReason.CONVERSION);
            if (!(illusioner==null)){
                illusioner.setXRot(evoker.getXRot());
                illusioner.setYRot(evoker.getYRot());
                illusioner.setYHeadRot(evoker.getYHeadRot());
                illusioner.setCustomName(evoker.getCustomName());
                illusioner.setCustomNameVisible(evoker.isCustomNameVisible());
                illusioner.setPersistenceRequired();
                illusioner.setHealth(evoker.getHealth());
                illusioner.teleportTo((float)evoker.getX(),(float)evoker.getY(),(float)evoker.getZ());
                evoker.discard();
                serverLevel.addFreshEntity(illusioner);
                illusioner.playSound(SoundEvents.ILLUSIONER_PREPARE_MIRROR);
                illusioner.jumpFromGround();
            }
            ci.cancel();
        }
    }
}
