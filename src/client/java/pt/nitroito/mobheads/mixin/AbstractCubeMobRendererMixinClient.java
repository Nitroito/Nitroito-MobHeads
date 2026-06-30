package pt.nitroito.mobheads.mixin;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.renderer.entity.AbstractCubeMobRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.monster.cubemob.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pt.nitroito.mobheads.MobHeadsData;


@Mixin(AbstractCubeMobRenderer.class)
public class AbstractCubeMobRendererMixinClient {
    //@Unique private final RenderStateDataKey<String> RENDER_STATE_SLIME_COLOR = RenderStateDataKey.create();

    //@Inject(method="extractRenderState(Lnet/minecraft/world/entity/monster/cubemob/AbstractCubeMob;Lnet/minecraft/client/renderer/entity/state/SlimeRenderState;F)V", at=@At("TAIL"))
    @Inject(method="extractRenderState", at=@At("TAIL"))
    private void extractRenderState(final AbstractCubeMob entity, final SlimeRenderState state, final float partialTicks, CallbackInfo ci) {
        if (entity instanceof Slime slime){
            String slimeColor = entity.getEntityData().get(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR);
            state.setData(MobHeadsData.RENDER_STATE_SLIME_COLOR,slimeColor);
        }
    }
}
