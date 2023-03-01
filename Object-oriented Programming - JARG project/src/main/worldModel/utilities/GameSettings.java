package main.worldModel.utilities;

import main.coordination.init.LoadNatives;

/**
 * An interface listing game parameters
 *
 */
public interface GameSettings {

	final static int WIDTH = 1280;
	final static int HEIGHT = 768;
	final static int TILESIZE = 64;
	final static int TOTALTILES = ((WIDTH - TILESIZE) * (HEIGHT - TILESIZE)) / (TILESIZE * TILESIZE);

	final static int MINDOORS = 1;
	final static int MAXDOORS = 4;

	final static int LIMITRIGHT = WIDTH - TILESIZE;
	final static int LIMITLEFT = TILESIZE - 1;
	final static int LIMITUP = TILESIZE - 48 - 1;
	final static int LIMITDOWN = HEIGHT - TILESIZE;

	final static int OBST_DOWN = TILESIZE - 48;

	final static int TOTCOINS = 4;

	final static String SEP = System.getProperty("file.separator");

	final static int FINALLEVEL = 4;

	final static String RESPATHOS = LoadNatives.isWindows() ? System.getProperty("java.io.tmpdir") + "jarg" + SEP
			: System.getProperty("java.io.tmpdir") + SEP + "jarg" + SEP;

	final static String RESPATH = !LoadNatives.isJar(GameSettings.class.getResource("GameSettings.class").toString())
			? RESPATHOS
			: "." + SEP + "res" + SEP;

}