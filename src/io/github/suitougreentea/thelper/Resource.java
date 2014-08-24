package io.github.suitougreentea.thelper;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resource {
    public static Image field;
    public static Image[] blockDefault;
    
    public static void Load() {
	field = loadImage("res/field.png");
	blockDefault = loadBlockImage("default");
    }
   
    public static Image[] loadBlockImage(String name) {
	Image[] result = new Image[4];
	for(int i=0;i<4;i++){
	    result[i] = loadImage("res/block/" + name + "-" + i + ".png");
	}
	return result;
    }
    
    public static Image loadImage(String path) {
	try {
	    return new Image(path);
	} catch (SlickException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
