package yuma140902.uptodatemod.world.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import yuma140902.uptodatemod.ModUpToDateMod;

public class WorldSavedDataDifficulty extends WorldSavedData {
	private static final String DATA_NAME = ModUpToDateMod.MOD_ID + "_Difficulty";
	
	private static final String NBT_KEY_DIFFICULTY_ID = "DifficultyID";
	
	private int difficultyId = 2;
	
	public WorldSavedDataDifficulty() {
		super(DATA_NAME);
	}
	
	public WorldSavedDataDifficulty(String s) {
		super(s);
	}
	
	public static WorldSavedDataDifficulty getFromWorld(World world) {
		MapStorage storage = world.mapStorage;
		WorldSavedDataDifficulty instance = (WorldSavedDataDifficulty) storage.loadData(WorldSavedDataDifficulty.class, DATA_NAME);
		
		if(instance == null) {
			instance = new WorldSavedDataDifficulty();
			storage.setData(DATA_NAME, instance);
		}
		
		return instance;
	}
	
	public EnumDifficulty getDifficulty() {
		return EnumDifficulty.getDifficultyEnum(difficultyId);
	}
	
	public int getDifficultyId() {
		return this.difficultyId;
	}
	
	public void setDifficulty(EnumDifficulty difficulty) {
		this.difficultyId = difficulty.getDifficultyId();
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.difficultyId = nbt.getInteger(NBT_KEY_DIFFICULTY_ID);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(NBT_KEY_DIFFICULTY_ID, difficultyId);
	}
}
