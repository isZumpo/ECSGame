package se.hampuscarlsson.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.hampuscarlsson.game.ECSGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 16;
		config.vSyncEnabled = true;
		config.allowSoftwareMode = false;
		new LwjglApplication(new ECSGame(), config);
	}
}
