package pt.nitroito.mobheads.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Unique private final Component KILLER_BUNNY = Component.translatable(Util.makeDescriptionId("entity",Identifier.withDefaultNamespace("killer_bunny")));
    @Unique private Entity self() {return (Entity)(Object)this;}

    @Inject(method = "setCustomName", at=@At("HEAD"), cancellable = true)
    public void setCustomName(Component component, CallbackInfo ci) {
        boolean isToastBunny = (this.self() instanceof Rabbit) && component!=null && component.getString().equals("Toast");
        boolean isKillerBunny = (this.self() instanceof Rabbit) && component!=null && component.getString().equals(KILLER_BUNNY.getString());
        if (isToastBunny || isKillerBunny) ci.cancel();
    }
}
