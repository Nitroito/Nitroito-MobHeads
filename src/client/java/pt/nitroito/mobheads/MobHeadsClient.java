package pt.nitroito.mobheads;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MobHeadsClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MobHeads.NAMESPACE);

	@Override
	public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(MobHeadsNetwork.SWING_PLAYER_HANDE_PACKET_ID, (packet, context) -> {
            LocalPlayer player = context.client().player;
            context.client().execute(() -> {
                if (player!=null) Objects.requireNonNull(player).swing(packet.hand());
            });
        });
	}
}
