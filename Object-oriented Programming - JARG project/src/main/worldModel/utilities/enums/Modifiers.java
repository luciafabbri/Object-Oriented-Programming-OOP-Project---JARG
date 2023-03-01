package main.worldModel.utilities.enums;

import java.util.*;

/**
 * Enumeration listing the modifiers, the entities that the player can pick up
 * and consume
 *
 */
public enum Modifiers {

	ATTACKUPGRADE1(0), HEALTHUPGRADE1(1), ATTACKSPEED1(2), MOVEMENTSPEED1(3), RECOVERHEALRTH(4);

	private Integer modifierCode;
	private static Map<Integer, Modifiers> map = new HashMap<>();

	private Modifiers(Integer modifierCode) {
		this.modifierCode = modifierCode;
	}

	static {
		for (Modifiers mod : Modifiers.values()) {
			map.put(mod.modifierCode, mod);
		}
	}

	/**
	 * Through a statically generated map association, the enumeration elements can
	 * be accessed through an integer code
	 * 
	 * @param pickupable code
	 * @return associated modifier entity
	 */
	public static Modifiers valueOf(int modifierCode) {
		return map.get(modifierCode);
	}

}
