package pt.nitroito.mobheads.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.InteractionHand;
import pt.nitroito.mobheads.MobHeadsNetwork;

public record SwingPlayerHandPacket(InteractionHand hand) implements CustomPacketPayload {

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return MobHeadsNetwork.SWUING_PLAYER_HANDE_PACKET_ID;
    }

    public static final StreamCodec<ByteBuf, SwingPlayerHandPacket> CODEC = new StreamCodec<>() {
        @Override
        public SwingPlayerHandPacket decode(ByteBuf buf) {
            int index = buf.readInt();
            return new SwingPlayerHandPacket(InteractionHand.values()[index]);
        }
        @Override
        public void encode(ByteBuf buf, SwingPlayerHandPacket packet) {
            buf.writeInt(packet.hand().ordinal());
        }
    };
}
