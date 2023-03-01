package main.dynamicBody.bullet;

import main.dynamicBody.UpDownLeftRight;
import main.dynamicBody.bullet.dimension.BulletDimFactory;
import main.dynamicBody.bullet.dimension.DimensionBullet;
import main.dynamicBody.bullet.move.MoveBull;
import main.dynamicBody.bullet.move.MoveBullImpl;
import main.dynamicBody.bullet.move.check.CheckMonsBull;
import main.dynamicBody.bullet.move.check.CheckPlayerBull;
import main.dynamicBody.move.Direction;
import main.dynamicBody.move.check.CheckPos;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;

/**
 * Class that implements interface Bullet used to represent a character's
 * generic bullet in the dungeon
 */

public class BulletImpl implements Bullet {

	private Pair<Integer, Integer> pos;
	private int dmg;
	private Direction direction;
	private MoveBull move;
	private RoomModel room;
	private Pair<DimensionBullet, DimensionBullet> dimensions;
	private CheckPos check;
	private TypeBullet typeBull;

	private BulletDimFactory dimFactory = new BulletDimFactory();

	/**
	 * Default constructor
	 * 
	 * @param position,  bullet's position
	 * @param damage,    bullet's damage power
	 * @param direction, bullet's direction
	 * @param room,      bullet's current room
	 * @param type,      bullet's type (player type or enemy type)
	 */
	public BulletImpl(Pair<Integer, Integer> position, int damage, Direction direction, RoomModel room,
			TypeBullet type) {
		this.pos = position;
		this.dmg = damage;
		this.direction = direction;
		this.room = room;
		this.move = new MoveBullImpl(room);
		this.typeBull = type;
		this.dimensions = dimFactory.getDimensionBullet(type);
		this.check = findCheck(type);
	}

	/**
	 * Method used to check which control type the bullet should used according to
	 * his type
	 * 
	 * @param type, bullet's type
	 * @return type of check that the bullet will use while shooting in the dungeon
	 */
	private CheckPos findCheck(TypeBullet type) {
		switch (type) {
		case ENEMY_BULL:
			return new CheckMonsBull(this);
		case PLAYER_BULL:
			return new CheckPlayerBull(this);
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isAlive() {
		return move.isAlive();
	}

	@Override
	public Pair<Integer, Integer> getPos() {
		return this.pos;
	}

	@Override
	public void updatePos() {
		this.pos = move.nextPos(this.pos, direction, check);
	}

	@Override
	public int getDamage() {
		return this.dmg;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public UpDownLeftRight<Integer> getDimension() {
		if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
			return dimensions.getX().getDimension();
		} else {
			return dimensions.getY().getDimension();
		}
	}

	@Override
	public RoomModel getRoom() {
		return room;
	}

	@Override
	public TypeBullet getType() {
		return typeBull;
	}

}
