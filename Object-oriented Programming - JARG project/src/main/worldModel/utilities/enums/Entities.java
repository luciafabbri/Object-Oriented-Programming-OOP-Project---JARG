package main.worldModel.utilities.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration listing the game's inanimate entities and the code that
 * identifies them
 *
 */
public enum Entities {

	COIN(0), KEY(1), ATTACKUPGRADE1(2), HEALTHUPGRADE1(3), BOULDER(4), STAIR(5), ATTACKSPEED1(6), MOVEMENTSPEED1(7),
	RECOVERHEALTH(8), UICOIN(9);

	private Integer entityCode;
	private static final Map<Integer, Entities> map = new HashMap<>();

	private Entities(Integer entityCode) {
		this.entityCode = entityCode;
	}

	static {
		for (Entities en : Entities.values()) {
			map.put(en.entityCode, en);
		}
	}

	/**
	 * Through a statically generated map association, the enumeration elements can
	 * be accessed through an integer code
	 * 
	 * @param entity code
	 * @return associated entity
	 */
	public static Entities valueOf(int entityCode) {
		return map.get(entityCode);
	}

}
