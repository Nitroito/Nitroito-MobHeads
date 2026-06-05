package pt.nitroito.mobheads.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.DyeColor;
import java.util.Random;

public class MobHeadsRandom {
        private static Random randomMachine;
        private static DyeColor[] dyeColors;

        public static void initialize(MinecraftServer server){
            long worldSeed = server.overworld().getRandom().nextLong();
            long timeSeed = System.nanoTime();
            randomMachine = new Random(worldSeed ^ timeSeed);
            dyeColors = DyeColor.values();
        }

        public static float nextFloat(){
            return randomMachine.nextFloat();
        }

        public static int nextInt(int bound){
            return randomMachine.nextInt(bound);
        }

        public static DyeColor nextColor(){
            return dyeColors[randomMachine.nextInt(dyeColors.length)];
        }
}
