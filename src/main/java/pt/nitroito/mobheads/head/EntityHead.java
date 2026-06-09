package pt.nitroito.mobheads.head;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ResolvableProfile;
import pt.nitroito.mobheads.MobHeads;
import pt.nitroito.mobheads.MobHeadsData;
import java.util.UUID;


public class EntityHead{
    private final Style HEAD_STYLE = Style.EMPTY.withColor(ChatFormatting.YELLOW).withItalic(false);
    private final Identifier key;
    private final String entityName;
    private final float dropChance;
    private final String noteblockSound;
    private final String texture;

    public EntityHead (String id, String entityName, float dropChance, String noteblockSound, String texture) {
        this.key = Identifier.fromNamespaceAndPath(MobHeads.MOD_ID, id);
        this.entityName = MobHeads.MOD_ID +".advancement."+entityName+".title";
        this.dropChance = dropChance;
        this.noteblockSound = noteblockSound;
        this.texture = texture;
    }

    public Identifier getKey() { return this.key; }
    public String getEntityName() { return this.entityName; }
    public float getDropChance() { return this.dropChance; }
    public String getNoteblockSound() { return this.noteblockSound; }
    public String getTexture() { return this.texture; }


    public ItemStack getHeadStack(int count) {
        return makeEntityHead(Identifier.withDefaultNamespace("player_head"),count);
    }

    private ItemStack makeEntityHead(Identifier identifier, int count) {
        ItemStack result = new ItemStack(BuiltInRegistries.ITEM.getValue(identifier), count);
        var customName = Component.translatable(this.getEntityName());
        result.set(DataComponents.RARITY, Rarity.UNCOMMON);
        result.set(DataComponents.CUSTOM_NAME, Component.translatable(MobHeads.MOD_ID +".head.display_name",customName).setStyle(HEAD_STYLE));
        result.set(DataComponents.NOTE_BLOCK_SOUND, Identifier.parse(this.getNoteblockSound()));
        result.set(MobHeadsData.DATA_COMPONENT_HEAD_ID, Identifier.parse(this.getKey().toString()));
        if (result.is(Items.PLAYER_HEAD)){
            result.set(DataComponents.PROFILE, makeTextureProfile(this.getTexture()));
        }
        return result;
    }

    private ResolvableProfile makeTextureProfile(String texture){
        UUID unknwonUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Multimap<String, Property> profileProperties = HashMultimap.create();
        profileProperties.put("textures", new Property("textures", texture));
        return ResolvableProfile.createResolved(new GameProfile(unknwonUUID, "NitroitoMobHeads", new PropertyMap(profileProperties)));
    }
}
