package yuma140902.uptodatemod;

import java.io.File;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.proxy.CommonProxy;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.UpdateChecker;
import yuma140902.uptodatemod.worldgen.MyMinableGenerator;

@Mod(modid = ModUpToDateMod.MOD_ID, useMetadata = true)
public class ModUpToDateMod {
	@Mod.Metadata
	public static ModMetadata modMetadata;
	
	@Mod.Instance
	public static ModUpToDateMod INSTANCE;
	
	@SidedProxy(clientSide = Stat.PROXY_CLIENT, serverSide = Stat.PROXY_SERVER)
	public static CommonProxy proxy;
	
	public static final String MOD_ID = "uptodate";
	public static final String MOD_NAME = "UpToDateMod";
	public static final String MINECRAFT_VERSION = "1.7.10";
	public static final String MOD_VERSION = "1.0.2";
	public static final String MOD_VERSIONS_TSV_URL = "https://raw.githubusercontent.com/yuma140902/UpdateJSON_Forge/master/UpToDateModVersions.tsv";
	public static final String CONFIG_FILE_NAME = "config\\" + MOD_NAME + ".cfg";
	
	public boolean config_worldGen_genStones;
	public boolean config_recipeRemove_oldFenceRecipe;
	public boolean config_enable_observer;
	
	private void loadModMetadata(ModMetadata modMetadata) {
		modMetadata.modId = MOD_ID;
		modMetadata.name = MOD_NAME;
		modMetadata.version = MOD_VERSION;
		modMetadata.authorList.add("yuma140902");
		modMetadata.description = "1.7.10にマイクラ1.8以降のバニラの要素を追加するMODです";
		modMetadata.url = "https://minecraft.curseforge.com/projects/uptodatemod";
		modMetadata.autogenerated = false;
	}
	
	private void loadConfig() {
		Configuration cfg = new Configuration(new File(CONFIG_FILE_NAME));
		try {
			cfg.load();
			config_worldGen_genStones = cfg.getBoolean("genStones", "worldgen", true, "Generate Granite, Diorite, Andesite in Overworld or not | 花崗岩、閃緑岩、安山岩をワールドに生成するか否か");
			config_recipeRemove_oldFenceRecipe = cfg.getBoolean("removeOldFenceRecipe", "recipe", false, "Delete the recipe from 6 sticks to 2 fences | 棒6本からフェンス2個を作るレシピを削除するかどうか(木材4つと棒2本からフェンスを作るレシピは、この設定に関わらず常に追加されます)");
			config_enable_observer = cfg.getBoolean("enableObserver", "experimental", false, "Enable observer(note: Observer has bugs) | オブザーバーを有効にするか否か【オブザーバーは未実装機能・バグ多数につき無効にしておくことを推奨】");
			EntityModBoatBase.boatCrashWhenCollide = cfg.getBoolean("boatCrashWhenCollide", "entity", false, "Boat added by this mod will crash when collision | このMODが追加するボートが、衝突時に壊れるかどうか(バニラのボートは衝突時に壊れる)");
			UpdateChecker.INSTANCE.config_updateChannel = cfg.getString("updateChannel", "misc", UpdateChecker.INSTANCE.config_updateChannel, "Channel of update checking| アップデートのチャンネル", new String[] {UpdateChecker.RECOMMENDED_STR, UpdateChecker.LATEST_STR});
			UpdateChecker.INSTANCE.config_doCheckUpdate = cfg.getBoolean("doUpdateChecking", "misc", UpdateChecker.INSTANCE.config_doCheckUpdate, "If true, the mod will check for updates automatically | アップデートを自動で確認するかどうか");
		}
		finally {
			cfg.save();
		}
	}
	
	private void tweakVanilla() {
		Items.wooden_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);
		BlockTrapDoor.disableValidation = true;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		loadModMetadata(modMetadata);
		loadConfig();
		try {
			UpdateChecker.INSTANCE.checkForUpdates();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(UpdateChecker.INSTANCE.hasNewVersionAvailable() ? "UpToDateMod: There is a new version available. - v" + UpdateChecker.INSTANCE.availableNewVersion + ". Visit " + UpdateChecker.INSTANCE.getNewVersionUrl() : "UpToDateMod is now up-to-date.");
		
		tweakVanilla();
		MyBlocks.register();
		MyItems.register();
		MyTileEntities.register();
		MyGuis.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Recipes.removeVanillaRecipes();
		Recipes.register();
		proxy.registerEntities();
		proxy.registerRenderers();
		MyMinableGenerator.Config stoneConfig = new MyMinableGenerator.Config(config_worldGen_genStones, 33, 10, 0, 80);
		
		WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_GRANITE, stoneConfig);
		WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_DIORITE, stoneConfig);
		WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_ANDESITE, stoneConfig);
		WorldGenerators.register();
		
		proxy.registerEventHandlers();
	}
}
