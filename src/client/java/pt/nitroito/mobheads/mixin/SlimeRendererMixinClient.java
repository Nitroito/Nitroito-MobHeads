package pt.nitroito.mobheads.mixin;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.nitroito.mobheads.MobHeads;
import pt.nitroito.mobheads.MobHeadsData;


@Mixin(SlimeRenderer.class)
public abstract class SlimeRendererMixinClient {
    @Mutable @Shadow @Final public static Identifier SLIME_LOCATION;
    //@Unique private final RenderStateDataKey<String> RENDER_STATE_SLIME_COLOR = RenderStateDataKey.create();

    //@Inject(method="getTextureLocation(Lnet/minecraft/client/renderer/entity/state/SlimeRenderState;)Lnet/minecraft/resources/Identifier;", at=@At("HEAD"), cancellable = true)
    @Inject(method="getTextureLocation", at=@At("HEAD"), cancellable = true)
    public void getTextureLocation(SlimeRenderState slimeRenderState, CallbackInfoReturnable<Identifier> cir) {
        String slimeColor = slimeRenderState.getData(MobHeadsData.RENDER_STATE_SLIME_COLOR);
        if (slimeColor==null || slimeColor.equals("none")){
            SLIME_LOCATION = Identifier.withDefaultNamespace("textures/entity/slime/slime.png");
            cir.setReturnValue(SLIME_LOCATION);
        }else {
            SLIME_LOCATION = Identifier.fromNamespaceAndPath(MobHeads.MOD_ID, "textures/entity/slime/"+slimeColor+"_slime.png");
            cir.setReturnValue(SLIME_LOCATION);
        }
    }
}
