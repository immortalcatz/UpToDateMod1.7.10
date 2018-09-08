package yuma140902.uptodatemod;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import yuma140902.uptodatemod.blocks.Stone;
import yuma140902.uptodatemod.util.ListUtils;

public final class Recipes {
	private Recipes() {}
	
	public static void removeVanillaRecipes() {
		List<String> removeRecipesOutputNameList = new ArrayList<>();
		removeRecipesOutputNameList.add("minecraft:wooden_door");
		if(ModUpToDateMod.INSTANCE.config_recipeRemove_oldFenceRecipe)
			removeRecipesOutputNameList.add("minecraft:fence");
		
		removeRecipesByOutputName(removeRecipesOutputNameList);
	}
	
	//クラフト結果のアイテムの内部名称によって削除するレシピを指定します。
	//レシピは最初に見つかった一つだけが削除されます。
	private static void removeRecipesByOutputName(List<String> outputNameList) {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		List<IRecipe> removeList = new ArrayList<IRecipe>();
		FMLControlledNamespacedRegistry<Item> itemRegistry = GameData.getItemRegistry();
		
		for(IRecipe recipe : recipes) {
			//see: http://forum.minecraftuser.jp/viewtopic.php?f=39&t=33757
			if(recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() != null) {
				Item outputItem = recipe.getRecipeOutput().getItem();
				String name = itemRegistry.getNameForObject(outputItem);
				
				if(ListUtils.contains(outputNameList, name)) {
					// このループ内では削除はせず、い)ったん削除予定リストに入れる
					removeList.add(recipe);

					outputNameList.remove(name);
				}
			}
		}
		
		// 削除する
		for(IRecipe removeRecipe : removeList) {
			recipes.remove(removeRecipe);
		}
	}
	
	public static void register() {
		registerStoneRecipes();
		registerDoorRecipes();
		registerFenceRecipes();
	}
	
	private static void registerStoneRecipes() {
	//4つ並べて磨かれた〇〇
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_GRANITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, Stone.META_GRANITE));
			
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_DIORITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE));
			
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_ANDESITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, Stone.META_ANDESITE));
			
			//丸石+ネザー水晶->閃緑岩
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 2, Stone.META_DIORITE),
					"SN",
					"NS",
					'S', Blocks.cobblestone,
					'N', Items.quartz
					);
			
			//閃緑岩+丸石->安山岩
			GameRegistry.addShapelessRecipe(
					new ItemStack(MyBlocks.stone, 2, Stone.META_ANDESITE),
					Blocks.cobblestone,
					new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE)
					);
			
			//閃緑岩+ネザー水晶->花崗岩
			GameRegistry.addShapelessRecipe(
					new ItemStack(MyBlocks.stone, 2, Stone.META_GRANITE),
					Items.quartz,
					new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE)
					);
	}

	private static void registerDoorRecipes() {
		final int
				PLANK_META_OAK = 0,
				PLANK_META_ACACIA = 4,
				PLANK_META_BIRCH = 2,
				PLANK_META_DARKOAK = 5,
				PLANK_META_JUNGLE = 3,
				PLANK_META_SPRUCE = 1;
		
		GameRegistry.addRecipe(
				new ItemStack(Items.wooden_door, 1, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_OAK)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorAcacia, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_ACACIA)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorBirch, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_BIRCH)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorDarkOak, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_DARKOAK)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorJungle, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_JUNGLE)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorSpruce, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_SPRUCE)
				);
	}
	
	private static void registerFenceRecipes() {
		final int
			PLANK_META_OAK = 0,
			PLANK_META_ACACIA = 4,
			PLANK_META_BIRCH = 2,
			PLANK_META_DARKOAK = 5,
			PLANK_META_JUNGLE = 3,
			PLANK_META_SPRUCE = 1;
		
		GameRegistry.addRecipe(
				new ItemStack(Blocks.fence, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_OAK),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceAcacia, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_ACACIA),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceBirch, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_BIRCH),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceDarkOak, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_DARKOAK),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceJungle, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_JUNGLE),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceSpruce, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_SPRUCE),
				'|', Items.stick
				);
		
	}
}