package io.github.suitougreentea.thelper;

public class MinoRuleStandard {
    private float[][] offset = {
	    {0, -0.5f},
	    {0.5f, -1},
	    {0.5f, -1},
	    {0, -1},
	    {0.5f, -1},
	    {0.5f, -1},
	    {0.5f, -1}
    };

    private int[][] spawnPosition = {
	    {2, 16},
	    {2, 16},
	    {2, 16},
	    {2, 16},
	    {2, 16},
	    {2, 16},
	    {2, 16},
    };
    
    private int[] color = {5, 6, 2, 3, 4, 7, 1};
    private int[] spawnRotationState = {0, 0, 0, 0, 0, 0, 0};

    public float getOffsetX(int minoId) {
	return offset[minoId][0];
    }

    public float getOffsetY(int minoId) {
	return offset[minoId][1];
    }
    
    public int getColor(int minoId){
	return color[minoId];
    }
    
    public int getSpawnRotationState(int minoId){
	return spawnRotationState[minoId];
    }
    
    public int getSpawnPositionX(int minoId) {
	return spawnPosition[minoId][0];
    }

    public int getSpawnPositionY(int minoId) {
	return spawnPosition[minoId][1];
    }
}
