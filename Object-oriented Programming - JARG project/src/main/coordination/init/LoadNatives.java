package main.coordination.init;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.worldModel.utilities.GameSettings;

public class LoadNatives {

	/**
	 * Variable containing the name of the current OS
	 */
	private static String OS = System.getProperty("os.name").toLowerCase();

	/**
	 * Method used to check if current OS is Windows
	 * 
	 * @return true if Windows, otherwise false
	 */
	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	/**
	 * Method used to check if current OS is MacOS
	 * 
	 * @return true if MacOS, otherwise false
	 */
	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	/**
	 * Method used to check if current OS is Linux
	 * 
	 * @return true if Linux, otherwise false
	 */
	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	/**
	 * Method used to check if application launched in jar file or Eclipse project
	 * 
	 * @param checkJar, string of the resource in the module
	 * @return true if app launched in jar file, otherwise false
	 */
	public static boolean isJar(final String checkJar) {
		return (checkJar.indexOf("jar") >= 0);
	}

	/**
	 * Method used to block Illegal Reflective Access, since issue happens
	 */
	public static void disableAccessWarning() {
		try {
			Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
			Field field = unsafeClass.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			Object unsafe = field.get(null);

			Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class,
					Object.class);
			Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

			Class<?> loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
			Field loggerField = loggerClass.getDeclaredField("logger");
			Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
			putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
		} catch (Exception ignored) {
			Logger.getLogger(LoadNatives.class.getName()).log(Level.SEVERE, null, ignored);
		}
	}

	/**
	 * Method used to load all libraries and natives to make the project run
	 * 
	 * @throws IOException
	 * @see IOException
	 */
	public static void loadLibs() throws IOException {

		String destPath;

		if (isWindows()) {
			destPath = System.getProperty("java.io.tmpdir") + "jarg" + GameSettings.SEP;
		} else {
			destPath = System.getProperty("java.io.tmpdir") + File.separator + "jarg" + GameSettings.SEP;
		}

		if (!LoadNatives.isJar(StateCoord.class.getResource("StateCoord.class").toString())) {

			try {
				String currPath = System.getProperty("user.dir") + GameSettings.SEP + System.getProperty("java.class.path");

				JarFile jarFile = new JarFile(currPath);
				Enumeration<JarEntry> enums = jarFile.entries();
				while (enums.hasMoreElements()) {
					JarEntry entry = enums.nextElement();
					if (entry.getName().startsWith("libJars") || entry.getName().startsWith("res")) {
						int nBytes;
						File toWrite = new File(destPath + entry.getName());
						if (entry.isDirectory()) {
							toWrite.mkdirs();
							continue;
						}
						InputStream in = new BufferedInputStream(jarFile.getInputStream(entry));
						final OutputStream out = new BufferedOutputStream(new FileOutputStream(toWrite));
						byte[] buffer = new byte[2048];
						while (true) {
							nBytes = in.read(buffer);
							if (nBytes <= 0) {
								break;
							}
							out.write(buffer, 0, nBytes);
						}
						out.flush();
						out.close();
						in.close();
					}
				}
				jarFile.close();
			} catch (IOException ex) {
				System.out.println("Could not find file ");
			}

		}

		String libPath;

		if (!LoadNatives.isJar(StateCoord.class.getResource("StateCoord.class").toString())) {
			libPath = destPath + "libJars" + GameSettings.SEP;
		} else {
			libPath = "." + GameSettings.SEP + "lib" + GameSettings.SEP + "libJars" + GameSettings.SEP;
		}

		if (isWindows()) {
			System.load(new File(libPath + "lwjgl64.dll").getAbsolutePath());
			System.load(new File(libPath + "OpenAL64.dll").getAbsolutePath());
			System.load(new File(libPath + "jinput-dx8_64.dll").getAbsolutePath());
			System.load(new File(libPath + "jinput-raw_64.dll").getAbsolutePath());
		}

		if (isUnix()) {
			System.load(new File(libPath + "liblwjgl64.so").getAbsolutePath());
			System.load(new File(libPath + "libjinput-linux64.so").getAbsolutePath());
			System.load(new File(libPath + "libopenal64.so").getAbsolutePath());
		}

		if (isMac()) {
			System.load(libPath + "libjinput-osx.dylib");
			System.load(libPath + "liblwjgl.dylib");
			System.load(libPath + "openal.dylib");
			System.load(libPath + "libjinput-osx.jnilib");
		}
	}

}
