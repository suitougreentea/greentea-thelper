package io.github.suitougreentea.thelper;
import io.github.suitougreentea.thelper.state.StateLoading;
import io.github.suitougreentea.thelper.state.StatePlaying;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameTHelper extends StateBasedGame {

    public GameTHelper(String name) {
	super(name);
	this.addState(new StateLoading(0));
	this.addState(new StatePlaying(1));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	this.getState(0).init(gc, this);
	this.getState(1).init(gc, this);
	this.enterState(1);
    }

}
