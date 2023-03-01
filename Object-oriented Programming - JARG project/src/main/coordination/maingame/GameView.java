package main.coordination.maingame;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public interface GameView {

	void render(Input input) throws SlickException;
}
