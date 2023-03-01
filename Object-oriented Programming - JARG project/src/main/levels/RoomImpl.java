package main.levels;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.tiles.*;
import main.worldModel.RoomModel;
import main.worldModel.utilities.enums.Door;

public class RoomImpl implements Room {

	/**
	 * Variable containing the RoomModel associated with the right room
	 */
	private RoomModel room;
	/**
	 * Variable containing the Map with the door and rooms the current room is
	 * connected to
	 */
	private Map<Door, Optional<RoomModel>> doorAccess;

	/**
	 * Variable containing the Tile of the floor
	 */
	private Image floor;
	/**
	 * Variable containing the Image of the vertical walls
	 */
	private Image wallVert;
	/**
	 * Variable containing the Image of the horizontal walls
	 */
	private Image wallHor;
	/**
	 * Variable containing the Image of the corner walls
	 */
	private Image corners;
	/**
	 * Variable containing the Image of the top of the vertical door
	 */
	private Image topDoorVert;
	/**
	 * Variable containing the Image of the top of the horizontal door
	 */
	private Image topDoorHor;

	/**
	 * Variable containing the Animation, so the animation of the west room
	 */
	private Animation doorWest;
	/**
	 * Variable containing the Animation, so the animation of the north room
	 */
	private Animation doorNorth;
	/**
	 * Variable containing the Animation, so the animation of the east room
	 */
	private Animation doorEast;
	/**
	 * Variable containing the Animation, so the animation of the south room
	 */
	private Animation doorSouth;
	/**
	 * Variable containing the boolean to check if the room key has been takens
	 */
	private boolean gotRoomKey;

	public RoomImpl(final RoomModel room, Map<RoomModel, Map<Door, Optional<RoomModel>>> doorAccess) {
		this.room = room;
		this.doorAccess = doorAccess.entrySet().stream().filter(s -> s.getKey().getRoomID() == room.getRoomID())
				.findFirst().get().getValue();
		this.gotRoomKey = false;

		try {

			this.floor = Tile.getTileTexture(TileImage.FLOOR1);
			this.wallVert = Tile.getTileTexture(TileImage.WALLHOR2);
			this.wallHor = Tile.getTileTexture(TileImage.WALLHOR1);
			this.corners = Tile.getTileTexture(TileImage.CORNER1);

			this.topDoorVert = Tile.getTileTexture(TileImage.DOORTOP1);
			this.topDoorHor = Tile.getTileTexture(TileImage.DOORTOP2);

			this.doorWest = AnimatedTile.getAnimatedTile(AnimatedTileImage.DOORWEST);
			this.doorNorth = AnimatedTile.getAnimatedTile(AnimatedTileImage.DOORNORTH);
			this.doorEast = AnimatedTile.getAnimatedTile(AnimatedTileImage.DOOREAST);
			this.doorSouth = AnimatedTile.getAnimatedTile(AnimatedTileImage.DOORSOUTH);

		} catch (SlickException e) {
			Logger.getLogger(Tile.class.getName()).log(Level.WARNING, null, e);
		}
	}

	@Override
	public Image getFloor() {
		return floor;
	}

	@Override
	public Image getWallVert() {
		return wallVert;
	}

	@Override
	public Image getWallHor() {
		return wallHor;
	}

	@Override
	public Image getCorners() {
		return corners;
	}

	@Override
	public Map<Door, Optional<RoomModel>> getDoorAccess() {
		return doorAccess;
	}

	@Override
	public RoomModel getRoom() {
		return room;
	}

	@Override
	public Animation getDoorWest() {
		return doorWest;
	}

	@Override
	public Animation getDoorNorth() {
		return doorNorth;
	}

	@Override
	public Animation getDoorEast() {
		return doorEast;
	}

	@Override
	public Animation getDoorSouth() {
		return doorSouth;
	}

	@Override
	public Image getTopDoorVert() {
		return topDoorVert;
	}

	@Override
	public Image getTopDoorHor() {
		return topDoorHor;
	}

	public boolean isGotRoomKey() {
		return gotRoomKey;
	}

	public void setGotRoomKey(boolean gotRoomKey) {
		this.gotRoomKey = gotRoomKey;
	}

}
