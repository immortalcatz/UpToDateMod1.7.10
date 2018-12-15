package yuma140902.uptodatemod;

import yuma140902.uptodatemod.items.ItemBoatAcacia;
import yuma140902.uptodatemod.items.ItemBoatBirch;
import yuma140902.uptodatemod.items.ItemBoatDarkOak;
import yuma140902.uptodatemod.items.ItemBoatJungle;
import yuma140902.uptodatemod.items.ItemBoatSpruce;
import yuma140902.uptodatemod.items.ItemDoorAcacia;
import yuma140902.uptodatemod.items.ItemDoorBirch;
import yuma140902.uptodatemod.items.ItemDoorDarkOak;
import yuma140902.uptodatemod.items.ItemDoorJungle;
import yuma140902.uptodatemod.items.ItemDoorSpruce;
import yuma140902.uptodatemod.items.ItemPrismarineCrystals;
import yuma140902.uptodatemod.items.ItemPrismarineShard;

public final class MyItems {
	private MyItems() {}
	
	public static void register() {
		itemDoorAcacia.register();
		itemDoorBirch.register();
		itemDoorDarkOak.register();
		itemDoorJungle.register();
		itemDoorSpruce.register();
		
		prismarineCrystal.register();
		prismarineShard.register();
		
		boatAcacia.register();
		boatBirch.register();
		boatDarkOak.register();
		boatJungle.register();
		boatSpruce.register();
	}
	
	public static final ItemDoorAcacia itemDoorAcacia = new ItemDoorAcacia();
	public static final ItemDoorBirch itemDoorBirch = new ItemDoorBirch();
	public static final ItemDoorDarkOak itemDoorDarkOak = new ItemDoorDarkOak();
	public static final ItemDoorJungle itemDoorJungle = new ItemDoorJungle();
	public static final ItemDoorSpruce itemDoorSpruce = new ItemDoorSpruce();
	
	public static final ItemPrismarineCrystals prismarineCrystal = new ItemPrismarineCrystals();
	public static final ItemPrismarineShard prismarineShard = new ItemPrismarineShard();
	
	public static final ItemBoatAcacia boatAcacia = new ItemBoatAcacia();
	public static final ItemBoatBirch boatBirch = new ItemBoatBirch();
	public static final ItemBoatDarkOak boatDarkOak = new ItemBoatDarkOak();
	public static final ItemBoatJungle boatJungle = new ItemBoatJungle();
	public static final ItemBoatSpruce boatSpruce = new ItemBoatSpruce();
}
