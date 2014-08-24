package io.github.suitougreentea.thelper;

import java.util.List;

public class CommonRenderHelper {
    public static void drawField(List<Block[]> field){
	for(int iy=0;iy<20;iy++){
	    for(int ix=0;ix<10;ix++){
		drawBlock(field.get(iy)[ix], ix * 20, -iy * 20 + 380, 0);
	    }
	}
    }
    
    public static void drawMino(Mino mino, int x, int y) {
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		drawBlock(mino.getBlock(ix, iy), (ix + x) * 20, -(iy + y) * 20 + 380, 0);
	    }
	}
    }

    public static void drawBlock(Block block, int x, int y, int size){
	if(block == null) return;
	int sbx = block.getId() % 16;
	int sby = block.getId() / 16;
	switch(size){
	case 0:
	    int sx = sbx * 20;
	    int sy = sby * 20;
	    Resource.block.draw(x, y, x+20, y+20, sx, sy, sx+20, sy+20);
	    break;
	}
    }

}
