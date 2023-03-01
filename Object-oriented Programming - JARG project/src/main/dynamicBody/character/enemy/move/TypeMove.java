package main.dynamicBody.character.enemy.move;

/**
 * Enumeration of the type of movement that enemy can do
 */
public enum TypeMove {

	STRAIGHT, IMMOBILIZED, TELEPORT, RANDOM, TO_PLAYER;

	/**
	 * Method use to get the movement of Boss
	 * 
	 * @return TypeMove of Boss
	 */
	public static TypeMove getBossMove() {
		return TO_PLAYER;
	}
}
