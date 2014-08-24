package io.github.suitougreentea.thelper;

import java.util.List;

public class RotationSpinRuleStandard {
    // L->0, 0->R, R->X, X->L
    private int[][] defaultOffset3x3CW = {
	    {1,0}, {0,-1}, {-1,0}, {0,1}
    };
    private int[][][] superOffset3x3CW = {
	    {{ 0, 0},{-1, 0},{-1,-1},{ 0, 2},{-1, 2}},
	    {{ 0, 0},{-1, 0},{-1, 1},{ 0,-2},{-1,-2}},
	    {{ 0, 0},{ 1, 0},{ 1,-1},{ 0, 2},{ 1, 2}},
	    {{ 0, 0},{ 1, 0},{ 1, 1},{ 0,-2},{ 1,-2}},
    };
    private int[][][] superOffset4x4CW = {
	    {{ 0, 0},{ 1, 0},{-2, 0},{ 1,-2},{-2, 1}},
	    {{ 0, 0},{-2, 0},{ 1, 0},{-2,-1},{ 1, 2}},
	    {{ 0, 0},{-1, 0},{ 2, 0},{-1, 2},{ 2,-1}},
	    {{ 0, 0},{ 2, 0},{-1, 0},{ 2, 1},{-1,-2}},
    };
    
    // R->0, X->R, L->X, 0->L
    private int[][] defaultOffset3x3CCW = {
	    {0,1}, {1,0}, {0,-1}, {-1,0}
    };
    private int[][][] superOffset3x3CCW = {
	    {{ 0, 0},{ 1, 0},{ 1,-1},{ 0, 2},{ 1, 2}},
	    {{ 0, 0},{-1, 0},{-1, 1},{ 0,-2},{-1,-2}},
	    {{ 0, 0},{-1, 0},{-1,-1},{ 0, 2},{-1, 2}},
	    {{ 0, 0},{ 1, 0},{ 1, 1},{ 0,-2},{ 1,-2}},
    };
    private int[][][] superOffset4x4CCW = {
	    {{ 0, 0},{ 2, 0},{-1, 0},{ 2, 1},{-1,-2}},
	    {{ 0, 0},{ 1, 0},{-2, 0},{ 1,-2},{-2, 1}},
	    {{ 0, 0},{-2, 0},{ 1, 0},{-2,-1},{ 1, 2}},
	    {{ 0, 0},{-1, 0},{ 2, 0},{-1, 2},{ 2,-1}},
    };
    
    public RotationResult getOffsetCW(List<Block[]> field, Mino mino, int minoX, int minoY){
	int dx, dy;
	int[][][] superOffset;
	if(mino.getMinoId() == Mino.MINO_I || mino.getMinoId() == Mino.MINO_O){
	    dx = 0;
	    dy = 0;
            superOffset = superOffset4x4CW;
	} else {
            dx = defaultOffset3x3CW[mino.getRotationState()][0];
            dy = defaultOffset3x3CW[mino.getRotationState()][1];
            superOffset = superOffset3x3CW;
	}
	for(int i=0;i<5;i++){
	    int ox = dx + superOffset[mino.getRotationState()][i][0];
	    int oy = dy + superOffset[mino.getRotationState()][i][1];
	    if(!CommonGameHelper.checkHit(field, mino, minoX + ox, minoY + oy)) return new RotationResult(true, ox, oy);
	}
	return new RotationResult(false, 0, 0);
    }

    public RotationResult getOffsetCCW(List<Block[]> field, Mino mino, int minoX, int minoY){
	int dx, dy;
	int[][][] superOffset;
	if(mino.getMinoId() == Mino.MINO_I || mino.getMinoId() == Mino.MINO_O){
	    dx = 0;
	    dy = 0;
            superOffset = superOffset4x4CCW;
	} else {
	    dx = defaultOffset3x3CCW[mino.getRotationState()][0];
	    dy = defaultOffset3x3CCW[mino.getRotationState()][1];
            superOffset = superOffset3x3CCW;
	}
	for(int i=0;i<5;i++){
	    int ox = dx + superOffset[mino.getRotationState()][i][0];
	    int oy = dy + superOffset[mino.getRotationState()][i][1];
	    if(!CommonGameHelper.checkHit(field, mino, minoX + ox, minoY + oy)) return new RotationResult(true, ox, oy);
	}
	return new RotationResult(false, 0, 0);
    }
}
