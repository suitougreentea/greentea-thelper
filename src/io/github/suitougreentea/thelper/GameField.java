package io.github.suitougreentea.thelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameField {
    private List<Block[]> field = new ArrayList<Block[]>();
    private Mino currentMino;
    private Mino holdMino;
    private Mino[] nextMino;
    private int currentMinoX, currentMinoY;
    private RotationSpinRuleStandard rotationSpinRule = new RotationSpinRuleStandard();
    private BagRandomizer7 randomizer = new BagRandomizer7(new Random());
    
    public GameField(int defaultHeight){
	if(defaultHeight < 20) defaultHeight = 20;
	for(int i=0;i<defaultHeight;i++) field.add(new Block[10]);
	
	nextMino = new Mino[6];
	for(int i=0;i<6;i++) nextMino[i] = new Mino(randomizer.next(), 8);
	newMino();
    }

    public List<Block[]> getField() {
        return field;
    }
    
    public Block getFieldBlock(int x, int y){
	return field.get(y)[x];
    }
    
    public void setFieldBlock(int x, int y, Block block){
	field.get(y)[x] = block;
    }

    public Mino getCurrentMino() {
        return currentMino;
    }

    public void setCurrentMino(Mino currentMino) {
        this.currentMino = currentMino;
    }

    public int getCurrentMinoX() {
        return currentMinoX;
    }

    public void setCurrentMinoX(int currentMinoX) {
        this.currentMinoX = currentMinoX;
    }

    public int getCurrentMinoY() {
        return currentMinoY;
    }
    
    public int getGhostY() {
	int result = currentMinoY;
	while(!CommonGameHelper.checkHit(field, currentMino, currentMinoX, result)) result--;
	return result + 1;
    }

    public void setCurrentMinoY(int currentMinoY) {
        this.currentMinoY = currentMinoY;
    }
    
    public void moveRight(){
	currentMinoX ++;
	if(checkHit()) currentMinoX--;
    }

    public void moveLeft(){
	currentMinoX --;
	if(checkHit()) currentMinoX++;
    }

    public void moveUp(){
	currentMinoY ++;
	if(checkHit()) currentMinoY--;
    }

    public void moveDown(){
	currentMinoY --;
	if(checkHit()) currentMinoY++;
    }
    
    public void rotateCW(){
	currentMino.rotateCW();
	RotationResult result = rotationSpinRule.getOffsetCW(field, currentMino, currentMinoX, currentMinoY);
	if(result.isSuccess()) {
	    currentMinoX += result.getOffsetX();
	    currentMinoY += result.getOffsetY();
	} else {
	    currentMino.rotateCCW();
	}
    }

    public void rotateCCW(){
	currentMino.rotateCCW();
	RotationResult result = rotationSpinRule.getOffsetCCW(field, currentMino, currentMinoX, currentMinoY);
	if(result.isSuccess()) {
	    currentMinoX += result.getOffsetX();
	    currentMinoY += result.getOffsetY();
	} else {
	    currentMino.rotateCW();
	}
    }
    
    public boolean checkHit(){
	return CommonGameHelper.checkHit(field, currentMino, currentMinoX, currentMinoY);
    }

    public void hardDrop() {
	int x = currentMinoX;
	int y = getGhostY();
	
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		int jx = x + ix;
		int jy = y + iy;
		Block block = currentMino.getBlock(ix, iy);
		if(block != null){
		    setFieldBlock(jx, jy, block);
		}
	    }
	}
	
	newMino();
    }
    
    public void newMino(){
	currentMinoX = 3;
	currentMinoY = 15;
	currentMino = nextMino[0];
	for(int i=0;i<5;i++) nextMino[i] = nextMino[i+1];
	nextMino[5] = new Mino(randomizer.next(), 8);
    }
    
    public Mino getNextMino(int index){
	return nextMino[index];
    }
}
