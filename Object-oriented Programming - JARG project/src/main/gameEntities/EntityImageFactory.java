package main.gameEntities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.worldModel.utilities.enums.Entities;

public class EntityImageFactory {

	public static Image getEntityTexture(final Entities entity) throws SlickException {
		Image tmpEnt;
		switch (entity) {
		case BOULDER:
			tmpEnt = EntityImage.OBSTACLE.getImage();
			break;
		case STAIR:
			tmpEnt = EntityImage.STAIRS.getImage();
			break;
		case COIN:
			tmpEnt = EntityImage.COIN.getImage();
			break;
		case KEY:
			tmpEnt = EntityImage.KEY.getImage();
			break;
		case ATTACKSPEED1:
			tmpEnt = EntityImage.ATTACKSPEED1.getImage();
			break;
		case ATTACKUPGRADE1:
			tmpEnt = EntityImage.ATTACKUPGRADE1.getImage();
			break;
		case HEALTHUPGRADE1:
			tmpEnt = EntityImage.HEALTHUPGRADE1.getImage();
			break;
		case MOVEMENTSPEED1:
			tmpEnt = EntityImage.MOVEMENTSPEED1.getImage();
			break;
		case RECOVERHEALTH:
			tmpEnt = EntityImage.RECOVERHEALTH.getImage();
			break;
		case UICOIN:
			tmpEnt = EntityImage.UICOIN.getImage();
			break;
		default:
			throw new IllegalArgumentException("Entity image not found");
		}
		return tmpEnt;
	}

}
