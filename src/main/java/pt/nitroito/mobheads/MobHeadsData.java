package pt.nitroito.mobheads;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;


public class MobHeadsData {
    public static EntityDataAccessor<String> DATA_ACCESSOR_SLIME_COLOR;
    public static DataComponentType<Identifier> DATA_COMPONENT_HEAD_ID = DataComponentType.<Identifier>builder().persistent(Identifier.CODEC).networkSynchronized(Identifier.STREAM_CODEC).build();
    public static RenderStateDataKey<String> RENDER_STATE_SLIME_COLOR = RenderStateDataKey.create();

    public static void register(){
        DATA_ACCESSOR_SLIME_COLOR = SynchedEntityData.defineId(AbstractCubeMob.class, EntityDataSerializers.STRING);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(MobHeads.MOD_ID,"head_id"), DATA_COMPONENT_HEAD_ID);
    }
}
