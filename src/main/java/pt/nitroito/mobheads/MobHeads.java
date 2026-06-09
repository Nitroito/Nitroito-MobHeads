package pt.nitroito.mobheads;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.nitroito.mobheads.head.EntityHead;
import pt.nitroito.mobheads.head.EntityHeadCollection;
import pt.nitroito.mobheads.head.EntityHeadResolver;
import pt.nitroito.mobheads.utils.MobHeadsRandom;
import java.util.Optional;


public class MobHeads implements ModInitializer {
    public static final boolean DEVELOPMENT_MODE = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static final Logger LOGGER = LoggerFactory.getLogger(MobHeads.MOD_ID);
	public static final String MOD_ID = "nitroito_mobheads";
    public static final String EASTER_EGG_JEB_SHEEP = "jeb_";
    public static final String EASTER_EGG_TOAST_RABBIT = "Toast";
    public static final String EASTER_EGG_KILLER_RABBIT = "Caerbannog";

	@Override
	public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(MobHeadsRandom::initialize);
        MobHeadsData.register();
        MobHeadsConfig.register();
        MobHeadsNetwork.register();
        EntityHeadCollection.registerHeads();


        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity entity, DamageSource damageSource) -> {
            if (entity.level().isClientSide() || entity.isBaby() || !(damageSource.getDirectEntity() instanceof Player player))
                return;

            Optional<EntityHead> entityHead = EntityHeadResolver.getHead(entity);
            if (entityHead.isEmpty() || entityHead.get().getDropChance() == 0)
                return;

            Registry<Enchantment> enchantments = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            Holder<Enchantment> enchantment = enchantments.getOrThrow(Enchantments.LOOTING);
            float extraChance = Math.max(EnchantmentHelper.getEnchantmentLevel(enchantment, player), 0) * 0.05F;
            if (MobHeadsRandom.nextFloat() <= (entityHead.get().getDropChance() + extraChance + (DEVELOPMENT_MODE?1:0))) {
                entity.drop(entityHead.get().getHeadStack(1), false, false);
            }
        });
    }
}
