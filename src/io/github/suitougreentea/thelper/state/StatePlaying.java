package io.github.suitougreentea.thelper.state;

import io.github.suitougreentea.thelper.CommonRenderHelper;
import io.github.suitougreentea.thelper.GameField;
import io.github.suitougreentea.thelper.Resource;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
	// TODO Auto-generated method stub

    }

    @Override
    public int getID() {
	return stateId;
    }
   
    private void drawField(GameField field, Graphics g){
	g.translate(120, 100);
	g.pushTransform();
	CommonRenderHelper.drawField(field.getField());
	g.popTransform();
    }
	    
}
