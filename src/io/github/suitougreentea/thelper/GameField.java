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
    private MinoRuleStandard minoRule = new MinoRuleStandard();
    private List<Integer> eraseLineList;
    private boolean alreadyHolded = false;
    
    public GameField(int defaultHeight){
	if(defaultHeight < 20) defaultHeight = 20;
	for(int i=0;i<defaultHeight;i++) field.add(new Block[10]);
	
	nextMino = new Mino[6];
	for(int i=0;i<6;i++) {
	    int minoId = randomizer.next();
	    nextMino[i] = new Mino(minoId, minoRule.getColor(minoId), minoRule.getSpawnRotationState(minoId));
	}
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
    
    public boolean moveRight(){
	currentMinoX ++;
	if(checkHit()) {
	    currentMinoX--;
	    return false;
	}
	return true;
    }

    public boolean moveLeft(){
	currentMinoX --;
	if(checkHit()) {
	    currentMinoX++;
	    return false;
	}
	return true;
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
    }
    
    public void newMino(){
	currentMino = nextMino[0];
	int minoId = currentMino.getMinoId();
	currentMinoX = minoRule.getSpawnPositionX(minoId);
	currentMinoY = minoRule.getSpawnPositionY(minoId);
	for(int i=0;i<5;i++) nextMino[i] = nextMino[i+1];
	int newMinoId = randomizer.next();
	nextMino[5] = new Mino(newMinoId, minoRule.getColor(newMinoId), minoRule.getSpawnRotationState(newMinoId));
	alreadyHolded = false;
    }
    
    public Mino getNextMino(int index){
	return nextMino[index];
    }
    
    public List<Integer> judgeEraseLine(){
	eraseLineList = new ArrayList<Integer>();
	for(int iy=0;iy<field.size();iy++){
	    int num = 0;
	    for(int ix=0;ix<10;ix++){
		if(getFieldBlock(ix, iy) != null) num++;
	    }
	    if(num == 10) {
		eraseLineList.add(iy);
	    }
	}
	return eraseLineList;
    }
    
    // Must call judgeEraseLine() before calling eraseLine()
    public void eraseLine(){
	for(int iy=0;iy<eraseLineList.size();iy++){
	    for(int ix=0;ix<10;ix++){
		setFieldBlock(ix, eraseLineList.get(iy), null);
	    }
	}
    }
   
    // returns if finished dropping
    public boolean dropOneLine(){
	for(int iy=eraseLineList.get(0);iy<field.size();iy++){
	    for(int ix=0;ix<10;ix++){
		if(iy == field.size() - 1) {
		    setFieldBlock(ix, iy, null);
		} else {
		    setFieldBlock(ix, iy, getFieldBlock(ix, iy + 1));
		}
	    }
	}
	eraseLineList.remove(0);
	if(eraseLineList.size() == 0){
	    return true;
	} else {
	    for(int i=0;i<eraseLineList.size();i++){
		eraseLineList.set(i, eraseLineList.get(i) - 1);
	    }
	    return false;
	}
    }
    
    public void hold(){
	if(!alreadyHolded){
	    if(holdMino == null){
		int minoId = currentMino.getMinoId();
		holdMino = new Mino(minoId, minoRule.getColor(minoId), minoRule.getSpawnRotationState(minoId));
		newMino();
	    } else {
		int minoId = currentMino.getMinoId();
		Mino temp = new Mino(minoId, minoRule.getColor(minoId), minoRule.getSpawnRotationState(minoId));
		currentMino = holdMino;
		holdMino = temp;

                minoId = currentMino.getMinoId();
                currentMinoX = minoRule.getSpawnPositionX(minoId);
                currentMinoY = minoRule.getSpawnPositionY(minoId);
	    }
	    alreadyHolded = true;
	}
    }

    public Mino getHoldMino() {
        return holdMino;
    }

    public MinoRuleStandard getMinoRule() {
        return minoRule;
    }
}
