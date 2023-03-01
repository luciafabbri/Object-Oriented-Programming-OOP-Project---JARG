package main.dynamicBody;

/**
 * Class used to represents all the different dynamic body dimensions based on
 * their images, in order to be as real as possible
 * 
 * @param <X>, generic param X that will be replace by any type passed as a
 *             parameter
 */

public class UpDownLeftRight<X> {

	private final X up;
	private final X down;
	private final X left;
	private final X right;

	/**
	 * Default constructor
	 * 
	 * @param up,    dynamic body's up dimension
	 * @param down,  dynamic body's down dimension
	 * @param left,  dynamic body's left dimension
	 * @param right, dynamic body's right dimension
	 */
	public UpDownLeftRight(X up, X down, X left, X right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public X getUp() {
		return up;
	}

	public X getDown() {
		return down;
	}

	public X getLeft() {
		return left;
	}

	public X getRight() {
		return right;
	}

}
