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
	currentMino = new Mino(0,1);
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
    }

    public void moveLeft(){
	currentMinoX --;
    }

    public void moveUp(){
	currentMinoY ++;
    }

    public void moveDown(){
	currentMinoY --;
    }
}
