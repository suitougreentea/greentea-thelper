package io.github.suitougreentea.thelper;

import java.util.List;

import org.newdawn.slick.Color;

public class CommonRenderHelper {
    private static int[] blockSize = {20, 16, 14, 12};
    
    public static void drawField(List<Block[]> field){
	for(int iy=0;iy<20;iy++){
	    for(int ix=0;ix<10;ix++){
		drawBlock(field.get(iy)[ix], ix * 20, -iy * 20 + 380, 0);
	    }
	}
    }
    
    public static void drawMino(Mino mino, int x, int y, int size) {
	drawMino(mino, x, y, size, 1f);
    }
    
    public static void drawMino(Mino mino, int x, int y, int size, float transparency) {
	int s = blockSize[size];
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		drawBlock(mino.getBlock(ix, iy), x + ix * s, y - iy * s, size, transparency);
	    }
	}
    }

    public static void drawBlock(Block block, int x, int y, int size){
	drawBlock(block, x, y, size, 1f);
    }
    
    public static void drawBlock(Block block, int x, int y, int size, float transparency){
	if(block == null) return;
	int sbx = block.getId() % 16;
	int sby = block.getId() / 16;
	int s = blockSize[size];
	int sx = sbx * s;
	int sy = sby * s;
	Resource.blockDefault[size].draw(x, y, x+s, y+s, sx, sy, sx+s, sy+s, new Color(1f, 1f, 1f, transparency));
    }
}
