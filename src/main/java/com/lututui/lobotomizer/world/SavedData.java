package com.lututui.lobotomizer.world;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.lututui.lobotomizer.LobotomizerMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class SavedData extends WorldSavedData {
    public static final String CHICKEN_DATA = "twin_egg_chicken_data";
    public static final String SHEEP_DATA = "grassless_wool_sheep_data";
    public static final String SILENCED_DATA = "silenced_data";

    private Set<UUID> upgradedChickens;
    private Set<UUID> upgradedSheep;
    private Set<UUID> silencedEntities;

    public SavedData(String s) {
        super(s);
    }

    @Nullable
    public static SavedData get(World world) {
        if (world.isRemote) {
            return null;
        }

        final MapStorage storage = world.getMapStorage();
        assert storage != null;
        SavedData savedData = (SavedData) storage.getOrLoadData(SavedData.class, LobotomizerMod.MODID);

        if (savedData == null) {
            LobotomizerMod.logger.info("Creating new saved data");

            savedData = new SavedData(LobotomizerMod.MODID);
            storage.setData(LobotomizerMod.MODID, savedData);
        }

        return savedData;
    }

    public boolean hasSilencedEntities() {
        return !(this.silencedEntities == null || this.silencedEntities.isEmpty());
    }

    public boolean hasUpgradedChickens() {
        return !(this.upgradedChickens == null || this.upgradedChickens.isEmpty());
    }

    public boolean hasUpgradedSheep() {
        return !(this.upgradedSheep == null || this.upgradedSheep.isEmpty());
    }

    public void add(EntityChicken chicken) {
        if (this.upgradedChickens == null) {
            this.upgradedChickens = new HashSet<>();
        }

        this.upgradedChickens.add(chicken.getUniqueID());
        this.markDirty();
    }

    public void add(EntitySheep sheep) {
        if (upgradedSheep == null) {
            this.upgradedSheep = new HashSet<>();
        }

        this.upgradedSheep.add(sheep.getUniqueID());
        this.markDirty();
    }

    public void silence(EntityLiving entity) {
        if (this.silencedEntities == null) {
            this.silencedEntities = new HashSet<>();
        }

        this.silencedEntities.add(entity.getUniqueID());
        this.markDirty();
    }

    public boolean contains(EntityChicken chicken) {
        return this.upgradedChickens != null && this.upgradedChickens.contains(chicken.getUniqueID());
    }

    public boolean contains(EntitySheep sheep) {
        return this.upgradedSheep != null && this.upgradedSheep.contains(sheep.getUniqueID());
    }

    public boolean isSilenced(EntityLiving entity) {
        return this.silencedEntities != null && this.silencedEntities.contains(entity.getUniqueID());
    }

    public void remove(EntityChicken chicken) {
        if (this.upgradedChickens == null) {
            LobotomizerMod.logger.error("Tried to remove chicken but set is null");
            return;
        }

        if (this.upgradedChickens.remove(chicken.getUniqueID())) {
            this.markDirty();
            return;
        }

        LobotomizerMod.logger.error("Failed to remove chicken (UUID: " + chicken.getUniqueID() + ") from upgraded+ set");
    }

    public void remove(EntitySheep sheep) {
        if (this.upgradedSheep == null) {
            LobotomizerMod.logger.error("Tried to remove sheep but set is null");
            return;
        }

        if (this.upgradedSheep.remove(sheep.getUniqueID())) {
            this.markDirty();
            return;
        }

        LobotomizerMod.logger.error("Failed to remove sheep (UUID: " + sheep.getUniqueID() + ") from upgraded set");
    }

    public void unsilence(EntityLiving entity) {
        if (this.silencedEntities == null) {
            LobotomizerMod.logger.error("Tried to unsilence entity but set is null");
            return;
        }

        if (this.silencedEntities.remove(entity.getUniqueID())) {
            this.markDirty();
            return;
        }

        LobotomizerMod.logger.error("Failed to unsilence entity (UUID: " + entity.getUniqueID() + ")");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        final NBTTagList chickenList = nbt.getTagList(CHICKEN_DATA, 8);
        final NBTTagList sheepList = nbt.getTagList(SHEEP_DATA, 8);
        final NBTTagList silencedList = nbt.getTagList(SILENCED_DATA, 8);

        this.upgradedChickens = new HashSet<>();
        this.upgradedSheep = new HashSet<>();
        this.silencedEntities = new HashSet<>();

        for (int i = 0; i < chickenList.tagCount(); i++) {
            this.upgradedChickens.add(UUID.fromString(chickenList.getStringTagAt(i)));
        }

        for (int i = 0; i < sheepList.tagCount(); i++) {
            this.upgradedSheep.add(UUID.fromString(sheepList.getStringTagAt(i)));
        }

        for (int i = 0; i < silencedList.tagCount(); i++) {
            this.silencedEntities.add(UUID.fromString(silencedList.getStringTagAt(i)));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        final NBTTagList chickenList = new NBTTagList();
        final NBTTagList sheepList = new NBTTagList();
        final NBTTagList silencedList = new NBTTagList();

        for (final UUID uuid : this.upgradedChickens) {
            chickenList.appendTag(new NBTTagString(uuid.toString()));
        }

        for (final UUID uuid : this.upgradedSheep) {
            sheepList.appendTag(new NBTTagString(uuid.toString()));
        }

        for (final UUID uuid : this.silencedEntities) {
            silencedList.appendTag(new NBTTagString(uuid.toString()));
        }

        compound.setTag(CHICKEN_DATA, chickenList);
        compound.setTag(SHEEP_DATA, sheepList);
        compound.setTag(SILENCED_DATA, silencedList);

        LobotomizerMod.logger.info("Saving data");
        LobotomizerMod.logger.info(compound.toString());

        return compound;
    }
}
