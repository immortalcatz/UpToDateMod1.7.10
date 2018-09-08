package yuma140902.uptodatemod;

import net.minecraft.block.Block;
import yuma140902.uptodatemod.blocks.DoorAcacia;
import yuma140902.uptodatemod.blocks.DoorBirch;
import yuma140902.uptodatemod.blocks.DoorDarkOak;
import yuma140902.uptodatemod.blocks.DoorJungle;
import yuma140902.uptodatemod.blocks.DoorSpruce;
import yuma140902.uptodatemod.blocks.FenceAcacia;
import yuma140902.uptodatemod.blocks.FenceBirch;
import yuma140902.uptodatemod.blocks.FenceDarkOak;
import yuma140902.uptodatemod.blocks.FenceJungle;
import yuma140902.uptodatemod.blocks.FenceSpruce;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		((IRegisterable) stone).register();
		((IRegisterable) doorAcacia).register();
		((IRegisterable) doorBirch).register();
		((IRegisterable) doorDarkOak).register();
		((IRegisterable) doorJungle).register();
		((IRegisterable) doorSpruce).register();
		((IRegisterable) fenceAcacia).register();
		((IRegisterable) fenceBirch).register();
		((IRegisterable) fenceDarkOak).register();
		((IRegisterable) fenceJungle).register();
		((IRegisterable) fenceSpruce).register();
	}
	
	public static final Block stone = new yuma140902.uptodatemod.blocks.Stone();
	public static final Block doorAcacia = new DoorAcacia();
	public static final Block doorBirch = new DoorBirch();
	public static final Block doorDarkOak = new DoorDarkOak();
	public static final Block doorJungle = new DoorJungle();
	public static final Block doorSpruce = new DoorSpruce();
	
	public static final Block fenceAcacia = new FenceAcacia();
	public static final Block fenceBirch = new FenceBirch();
	public static final Block fenceDarkOak = new FenceDarkOak();
	public static final Block fenceJungle = new FenceJungle();
	public static final Block fenceSpruce = new FenceSpruce();
}