package io.github.suitougreentea.thelper;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resource {
    public static Image field;
    public static Image block;
    
    public static void Load() {
	field = LoadImage("res/field.png");
	block = LoadImage("res/block/default.png");
    }
    
    public static Image LoadImage(String path) {
	try {
	    return new Image(path);
	} catch (SlickException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
