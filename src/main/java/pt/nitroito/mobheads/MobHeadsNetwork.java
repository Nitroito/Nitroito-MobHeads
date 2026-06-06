package pt.nitroito.mobheads;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import pt.nitroito.mobheads.network.SwingPlayerHandPacket;

public class MobHeadsNetwork {
    public static final CustomPacketPayload.Type<SwingPlayerHandPacket> SWUING_PLAYER_HANDE_PACKET_ID = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MobHeads.NAMESPACE, "player_swing_hand"));

    public static void register(){
        PayloadTypeRegistry.clientboundPlay().register(SWUING_PLAYER_HANDE_PACKET_ID, SwingPlayerHandPacket.CODEC);
    }

    public static void sendSwingPlayerHandPacket(Player player, InteractionHand hand){
        ServerPlayNetworking.send((ServerPlayer)player, new SwingPlayerHandPacket(hand));
    }
}
