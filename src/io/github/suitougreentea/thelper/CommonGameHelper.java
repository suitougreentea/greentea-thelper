package io.github.suitougreentea.thelper;

import java.util.List;

public class CommonGameHelper {
    public static boolean checkHit(List<Block[]> field, Mino mino, int minoX, int minoY){
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		int jx = ix + minoX;
		int jy = iy + minoY;
		if(mino.getBlock(ix, iy) != null){
		    if(jx < 0 || 10 <= jx || jy < 0 || field.size() <= jy || field.get(jy)[jx] != null) return true;
		}
	    }
	}
	return false;
    }
}
