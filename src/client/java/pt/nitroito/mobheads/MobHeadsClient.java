package pt.nitroito.mobheads;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MobHeadsClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MobHeads.NAMESPACE);

	@Override
	public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(MobHeadsNetwork.SWUING_PLAYER_HANDE_PACKET_ID, (packet, context) -> {
            context.client().execute(() -> {
                //if (context.client().player!=null) context.client().player.swing(packet.hand());
                if (context.client().player!=null) Objects.requireNonNull(context.client().player).swing(packet.hand());
            });
        });
	}
}
