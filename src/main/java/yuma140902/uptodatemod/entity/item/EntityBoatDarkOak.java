package yuma140902.uptodatemod.entity.item;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.util.Stat;

public class EntityBoatDarkOak extends EntityModBoatBase {

	public EntityBoatDarkOak(World world) {
		super(world);
	}

	public EntityBoatDarkOak(World world, double d1, double d2, double d3) {
		super(world, d1, d2, d3);
	}
	
	@Override
	protected Type getType() {
		return Type.DARK_OAK;
	}
	
	@Override
	protected Item getItemBoat() {
		return MyItems.boatDarkOak;
	}
	
	@Override
	protected int getPlankMeta() {
		return Stat.PLANK_META_DARKOAK;
	}
	
}
