package io.github.suitougreentea.thelper.state;

import io.github.suitougreentea.thelper.Resource;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateLoading extends BasicGameState {
    
    private int stateId; 
    
    public StateLoading(int i) {
	this.stateId = i;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
	    throws SlickException {
	// TODO Auto-generated method stub
	Resource.Load();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	    throws SlickException {
	// TODO Auto-generated method stub

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

}
