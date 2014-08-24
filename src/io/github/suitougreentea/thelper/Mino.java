package io.github.suitougreentea.thelper;

public class Mino {
    public static final int MINO_I = 0; 
    public static final int MINO_J = 1; 
    public static final int MINO_L = 2; 
    public static final int MINO_O = 3; 
    public static final int MINO_S = 4; 
    public static final int MINO_T = 5; 
    public static final int MINO_Z = 6; 
    private int minoId; 
    private Block[][] block;
    private int rotationState;

    public Mino(int minoId, int blockId){
	this.minoId = minoId;
	block = new Block[6][6];
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		if(minoList[minoId][iy][ix]) block[iy][ix] = new Block(blockId);
	    }
	}
    }
    
    public Block getBlock(int x, int y){
	return block[y][x];
    }
    
    public int getRotationState(){
	return rotationState;
    }
    
    public int getMinoId(){
	return minoId;
    }
    
    public void rotateCW(){
	// Rotate array CCW
	Block[][] result = new Block[6][6];
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		result[iy][ix] = block[ix][5 - iy];
	    }
	}
	block = result;
	rotationState = (rotationState + 1) % 4;
    }

    public void rotateCCW(){
	// Rotate array CW
	Block[][] result = new Block[6][6];
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		result[iy][ix] = block[5 - ix][iy];
	    }
	}
	block = result;
	rotationState = (rotationState + 3) % 4;
    }
    
    // All mino array are vertically flipped (because younger index is bottom, older index is top)
    private static boolean[][][] minoList = {
	{ //I
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false, true, true, true, true,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	},
	{ //J
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false, true, true, true,false,false},
	    {false, true,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	},
	{ //L
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false, true, true, true,false,false},
	    {false,false,false, true,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	},
	{ //O
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false,false, true, true,false,false},
	    {false,false, true, true,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	},
	{ //S
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false, true, true,false,false,false},
	    {false,false, true, true,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	},
	{ //T
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false, true, true, true,false,false},
	    {false,false, true,false,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	},
	{ //Z
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	    {false,false, true, true,false,false},
	    {false, true, true,false,false,false},
	    {false,false,false,false,false,false},
	    {false,false,false,false,false,false},
	}
    };
}
