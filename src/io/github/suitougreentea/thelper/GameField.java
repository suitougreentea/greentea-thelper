package io.github.suitougreentea.thelper;

import java.util.ArrayList;
import java.util.List;


public class GameField {
    private List<Block[]> field = new ArrayList<Block[]>();
    private Mino currentMino;
    private int currentMinoX, currentMinoY;
    
    public GameField(int defaultHeight){
	if(defaultHeight < 20) defaultHeight = 20;
	for(int i=0;i<defaultHeight;i++) field.add(new Block[10]);
	
	setFieldBlock(0, 0, new Block(1));
	currentMino = new Mino(5,1);
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
    }

    public void rotateCCW(){
	currentMino.rotateCCW();
    }
    
    public boolean checkHit(){
	for(int iy=0;iy<6;iy++){
	    for(int ix=0;ix<6;ix++){
		int jx = ix + currentMinoX;
		int jy = iy + currentMinoY;
		if(currentMino.getBlock(ix, iy) != null){
		    if(jx < 0 || 10 <= jx || jy < 0 || field.size() <= jy || getFieldBlock(jx, jy) != null) return true;
		}
	    }
	}
	return false;
    }
}
