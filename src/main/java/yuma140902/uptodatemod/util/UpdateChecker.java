package yuma140902.uptodatemod.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import yuma140902.uptodatemod.ModUpToDateMod;

public class UpdateChecker {
	private UpdateChecker() {}
	
	public static final UpdateChecker INSTANCE = new UpdateChecker();
	
	public static final String LATEST_STR = "latest";
	public static final String RECOMMENDED_STR = "recommended";
	public static final String homePage = "https://minecraft.curseforge.com/projects/uptodatemod";
	
	public String config_updateChannel = RECOMMENDED_STR;
	public boolean config_doCheckUpdate = true;
	
	public String currentVersion = ModUpToDateMod.MOD_VERSION;
	public String availableNewVersion = ModUpToDateMod.MOD_VERSION;
	public HashMap<String, String> versions = null;
	
	private static String getFromUrl(String urlStr) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.connect();
		
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append('\n');
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			try {
				isr.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			try {
				br.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return sb.toString();
	}
	
	
	private static HashMap<String, String> getVersionsTable(String tsv){
		HashMap<String, String> hashMap = new HashMap<>();
		
		if(tsv == null || tsv.isEmpty()) return hashMap;
		
		String[] lines = tsv.split("[\\n\\r]");
		for (String line : lines) {
			String[] tmp = line.split("\\t");
			if(tmp.length < 2) continue;
			hashMap.put(tmp[0], tmp[1]);
		}
		
		return hashMap;
	}
	
	public void checkForUpdates() {
		if(!config_doCheckUpdate) {
			return;
		}
		
		String versionsTsv = getFromUrl(ModUpToDateMod.MOD_VERSIONS_TSV_URL);
		if(versionsTsv == null || versionsTsv.isEmpty()) return;
		
		System.out.println("versionsTsv:");
		System.out.print(versionsTsv);
		
		this.versions = getVersionsTable(versionsTsv);
		
		String newestVersionStr = LATEST_STR.equals(config_updateChannel) ? LATEST_STR : RECOMMENDED_STR;
		
		if(versions.keySet().contains(newestVersionStr)) {
			String newestVersion = versions.get(newestVersionStr);
			if(Version3.isLaterThan(newestVersion, currentVersion)) {
				this.availableNewVersion = newestVersion;
				return;
			}
		}
	}
	
	public boolean hasNewVersionAvailable() {
		return Version3.isLaterThan(availableNewVersion, currentVersion);
	}
	
	public String getNewVersionUrl() {
		if(versions == null) return homePage;
		
		if(versions.keySet().contains(availableNewVersion)) {
			return versions.get(availableNewVersion);
		}
		else return homePage;
	}
}