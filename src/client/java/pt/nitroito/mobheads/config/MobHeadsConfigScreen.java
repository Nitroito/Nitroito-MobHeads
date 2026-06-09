package pt.nitroito.mobheads.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import pt.nitroito.mobheads.MobHeads;
import pt.nitroito.mobheads.MobHeadsConfig;


public class MobHeadsConfigScreen {

    public static Screen create(Screen parent) {
        MobHeadsConfigFile config =AutoConfig.getConfigHolder(MobHeadsConfigFile.class).getConfig();
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.translatable(MobHeads.MOD_ID +".config.title"));
        ConfigEntryBuilder entry = builder.entryBuilder();

        //==============================================================================================================
        // GENERAL SETTINGS
        //==============================================================================================================
        ConfigCategory toggles = builder.getOrCreateCategory(Component.translatable(MobHeads.MOD_ID +".config.general"));
        toggles.addEntry(entry.startBooleanToggle(Component.translatable(MobHeads.MOD_ID + ".config.general.spawn_colored_sheep"), config.spawnColoredSheep)
            .setTooltip(Component.translatable(MobHeads.MOD_ID + ".config.general.tooltip.dye_usage"))
            .setDefaultValue(false)
            .setSaveConsumer(value -> config.spawnColoredSheep = value)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.translatable(MobHeads.MOD_ID +".config.general.spawn_colored_slimes"),config.spawnColoredSlimes)
            .setTooltip(Component.translatable(MobHeads.MOD_ID +".config.general.tooltip.dye_usage"))
            .setDefaultValue(false)
            .setSaveConsumer(value -> config.spawnColoredSlimes = value)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.translatable(MobHeads.MOD_ID +".config.general.spawn_colored_shulkers"),config.spawnColoredShulkers)
            .setTooltip(Component.translatable(MobHeads.MOD_ID +".config.general.tooltip.dye_usage"))
            .setDefaultValue(false)
            .setSaveConsumer(value -> config.spawnColoredShulkers = value)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.translatable(MobHeads.MOD_ID +".config.general.spawn_jeb_sheep"),config.spawnJebSheep)
            .setTooltip(Component.translatable(MobHeads.MOD_ID +".config.general.tooltip.name_tag_usage",MobHeads.EASTER_EGG_JEB_SHEEP))
            .setDefaultValue(false)
            .setSaveConsumer(value -> config.spawnJebSheep = value)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.translatable(MobHeads.MOD_ID +".config.general.spawn_killer_rabbit"),config.spawnToastRabbit)
            .setTooltip(Component.translatable(MobHeads.MOD_ID +".config.general.tooltip.name_tag_usage",MobHeads.EASTER_EGG_TOAST_RABBIT))
            .setDefaultValue(false)
            .setSaveConsumer(value -> config.spawnToastRabbit = value)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.translatable(MobHeads.MOD_ID +".config.general.spawn_killer_rabbit"),config.spawnKillerRabbit)
            .setTooltip(Component.translatable(MobHeads.MOD_ID +".config.general.tooltip.name_tag_usage",MobHeads.EASTER_EGG_KILLER_RABBIT))
            .setDefaultValue(false)
            .setSaveConsumer(value -> config.spawnKillerRabbit = value)
            .build());

        //==============================================================================================================
        // SPAWN CHANCES
        //==============================================================================================================
        ConfigCategory chances = builder.getOrCreateCategory(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances"));
        chances.addEntry(entry.startFloatField(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances.colored_sheep"),config.spawnColoredSheepChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setSaveConsumer(value -> config.spawnColoredSheepChance = value)
            .build());
        chances.addEntry(entry.startFloatField(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances.colored_slimes"),config.spawnColoredSlimesChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setSaveConsumer(value -> config.spawnColoredSlimesChance = value)
            .build());
        chances.addEntry(entry.startFloatField(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances.colored_shulkers"),config.spawnColoredShulkersChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setSaveConsumer(value -> config.spawnColoredShulkersChance = value)
            .build());
        chances.addEntry(entry.startFloatField(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances.jeb_sheep"),config.spawnKillerRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setSaveConsumer(value -> config.spawnKillerRabbitChance = value)
            .build());
        chances.addEntry(entry.startFloatField(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances.toast_rabbit"),config.spawnToastRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setSaveConsumer(value -> config.spawnToastRabbitChance = value)
            .build());
        chances.addEntry(entry.startFloatField(Component.translatable(MobHeads.MOD_ID +".config.spawn_chances.killer_rabbit"),config.spawnKillerRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setSaveConsumer(value -> config.spawnKillerRabbitChance = value)
            .build());

        //==============================================================================================================
        // SAVE CONFIG
        //==============================================================================================================
        builder.setSavingRunnable(() -> {
            AutoConfig.getConfigHolder(MobHeadsConfigFile.class).save();
            MobHeadsConfigFile cfg = AutoConfig.getConfigHolder(MobHeadsConfigFile.class).getConfig();
            MobHeadsConfig.loadConfigFile(cfg);
            MobHeadsConfigEvents.fire();
        });

        return builder.build();
    }
}
