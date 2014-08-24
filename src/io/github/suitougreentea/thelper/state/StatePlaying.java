package io.github.suitougreentea.thelper.state;

import io.github.suitougreentea.thelper.CommonRenderHelper;
import io.github.suitougreentea.thelper.GameField;
import io.github.suitougreentea.thelper.Resource;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StatePlaying extends BasicGameState {

    private int stateId; 
    
    private GameField field;
    
    public StatePlaying(int i) {
	this.stateId = i;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
	    throws SlickException {

    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg)
	    throws SlickException {
	field = new GameField(20);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	    throws SlickException {
	Resource.field.draw();
	
	drawField(field, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
	    throws SlickException {
	Input i = gc.getInput();
	if(i.isKeyPressed(Input.KEY_LEFT)) field.moveLeft();
	if(i.isKeyPressed(Input.KEY_RIGHT)) field.moveRight();
	if(i.isKeyPressed(Input.KEY_DOWN)) field.moveDown();
	if(i.isKeyPressed(Input.KEY_UP)) field.hardDrop();
	if(i.isKeyPressed(Input.KEY_X)) field.rotateCW();
	if(i.isKeyPressed(Input.KEY_Z)) field.rotateCCW();
	i.clearKeyPressedRecord();
    }

    @Override
    public int getID() {
	return stateId;
    }
   
    private void drawField(GameField field, Graphics g){
	g.translate(100, 100);
	g.pushTransform();
	CommonRenderHelper.drawField(field.getField());
	CommonRenderHelper.drawMino(field.getCurrentMino(), field.getCurrentMinoX(), field.getCurrentMinoY(), 0);
	CommonRenderHelper.drawMino(field.getCurrentMino(), field.getCurrentMinoX(), field.getGhostY(), 0, 0.5f);
	CommonRenderHelper.drawMino(field.getNextMino(1), 0, 0, 1);
	g.popTransform();
    }
	    
}
