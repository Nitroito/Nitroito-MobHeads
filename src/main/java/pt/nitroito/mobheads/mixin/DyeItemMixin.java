package pt.nitroito.mobheads.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.cubemob.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.MobHeadsData;
import pt.nitroito.mobheads.MobHeadsConfig;
import pt.nitroito.mobheads.MobHeadsNetwork;
import java.util.Optional;


@Mixin(DyeItem.class)
public abstract class DyeItemMixin {

    @Inject(method="interactLivingEntity", at = @At("HEAD"), cancellable = true)
	public void interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        DyeColor dyeColor = itemStack.get(DataComponents.DYE);
        if (livingEntity.isAlive() && !player.level().isClientSide() && dyeColor!=null) {
            if ((livingEntity instanceof Sheep) && MobHeadsConfig.spawnColoredSheep) {
                cir.setReturnValue(InteractionResult.FAIL);
                cir.cancel();
            }
            if ((livingEntity instanceof Shulker shulker) && !MobHeadsConfig.spawnColoredShulkers) {
                DyeColor shulkerColor = shulker.getColor();
                if (shulkerColor==null || shulkerColor!=dyeColor) {
                    itemStack.shrink(1);
                    shulker.setVariant(Optional.of(dyeColor));
                    shulker.playSound(SoundEvents.DYE_USE);
                    MobHeadsNetwork.sendSwingPlayerHandPacket(player, interactionHand);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                    cir.cancel();
                }
            }
            if ((livingEntity instanceof Slime slime) && !MobHeadsConfig.spawnColoredSlimes) {
                DyeColor slimeColor = DyeColor.byName(slime.getEntityData().get(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR), null);
                if (slimeColor==null || slimeColor!=dyeColor) {
                    itemStack.shrink(1);
                    slime.getEntityData().set(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR, dyeColor.getName());
                    player.swing(InteractionHand.MAIN_HAND);
                    slime.playSound(SoundEvents.DYE_USE);
                    MobHeadsNetwork.sendSwingPlayerHandPacket(player, interactionHand);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                    cir.cancel();
                }
            }
        }
    }
}
