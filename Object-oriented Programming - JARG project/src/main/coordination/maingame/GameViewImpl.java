package main.coordination.maingame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Optional;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.coordination.ImageFactory;
import main.dynamicBody.bullet.Bullet;
import main.dynamicBody.character.enemy.Enemy;
import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.character.player.Player;
import main.dynamicBody.move.Direction;
import main.gameEntities.EntityImageFactory;
import main.gameEntities.Stairs;
import main.levels.LevelComp;
import main.levels.Room;
import main.worldModel.RoomModel;
import main.worldModel.utilities.GameSettings;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Door;
import main.worldModel.utilities.enums.Entities;

public class GameViewImpl implements GameView {

	/**
	 * Variable containing data of current Level
	 */
	private LevelComp level;
	/**
	 * Variable containing data of current Player
	 */
	private Player player;
	/**
	 * Variable containing data of current RoomImpl
	 */
	private Room currentRoom;

	/**
	 * Variable containing the bullets of the MainCharacter of the current room
	 */
	private Map<Bullet, Image> bulletsPlayer = new HashMap<>();
	/**
	 * Variable containing the bullets of the enemies of the current room
	 */
	private Map<TypeEnemy, Set<Pair<Direction, Animation>>> enemyAnimation;
	/**
	 * Variable containing the MainCharacter animations
	 */
	private Set<Pair<Direction, Animation>> playerAnimation;
	private Image playerBull;
	private Image enemyBull;
	private Image obstacle;

	/**
	 * Constructor for RenderingImpl
	 * 
	 * @param level,  to keep track of current Level
	 * @param player, to keep track of current Player
	 * @throws SlickException
	 */
	public GameViewImpl(final LevelComp level, final Player player) throws SlickException {
		this.level = level;
		this.player = player;
		this.currentRoom = level.getLevel().get(level.getRoomID());
		this.playerAnimation = new HashSet<>();

		this.loadMainAnimations();
		this.enemyAnimation = level.checkAnimations();
	}

	/**
	 * Method used by GameController to render everything on screen
	 */
	public void render(Input input) throws SlickException {

		this.drawFloor();
		this.drawWalls();

		this.drawItems();
		this.drawMod();
		this.drawObstacles();

		this.drawDoors();

		if (!this.level.isPauseMenu() && !this.level.isGameWon()) {

			this.drawEnemies();

			try {
				this.drawMain(input);
				this.drawDoorTop();
			} catch (SlickException e) {
				Logger.getLogger(GameView.class.getName()).log(Level.WARNING, null, e);
			}

		}

	}

	/**
	 * Method used to preemptively load the animations to lighten the CPU work
	 * 
	 * @throws SlickException
	 */
	private void loadMainAnimations() throws SlickException {

		playerAnimation.add(
				new Pair<>(Direction.NORTH, ImageFactory.getAnimation(ImageFactory.getPlayerImage(Direction.NORTH))));
		playerAnimation.add(
				new Pair<>(Direction.EAST, ImageFactory.getAnimation(ImageFactory.getPlayerImage(Direction.EAST))));
		playerAnimation.add(
				new Pair<>(Direction.WEST, ImageFactory.getAnimation(ImageFactory.getPlayerImage(Direction.WEST))));
		playerAnimation.add(
				new Pair<>(Direction.SOUTH, ImageFactory.getAnimation(ImageFactory.getPlayerImage(Direction.SOUTH))));

		playerBull = ImageFactory.getPlayerBull();
		enemyBull = ImageFactory.getEnemyBull();
		obstacle = EntityImageFactory.getEntityTexture(Entities.BOULDER);

//		modifiers.add(EntityImage.ATTACKSPEED1);
//		modifiers.add(EntityImage.ATTACKUPGRADE1);
//		modifiers.add(EntityImage.HEALTHUPGRADE1);
//		modifiers.add(EntityImage.MOVEMENTSPEED1);
//		modifiers.add(EntityImage.RECOVERHEALTH);

	}

	/**
	 * Method used to draw the right animation of the Player, based on if he's
	 * moving or not
	 * 
	 * @param input, used to see if the Player is moved or not
	 * @throws SlickException
	 */
	private void drawMain(final Input input) throws SlickException {
		this.currentRoom = level.getLevel().get(level.getRoomID());

		Animation tmpPlayer = playerAnimation.stream().filter(s -> s.getX().equals(player.getDirection())).findFirst()
				.get().getY();

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_A)
				|| input.isKeyDown(Input.KEY_D)) {
			tmpPlayer.draw(player.getPosition().getX(), player.getPosition().getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);
		} else {
			tmpPlayer.setCurrentFrame(0);
			tmpPlayer.getCurrentFrame().draw(player.getPosition().getX(), player.getPosition().getY(),
					GameSettings.TILESIZE, GameSettings.TILESIZE);
		}

		this.drawMainProj();
	}

	/**
	 * Method called by drawMain, so it renders the bullets while also rendering the
	 * Player
	 */
	private void drawMainProj() {
		Set<Bullet> bullets = player.getRoomBullets();

		bullets.forEach(b -> {
			if (!bulletsPlayer.containsKey(b)) {
				bulletsPlayer.put(b, playerBull);
			}
		});

		if (!bullets.isEmpty()) {
			bullets.forEach(s -> {
				if (s.getRoom().getRoomID() == player.getRoom().getRoomID()) {
					rotateMainProj(s);
				}
			});
		}

	}

	/**
	 * Method called by drawMainProj for each projectile, to draw it appropriately
	 * based on direction
	 * 
	 * @param bullet, to check the stored direction of each bullet
	 * @throws SlickException
	 */
	private void rotateMainProj(final Bullet bullet) {
		if (bullet.getDirection().equals(Direction.NORTH)) {
			bulletsPlayer.get(bullet).draw(bullet.getPos().getX(), bullet.getPos().getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);
		} else if (bullet.getDirection().equals(Direction.EAST)) {
			bulletsPlayer.get(bullet).rotate(90);
			bulletsPlayer.get(bullet).draw(bullet.getPos().getX(), bullet.getPos().getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);
			bulletsPlayer.get(bullet).rotate(270);
		} else if (bullet.getDirection().equals(Direction.SOUTH)) {
			bulletsPlayer.get(bullet).rotate(180);
			bulletsPlayer.get(bullet).draw(bullet.getPos().getX(), bullet.getPos().getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);
			bulletsPlayer.get(bullet).rotate(180);
		} else if (bullet.getDirection().equals(Direction.WEST)) {
			bulletsPlayer.get(bullet).rotate(270);
			bulletsPlayer.get(bullet).draw(bullet.getPos().getX(), bullet.getPos().getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);
			bulletsPlayer.get(bullet).rotate(90);
		}
	}

	/**
	 * Method used to draw the enemies in each room
	 */
	private void drawEnemies() {
		currentRoom.getRoom().getEnemySet().forEach(s -> {

			Animation tmp = enemyAnimation.get(s.getTypeEnemy()).stream().filter(p -> p.getX().equals(s.getDirection()))
					.findFirst().get().getY();

			if (s.getTypeEnemy().equals(TypeEnemy.MAGE)) {

				tmp.setCurrentFrame(0);
				tmp.getCurrentFrame().draw(s.getPosition().getX(), s.getPosition().getY(), GameSettings.TILESIZE,
						GameSettings.TILESIZE);
			} else {
				tmp.draw(s.getPosition().getX(), s.getPosition().getY(), GameSettings.TILESIZE, GameSettings.TILESIZE);
			}
		});

		this.drawEnemyProj();
	}

	/**
	 * Method called by drawEnemies, to draw the appropriate bullet according to
	 * each enemy
	 */
	private void drawEnemyProj() {
		Set<Enemy> enemys = currentRoom.getRoom().getEnemySet();

		enemys.forEach(e -> {
			Set<Bullet> bulletMon = e.getRoomBullets();

			if (!bulletMon.isEmpty()) {
				bulletMon.forEach(s -> {
					enemyBull.draw(s.getPos().getX(), s.getPos().getY(), GameSettings.TILESIZE, GameSettings.TILESIZE);
				});
			}
		});
	}

	/**
	 * Method used to draw the obstacles place in each room
	 */
	private void drawObstacles() throws SlickException {
		currentRoom.getRoom().getObstacleSet().forEach(s -> {
			obstacle.draw(s.getPosition().getX(), s.getPosition().getY(), GameSettings.TILESIZE, GameSettings.TILESIZE);
		});
	}

	/**
	 * Method used to draw the items placed in each room
	 */
	private void drawItems() throws SlickException {
		Pair<Integer, Integer> tmp = currentRoom.getRoom().getKey().getPosition();
		if (!currentRoom.isGotRoomKey())
			EntityImageFactory.getEntityTexture(Entities.KEY).draw(tmp.getX(), tmp.getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);

		if (currentRoom.getRoom().getCoin().isPresent() && !level.isGotLevelCoin()) {
			tmp = currentRoom.getRoom().getCoin().get().getPosition();
			EntityImageFactory.getEntityTexture(Entities.COIN).draw(tmp.getX(), tmp.getY(), GameSettings.TILESIZE,
					GameSettings.TILESIZE);
		}
	}

	/**
	 * Method used to draw the modifiers placed in each room
	 */
	private void drawMod() throws SlickException {
		currentRoom.getRoom().getPickupablesSet().stream().filter(s -> s.getTypeEnt().equals(Entities.ATTACKUPGRADE1)
				|| s.getTypeEnt().equals(Entities.HEALTHUPGRADE1) || s.getTypeEnt().equals(Entities.MOVEMENTSPEED1)
				|| s.getTypeEnt().equals(Entities.ATTACKSPEED1) || s.getTypeEnt().equals(Entities.RECOVERHEALTH))
				.forEach(s -> {

					try {
						EntityImageFactory.getEntityTexture(s.getTypeEnt()).draw(s.getPosition().getX(),
								s.getPosition().getY(), GameSettings.TILESIZE, GameSettings.TILESIZE);
					} catch (SlickException e) {
						Logger.getLogger(EntityImageFactory.class.getName()).log(Level.WARNING, null, e);
					}
				});

	}

	/**
	 * Method used to draw the floor in each room
	 */
	private void drawFloor() throws SlickException {
		for (int x = 0; x < GameSettings.WIDTH; x += GameSettings.TILESIZE) {
			for (int y = 0; y < GameSettings.HEIGHT; y += GameSettings.TILESIZE) {
				currentRoom.getFloor().draw(x, y, GameSettings.TILESIZE, GameSettings.TILESIZE);
			}
		}
		if (currentRoom.getRoom().areStairsPresent()) {
			this.drawStairs();
		}
	}

	/**
	 * Method called by drawFloor, to draw the Stairs in the room if present
	 */
	private void drawStairs() throws SlickException {
		Stairs tmp = currentRoom.getRoom().getStairs();
		EntityImageFactory.getEntityTexture(Entities.STAIR).draw(tmp.getPosition().getX(), tmp.getPosition().getY(),
				GameSettings.TILESIZE, GameSettings.TILESIZE);
	}

	/**
	 * Method used to draw the wall in each room
	 */
	private void drawWalls() {
		for (int x = 0; x < GameSettings.WIDTH; x += GameSettings.TILESIZE) {
			for (int y = 0; y < GameSettings.HEIGHT; y += GameSettings.TILESIZE) {
				if (x == 0 && y > 0 && y < GameSettings.HEIGHT - GameSettings.TILESIZE) {
					currentRoom.getWallVert().draw(x, y, GameSettings.TILESIZE, GameSettings.TILESIZE);
				} else if (x == GameSettings.WIDTH - GameSettings.TILESIZE && y > 0
						&& y < GameSettings.HEIGHT - GameSettings.TILESIZE) {
					currentRoom.getWallVert().getFlippedCopy(true, false).draw(x, y, GameSettings.TILESIZE,
							GameSettings.TILESIZE);
				} else if (y == 0 && x > 0 && x < GameSettings.WIDTH - GameSettings.TILESIZE) {
					currentRoom.getWallHor().getFlippedCopy(false, true).draw(x, y, GameSettings.TILESIZE,
							GameSettings.TILESIZE);
				} else if (y == GameSettings.HEIGHT - GameSettings.TILESIZE && x > 0
						&& x < GameSettings.WIDTH - GameSettings.TILESIZE) {
					currentRoom.getWallHor().draw(x, y, GameSettings.TILESIZE, GameSettings.TILESIZE);
				}
			}
		}

		// Here a draw the corners, since they're always in the same position (the
		// corners), I don't need to draw the dynamically
		currentRoom.getCorners().draw(0, 0, GameSettings.TILESIZE, GameSettings.TILESIZE);
		currentRoom.getCorners().getFlippedCopy(false, true).draw(0, GameSettings.HEIGHT - GameSettings.TILESIZE,
				GameSettings.TILESIZE, GameSettings.TILESIZE);
		currentRoom.getCorners().getFlippedCopy(true, false).draw(GameSettings.WIDTH - GameSettings.TILESIZE, 0,
				GameSettings.TILESIZE, GameSettings.TILESIZE);
		currentRoom.getCorners().getFlippedCopy(true, true).draw(GameSettings.WIDTH - GameSettings.TILESIZE,
				GameSettings.HEIGHT - GameSettings.TILESIZE, GameSettings.TILESIZE, GameSettings.TILESIZE);
	}

	/**
	 * Method used to draw the doors (if they're present) in each room
	 */
	private void drawDoors() {
		Map<Door, Optional<RoomModel>> doors = currentRoom.getDoorAccess();

		for (Entry<Door, Optional<RoomModel>> entry : doors.entrySet()) {
			if (entry.getValue().isPresent()) {
				if (entry.getKey().equals(Door.NORTH)) {
					this.renderDoor(currentRoom.getDoorNorth(), Door.NORTH);
				} else if (entry.getKey().equals(Door.SOUTH)) {
					this.renderDoor(currentRoom.getDoorSouth(), Door.SOUTH);
				} else if (entry.getKey().equals(Door.WEST)) {
					this.renderDoor(currentRoom.getDoorWest(), Door.WEST);
				} else {
					this.renderDoor(currentRoom.getDoorEast(), Door.EAST);
				}

			}
		}
	}

	/**
	 * Method called by drawDoors, to draw the right animation based on the Door
	 * cardinality, and if the room has been cleared
	 * 
	 * @param animation, the right animation based on the Door cardinality
	 * @param door,      the according Door Cardinality
	 */
	private void renderDoor(final Animation animation, Door door) {
		if (currentRoom.isGotRoomKey()) {
			if (door.equals(Door.NORTH)) {
				animation.stopAt(7);
				animation.draw(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, 0, GameSettings.TILESIZE,
						GameSettings.TILESIZE);
			} else if (door.equals(Door.EAST)) {
				animation.stopAt(7);
				animation.draw(GameSettings.LIMITRIGHT, GameSettings.HEIGHT / 2 - GameSettings.TILESIZE,
						GameSettings.TILESIZE, GameSettings.TILESIZE);
			} else if (door.equals(Door.SOUTH)) {
				animation.stopAt(7);
				animation.draw(GameSettings.WIDTH / 2 - GameSettings.TILESIZE,
						GameSettings.HEIGHT - GameSettings.TILESIZE, GameSettings.TILESIZE, GameSettings.TILESIZE);
			} else if (door.equals(Door.WEST)) {
				animation.stopAt(7);
				animation.draw(0, GameSettings.HEIGHT / 2 - GameSettings.TILESIZE, GameSettings.TILESIZE,
						GameSettings.TILESIZE);
			}
		} else {
			if (door.equals(Door.NORTH)) {
				animation.setCurrentFrame(0);
				animation.getCurrentFrame().draw(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, 0,
						GameSettings.TILESIZE, GameSettings.TILESIZE);
			} else if (door.equals(Door.EAST)) {
				animation.setCurrentFrame(0);
				animation.getCurrentFrame().draw(GameSettings.LIMITRIGHT,
						GameSettings.HEIGHT / 2 - GameSettings.TILESIZE, GameSettings.TILESIZE, GameSettings.TILESIZE);
			} else if (door.equals(Door.SOUTH)) {
				animation.setCurrentFrame(0);
				animation.getCurrentFrame().draw(GameSettings.WIDTH / 2 - GameSettings.TILESIZE,
						GameSettings.HEIGHT - GameSettings.TILESIZE, GameSettings.TILESIZE, GameSettings.TILESIZE);
			} else if (door.equals(Door.WEST)) {
				animation.setCurrentFrame(0);
				animation.getCurrentFrame().draw(0, GameSettings.HEIGHT / 2 - GameSettings.TILESIZE,
						GameSettings.TILESIZE, GameSettings.TILESIZE);
			}
		}
	}

	/**
	 * Method used to draw the top of the doors in each room, so that the it gives
	 * the Player the illusion of traversing room
	 * 
	 * @throws SlickException
	 * @see SlickException
	 */
	private void drawDoorTop() throws SlickException {
		Map<Door, Optional<RoomModel>> doors = level.getLevel().get(level.getRoomID()).getDoorAccess();

		for (Entry<Door, Optional<RoomModel>> entry : doors.entrySet()) {
			if (entry.getValue().isPresent()) {
				if (entry.getKey().equals(Door.NORTH)) {
					currentRoom.getTopDoorHor().getFlippedCopy(true, false)
							.draw(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, 0, 64, 14);
				} else if (entry.getKey().equals(Door.SOUTH)) {
					currentRoom.getTopDoorHor().getFlippedCopy(true, true)
							.draw(GameSettings.WIDTH / 2 - GameSettings.TILESIZE, GameSettings.HEIGHT - 14, 64, 14);
				} else if (entry.getKey().equals(Door.EAST)) {
					currentRoom.getTopDoorVert().getFlippedCopy(true, false).draw(GameSettings.WIDTH - 14,
							GameSettings.HEIGHT / 2 - GameSettings.TILESIZE, 14, 64);
				} else {
					currentRoom.getTopDoorVert().getFlippedCopy(false, false).draw(0,
							GameSettings.HEIGHT / 2 - GameSettings.TILESIZE, 14, 64);
				}
			}
		}
	}

}
