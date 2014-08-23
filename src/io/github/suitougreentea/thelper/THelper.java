package io.github.suitougreentea.thelper;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class THelper {

    public static void main(String[] args) throws SlickException {
	AppGameContainer app = new AppGameContainer(new GameTHelper("THelper"));
	app.setDisplayMode(800, 600, false);
	app.setTargetFrameRate(60);
	app.setShowFPS(true);
	app.start();
    }
}
