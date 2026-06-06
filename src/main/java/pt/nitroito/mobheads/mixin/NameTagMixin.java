package pt.nitroito.mobheads.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.NameTagItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.MobHeadsConfig;

import java.util.Optional;

@Mixin(NameTagItem.class)
public abstract class NameTagMixin {

    @Inject(method = "interactLivingEntity",at = @At("HEAD"), cancellable = true)
   	public void interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!player.level().isClientSide() && livingEntity.isAlive() && itemStack.getCustomName()!=null) {
            String customName = itemStack.getCustomName().getString();
            if ((livingEntity instanceof Sheep) && MobHeadsConfig.spawnJebSheep && customName.equals("jeb_")) {
                cir.setReturnValue(InteractionResult.SUCCESS);
                cir.cancel();
            }
            if ((livingEntity instanceof Rabbit rabbit)) {
                if (rabbit.getVariant()==Rabbit.Variant.WHITE_SPLOTCHED && !MobHeadsConfig.spawnToastRabbit && customName.equals("Toast")) {
                    itemStack.shrink(1);
                    rabbit.getEntityData().set(Entity.DATA_CUSTOM_NAME,Optional.of(Component.literal("Toast")));
                    rabbit.playSound(SoundEvents.RABBIT_AMBIENT);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                    cir.cancel();
                } else if (rabbit.getVariant()==Rabbit.Variant.WHITE && !MobHeadsConfig.spawnKillerRabbit && customName.equals("Killer Bunny")) {
                    itemStack.shrink(1);
                    rabbit.setVariant(Rabbit.Variant.EVIL);
                    rabbit.playSound(SoundEvents.RABBIT_ATTACK);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                    cir.cancel();
                }else if(customName.equals("Killer Bunny")){
                    cir.setReturnValue(InteractionResult.SUCCESS);
                    cir.cancel();
                }
            }
        }
    }
}
