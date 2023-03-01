package main.dynamicBody.character.player;

import main.coordination.SoundBoard;
import main.coordination.SoundBoardFactory;
import main.dynamicBody.UpDownLeftRight;
import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.character.health.*;
import main.dynamicBody.character.player.inventory.*;
import main.dynamicBody.character.player.movement.*;
import main.dynamicBody.character.player.movement.check.*;
import main.dynamicBody.character.player.shoot.*;
import main.dynamicBody.move.Direction;
import main.levels.LevelComp;
import main.worldModel.RoomModel;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Door;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class that implements interface Player used to define all the aspects
 * concerning the player
 */

public class PlayerImpl implements Player {

	private int level;
	private boolean clearRoom;
	private int playerSpeed;
	private int damage;
	private int rateOfFire;
	private Pair<Integer, Integer> position;
	private Direction direction;
	private Movement move;
	private CheckPlayerMovement check;
	private Health health;
	private RoomModel currentRoom;
	private Inventory inventory;
	private Pair<PlayerDimensions, PlayerDimensions> dimensions;
	private ShootingPlayer bullet;
	private Set<Bullet> roomBullets = new HashSet<>();

	/**
	 * Default constructor
	 * 
	 * @param pos,   player's position
	 * @param dir,   player's direction
	 * @param level, player's starting level
	 */
	public PlayerImpl(Pair<Integer, Integer> pos, Direction dir, int level) {
		this.position = pos;
		this.level = level;
		this.direction = dir;
		this.playerSpeed = 2;
		this.rateOfFire = 800;
		this.damage = 10;
		this.inventory = new InventoryImpl(this);
		this.health = new HealthImpl(100);
		this.move = new MovementImpl();
		this.check = new CheckPlayerMovementImpl(this);
		this.bullet = new ShootingPlayerImpl(this);
		this.dimensions = PlayerDimensions.getPlayerDimensions(this);
		this.clearRoom = false;
	}

	@Override
	public void setPosition(Input input, LevelComp level) throws SlickException {
		Pair<Integer, Integer> newPos;
		Map<Door, Optional<RoomModel>> map = level.getLevel().get(level.getRoomID()).getDoorAccess();
		newPos = move.movePlayer(input, this.position, this.direction, this.playerSpeed);

		check.checkEnemyRoom(this.currentRoom, newPos);

		check.checkGameEntities(this.currentRoom, newPos, level);

		if (check.possiblePos(this.currentRoom, newPos) || (check.checkDoors(newPos, map) && clearRoom)) {
			this.position = newPos;
		}
		/**
		 * direction changes even if the player can't actually go in that position
		 */
		this.direction = move.getDirection();
	}

	public void resetStats() {
		this.playerSpeed = 2;
		this.rateOfFire = 800;
		this.damage = 10;
		this.inventory = new InventoryImpl(this);
		this.health = new HealthImpl(100);
	}

	@Override
	public void transitionPos(Pair<Integer, Integer> position) {
		this.position = position;
	}

	@Override
	public Pair<Integer, Integer> getPosition() {
		return this.position;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public void setCurrentRoom(RoomModel room) {
		this.currentRoom = room;
	}

	@Override
	public RoomModel getRoom() {
		return this.currentRoom;
	}

	@Override
	public int getRateOfFire() {
		return this.rateOfFire;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public UpDownLeftRight<Integer> getDimension() {
		if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
			return dimensions.getX().getDimensions();
		} else if (direction.equals(Direction.WEST) || direction.equals(Direction.EAST)) {
			return dimensions.getY().getDimensions();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int getPlayerSpeed() {
		return this.playerSpeed;
	}

	@Override
	public void upgradePlayerSpeed(int upgrade) {
		this.playerSpeed = this.playerSpeed + upgrade;
	}

	@Override
	public void upgradeDamage(int upgrade) {
		this.damage = this.damage + upgrade;
	}

	@Override
	public void upgradeRateOfFire(int upgrade) {
		this.rateOfFire = this.rateOfFire - upgrade;
	}

	@Override
	public void upgradeMaxHealth(int upgrade) {
		this.health.upgradeMaxHealth(upgrade);
	}

	@Override
	public void heal(int heal) {
		this.health.heal(heal);
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void takeDamage(int damage) {
		SoundBoardFactory.getEntitySound(SoundBoard.mainCharacterHurt);
		this.health.takeDmg(damage);
	}

	@Override
	public boolean isAlive() {
		return this.health.isAlive();
	}

	@Override
	public ShootingPlayer getShootingBullet() {
		return this.bullet;
	}

	@Override
	public CheckPlayerMovement getCheck() {
		return this.check;
	}

	@Override
	public Set<Bullet> getRoomBullets() {
		return this.roomBullets;
	}

	@Override
	public void setClearRoom(boolean clearRoom) {
		this.clearRoom = clearRoom;
	}

	@Override
	public int getCurrentHealth() {
		return this.health.getCurrentHealth();
	}

	@Override
	public int getMaxHealth() {
		return this.health.getMaxHealth();
	}

	@Override
	public Movement getMove() {
		return this.move;
	}
}