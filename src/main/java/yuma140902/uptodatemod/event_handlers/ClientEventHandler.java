package yuma140902.uptodatemod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.command.server.CommandMessageRaw;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import yuma140902.uptodatemod.util.UpdateChecker;
import yuma140902.uptodatemod.world.storage.WorldSavedDataDifficulty;

public class ClientEventHandler {
private ClientEventHandler() {}
	
	public static final ClientEventHandler INSTANCE = new ClientEventHandler();
	
	private boolean hasNotifiedAboutUpdate = false;
	
	@SubscribeEvent
	public void onWorldLoaded(WorldEvent.Load event) {
		updateNotify(event);
		changeDifficulty(event);
	}
	
	private void updateNotify(WorldEvent.Load event) {
		if(!event.world.isRemote || !UpdateChecker.INSTANCE.config_doCheckUpdate || hasNotifiedAboutUpdate) {
			return;
		}
		if(!UpdateChecker.INSTANCE.hasNewVersionAvailable()) {
			return;
		}
		IntegratedServer integratedServer = Minecraft.getMinecraft().getIntegratedServer();
		if(integratedServer == null) {
			return;
		}
		
		String msgRaw = StatCollector.translateToLocalFormatted("text.uptodate.update_notify", UpdateChecker.INSTANCE.availableNewVersion, UpdateChecker.INSTANCE.getNewVersionUrl());
		new CommandMessageRaw().processCommand(integratedServer, new String[] {"@a", msgRaw});
		
		hasNotifiedAboutUpdate = true;
	}
	
	@SubscribeEvent
	public void onWorldSaving(WorldEvent.Save event) {
		saveDifficulty(event);
	}
	
	private void changeDifficulty(WorldEvent.Load event) {
		if(!event.world.isRemote) return;
		
		World world = event.world;
		WorldSavedDataDifficulty difficultySavedData = WorldSavedDataDifficulty.getFromWorld(world);
		
		System.out.println("現在の難易度: " + world.difficultySetting);
		System.out.println("保存された難易度: " + difficultySavedData.getDifficulty());
		
		world.difficultySetting = difficultySavedData.getDifficulty();
		Minecraft.getMinecraft().gameSettings.setOptionValue(Options.DIFFICULTY, difficultySavedData.getDifficulty().getDifficultyId());
		Minecraft.getMinecraft().gameSettings.saveOptions();
	}
	
	private void saveDifficulty(WorldEvent.Save event) {
		if(!event.world.isRemote) return;
		
		World world = event.world;
		WorldSavedDataDifficulty difficultySavedData = WorldSavedDataDifficulty.getFromWorld(world);
		
		System.out.println("現在の難易度: " + world.difficultySetting);
		
		difficultySavedData.setDifficulty(world.difficultySetting);
	}
}
