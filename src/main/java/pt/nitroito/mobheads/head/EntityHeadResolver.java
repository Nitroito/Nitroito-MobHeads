package pt.nitroito.mobheads.head;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.chicken.ChickenVariants;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.cow.CowVariants;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.entity.animal.equine.TraderLlama;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.feline.CatVariants;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.frog.FrogVariants;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilus;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilusVariant;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilusVariants;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.pig.PigVariants;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariants;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.DyeColor;
import pt.nitroito.mobheads.MobHeads;
import pt.nitroito.mobheads.MobHeadsData;
import java.util.Optional;


public class EntityHeadResolver {

    public static Optional<EntityHead> getHead(Entity entity) {
        return switch (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath()){
            case "axolotl" -> EntityHeadResolver.getAxolotlHead((Axolotl)entity);
            case "cat" -> EntityHeadResolver.getCatHead((Cat)entity);
            case "chicken" -> EntityHeadResolver.getChickenHead((Chicken)entity);
            case "copper_golem" -> EntityHeadResolver.getCopperGolemHead((CopperGolem)entity);
            case "cow" -> EntityHeadResolver.getCowHead((Cow)entity);
            case "creeper" -> EntityHeadResolver.getCreeperHead((Creeper)entity);
            case "fox" -> EntityHeadResolver.getFoxHead((Fox)entity);
            case "frog" -> EntityHeadResolver.getFrogHead((Frog)entity);
            case "goat" -> EntityHeadResolver.getGoatHead((Goat)entity);
            case "horse" -> EntityHeadResolver.getHorseHead((Horse)entity);
            case "llama" -> EntityHeadResolver.getLlamaHead((Llama)entity);
            case "mooshroom" -> EntityHeadResolver.getMushroomCowHead((MushroomCow)entity);
            case "parrot" -> EntityHeadResolver.getParrotHead((Parrot)entity);
            case "pig" -> EntityHeadResolver.getPigHead((Pig)entity);
            case "rabbit" -> EntityHeadResolver.getRabbitHead((Rabbit)entity);
            case "sheep" -> EntityHeadResolver.getSheepHead((Sheep)entity);
            case "shulker" -> EntityHeadResolver.getShulkerHead((Shulker)entity);
            case "slime" -> EntityHeadResolver.getSlimeHead((Slime)entity);
            case "snow_golem" -> EntityHeadResolver.getSnowGolemHead((SnowGolem)entity);
            case "strider" -> EntityHeadResolver.getStriderHead((Strider)entity);
            case "trader_llama" -> EntityHeadResolver.getTraderLlamaHead((TraderLlama)entity);
            case "wolf" -> EntityHeadResolver.getWolfHead((Wolf)entity);
            case "villager" -> EntityHeadResolver.getVillagerHead((Villager)entity);
            case "zombie_villager" -> EntityHeadResolver.getZombieVillagerHead((ZombieVillager)entity);
            case "zombie_nautilus" -> EntityHeadResolver.getZombieNautilusHead((ZombieNautilus)entity);
            default -> EntityHeadCollection.get(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath().replaceAll(" ","_").toLowerCase());
        };
    }

    public static Optional<EntityHead> getAxolotlHead(Axolotl axolotl) {
        return switch (axolotl.getVariant()){
            case BLUE -> EntityHeadCollection.get("blue_axolotl");
            case CYAN -> EntityHeadCollection.get("cyan_axolotl");
            case GOLD -> EntityHeadCollection.get("gold_axolotl");
            case LUCY -> EntityHeadCollection.get("lucy_axolotl");
            case WILD -> EntityHeadCollection.get("wild_axolotl");
        };
    }

    public static Optional<EntityHead> getCatHead(Cat cat) {
        Holder<CatVariant> catVariant = cat.getVariant();
        if (catVariant.is(CatVariants.ALL_BLACK)) return EntityHeadCollection.get("black_cat");
        if (catVariant.is(CatVariants.BLACK)) return EntityHeadCollection.get("tuxedo_cat");
        if (catVariant.is(CatVariants.BRITISH_SHORTHAIR)) return EntityHeadCollection.get("british_shorthair_cat");
        if (catVariant.is(CatVariants.CALICO)) return EntityHeadCollection.get("calico_cat");
        if (catVariant.is(CatVariants.JELLIE)) return EntityHeadCollection.get("jellie_cat");
        if (catVariant.is(CatVariants.PERSIAN)) return EntityHeadCollection.get("persian_cat");
        if (catVariant.is(CatVariants.RAGDOLL)) return EntityHeadCollection.get("ragdoll_cat");
        if (catVariant.is(CatVariants.RED)) return EntityHeadCollection.get("ginger_cat");
        if (catVariant.is(CatVariants.SIAMESE)) return EntityHeadCollection.get("siamese_cat");
        if (catVariant.is(CatVariants.TABBY)) return EntityHeadCollection.get("tabby_cat");
        if (catVariant.is(CatVariants.WHITE)) return EntityHeadCollection.get("white_cat");
        return Optional.empty();
    }

    public static Optional<EntityHead> getChickenHead(Chicken chicken) {
        Holder<ChickenVariant> chickenVariant = chicken.getVariant();
        if (chickenVariant.is(ChickenVariants.TEMPERATE)) return EntityHeadCollection.get("temperate_chicken");
        if (chickenVariant.is(ChickenVariants.COLD)) return EntityHeadCollection.get("cold_chicken");
        if (chickenVariant.is(ChickenVariants.WARM)) return EntityHeadCollection.get("warm_chicken");
        return Optional.empty();
    }

    public static Optional<EntityHead> getCopperGolemHead(CopperGolem copperGolem) {
        return switch (copperGolem.getWeatherState()){
            case UNAFFECTED -> EntityHeadCollection.get("copper_golem");
            case EXPOSED -> EntityHeadCollection.get("exposed_copper_golem");
            case WEATHERED -> EntityHeadCollection.get("weathered_copper_golem");
            case OXIDIZED -> EntityHeadCollection.get("oxidized_copper_golem");
        };
    }

    public static Optional<EntityHead> getCowHead(Cow cow) {
        Holder<CowVariant> cowVariant = cow.getVariant();
        if (cowVariant.is(CowVariants.TEMPERATE)) return EntityHeadCollection.get("temperate_cow");
        if (cowVariant.is(CowVariants.COLD)) return EntityHeadCollection.get("cold_cow");
        if (cowVariant.is(CowVariants.WARM)) return EntityHeadCollection.get("warm_cow");
        return Optional.empty();
    }

    public static Optional<EntityHead> getCreeperHead(Creeper creeper) {
        return creeper.isPowered() ? EntityHeadCollection.get("charged_creeper") : Optional.empty();
    }

    public static Optional<EntityHead> getFoxHead(Fox fox) {
        return switch (fox.getVariant()) {
            case RED -> EntityHeadCollection.get("red_fox");
            case SNOW -> EntityHeadCollection.get("white_fox");
        };
    }

    public static Optional<EntityHead> getFrogHead(Frog frog) {
        Holder<FrogVariant> frogVariant = frog.getVariant();
        if (frogVariant.is(FrogVariants.TEMPERATE)) return EntityHeadCollection.get("temperate_frog");
        if (frogVariant.is(FrogVariants.COLD)) return EntityHeadCollection.get("cold_frog");
        if (frogVariant.is(FrogVariants.WARM)) return EntityHeadCollection.get("warm_frog");
        return Optional.empty();
    }

    public static Optional<EntityHead> getGoatHead(Goat goat) {
        if (goat.isScreamingGoat()){
            if (goat.hasLeftHorn() && goat.hasRightHorn()) return EntityHeadCollection.get("screaming_goat");
            if (goat.hasLeftHorn()) return EntityHeadCollection.get("left_horned_screaming_goat");
            if (goat.hasRightHorn()) return EntityHeadCollection.get("right_horned_screaming_goat");
            return EntityHeadCollection.get("hornless_screaming_goat");
        }else{
            if (goat.hasLeftHorn() && goat.hasRightHorn()) return EntityHeadCollection.get("goat");
            if (goat.hasLeftHorn()) return EntityHeadCollection.get("left_horned_goat");
            if (goat.hasRightHorn()) return EntityHeadCollection.get("right_horned_goat");
            return EntityHeadCollection.get("hornless_goat");
        }
    }

    public static Optional<EntityHead> getHorseHead(Horse horse) {
        return switch (horse.getVariant()) {
            case BLACK -> EntityHeadCollection.get("black_horse");
            case BROWN -> EntityHeadCollection.get("brown_horse");
            case CHESTNUT -> EntityHeadCollection.get("chestnut_horse");
            case CREAMY -> EntityHeadCollection.get("creamy_horse");
            case DARK_BROWN -> EntityHeadCollection.get("dark_brown_horse");
            case GRAY -> EntityHeadCollection.get("gray_horse");
            case WHITE -> EntityHeadCollection.get("white_horse");
        };
    }

    public static Optional<EntityHead> getLlamaHead(Llama llama) {
        return switch (llama.getVariant()){
            case BROWN -> EntityHeadCollection.get("brown_llama");
            case CREAMY -> EntityHeadCollection.get("creamy_llama");
            case GRAY -> EntityHeadCollection.get("gray_llama");
            case WHITE -> EntityHeadCollection.get("white_llama");
        };
    }

    public static Optional<EntityHead> getMushroomCowHead(MushroomCow cow) {
        return switch (cow.getVariant()) {
            case BROWN -> EntityHeadCollection.get("brown_mooshroom");
            case RED -> EntityHeadCollection.get("red_mooshroom");
        };
    }

    public static Optional<EntityHead> getParrotHead(Parrot parrot) {
        return switch (parrot.getVariant()) {
            case BLUE -> EntityHeadCollection.get("blue_parrot");
            case GRAY -> EntityHeadCollection.get("gray_parrot");
            case GREEN -> EntityHeadCollection.get("green_parrot");
            case RED_BLUE -> EntityHeadCollection.get("red_parrot");
            case YELLOW_BLUE -> EntityHeadCollection.get("cyan_parrot");
        };
    }

    public static Optional<EntityHead> getPigHead(Pig pig) {
        Holder<PigVariant> pigVariant = pig.getVariant();
        if (pigVariant.is(PigVariants.TEMPERATE)) return EntityHeadCollection.get("temperate_pig");
        if (pigVariant.is(PigVariants.COLD)) return EntityHeadCollection.get("cold_pig");
        if (pigVariant.is(PigVariants.WARM)) return EntityHeadCollection.get("warm_pig");
        return Optional.empty();
    }

    public static Optional<EntityHead> getRabbitHead(Rabbit rabbit) {
        return switch (rabbit.getVariant()) {
            case BLACK -> EntityHeadCollection.get("black_rabbit");
            case BROWN -> EntityHeadCollection.get("brown_rabbit");
            case GOLD -> EntityHeadCollection.get("gold_rabbit");
            case SALT -> EntityHeadCollection.get("salt_rabbit");
            case WHITE -> EntityHeadCollection.get("white_rabbit");
            case WHITE_SPLOTCHED -> EntityHeadCollection.get("white_splotched_rabbit");
            case EVIL -> EntityHeadCollection.get("killer_rabbit");
        };
    }

    public static Optional<EntityHead> getSheepHead(Sheep sheep) {
        if (sheep.getDisplayName().getString().equals(MobHeads.EASTER_EGG_JEB_SHEEP)) {
            return EntityHeadCollection.get("jeb_sheep");
        }
        return EntityHeadCollection.get(sheep.getColor().getName()+"_sheep");
    }

    public static Optional<EntityHead> getShulkerHead(Shulker shulker) {
        Optional<DyeColor> shulkerColor = shulker.getVariant();
        if (shulkerColor.isEmpty()) {
            return EntityHeadCollection.get("shulker");
        }
        return EntityHeadCollection.get(shulkerColor.get().getName()+"_shulker");
    }

    public static Optional<EntityHead> getSlimeHead(Slime slime) {
        String slimeColorName = slime.getEntityData().get(MobHeadsData.DATA_ACCESSOR_SLIME_COLOR);
        if (slimeColorName.isEmpty() || slimeColorName.equals("none")) {
            return EntityHeadCollection.get("slime");
        }
        return EntityHeadCollection.get(slimeColorName+"_slime");
    }

    public static Optional<EntityHead> getSnowGolemHead(SnowGolem snowGolem) {
        return snowGolem.hasPumpkin() ? EntityHeadCollection.get("snow_golem") : EntityHeadCollection.get("snowman");
    }

    public static Optional<EntityHead> getStriderHead(Strider strider) {
        return strider.isInLava() ? EntityHeadCollection.get("strider") : EntityHeadCollection.get("cold_strider");
    }

    public static Optional<EntityHead> getTraderLlamaHead(TraderLlama traderLlama) {
        return switch (traderLlama.getVariant()){
            case BROWN -> EntityHeadCollection.get("brown_trader_llama");
            case CREAMY -> EntityHeadCollection.get("creamy_trader_llama");
            case GRAY -> EntityHeadCollection.get("gray_trader_llama");
            case WHITE -> EntityHeadCollection.get("white_trader_llama");
        };
    }

    public static Optional<EntityHead> getWolfHead(Wolf wolf) {
        Holder<WolfVariant> wolfVariant = wolf.getVariant();
        if (wolfVariant.is(WolfVariants.ASHEN)) return EntityHeadCollection.get("ashen_wolf");
        if (wolfVariant.is(WolfVariants.BLACK)) return EntityHeadCollection.get("black_wolf");
        if (wolfVariant.is(WolfVariants.CHESTNUT)) return EntityHeadCollection.get("chestnut_wolf");
        if (wolfVariant.is(WolfVariants.PALE)) return EntityHeadCollection.get("pale_wolf");
        if (wolfVariant.is(WolfVariants.RUSTY)) return EntityHeadCollection.get("rusty_wolf");
        if (wolfVariant.is(WolfVariants.SNOWY)) return EntityHeadCollection.get("snowy_wolf");
        if (wolfVariant.is(WolfVariants.SPOTTED)) return EntityHeadCollection.get("spotted_wolf");
        if (wolfVariant.is(WolfVariants.STRIPED)) return EntityHeadCollection.get("striped_wolf");
        if (wolfVariant.is(WolfVariants.WOODS)) return EntityHeadCollection.get("woods_wolf");
        return Optional.empty();
    }

    public static Optional<EntityHead> getVillagerHead(Villager villager) {
        VillagerData villagerData = villager.getVillagerData();
        Identifier villagerBiomeKey = villagerData.type().unwrapKey().orElseThrow().identifier();
        Identifier villagerJobKey = villagerData.profession().unwrapKey().orElseThrow().identifier();
        if (villagerData.profession().is(VillagerProfession.NONE)) {
            return EntityHeadCollection.get(villagerBiomeKey.getPath()+"_villager");
        }else{
            return EntityHeadCollection.get(villagerBiomeKey.getPath()+"_"+villagerJobKey.getPath());
        }
    }

    public static Optional<EntityHead> getZombieVillagerHead(ZombieVillager zombieVillager) {
        VillagerData zombieVillagerData = zombieVillager.getVillagerData();
        Identifier zombieVillagerBiomeKey = zombieVillagerData.type().unwrapKey().orElseThrow().identifier();
        Identifier zombieVillagerJobKey = zombieVillagerData.profession().unwrapKey().orElseThrow().identifier();
        if (zombieVillagerData.profession().is(VillagerProfession.NONE)) {
            return EntityHeadCollection.get(zombieVillagerBiomeKey.getPath()+"_zombie_villager");
        }else{
            return EntityHeadCollection.get(zombieVillagerBiomeKey.getPath()+"_zombie_"+zombieVillagerJobKey.getPath());
        }
    }

    public static Optional<EntityHead> getZombieNautilusHead(ZombieNautilus zombieNautilus) {
        Holder<ZombieNautilusVariant> zombieNautilusVariant = zombieNautilus.getVariant();
        return zombieNautilusVariant.is(ZombieNautilusVariants.WARM) ? EntityHeadCollection.get("zombie_nautilus_coral") : EntityHeadCollection.get("zombie_nautilus");
    }
}
