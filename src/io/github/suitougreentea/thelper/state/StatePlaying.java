package io.github.suitougreentea.thelper.state;

import io.github.suitougreentea.thelper.CommonRenderHelper;
import io.github.suitougreentea.thelper.GameField;
import io.github.suitougreentea.thelper.Mino;
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

    private int timer;
    private final int TIMER_READY = 10;
    private final int TIMER_GO = 10;
    private final int TIMER_WAITFORSPAWN = 0;
    private final int TIMER_WAITFORERASE = 0;
    private final int TIMER_WAITFORFALL = 0;
    private final int TIMER_AFTERFALLING = 0;
    private boolean readyGo = true;

    private static final int PHASE_WAITFORSPAWN = 0;
    private static final int PHASE_CONTROLLING = 1;
    private static final int PHASE_WAITFORERASE = 2;
    private static final int PHASE_WAITFORFALL = 3;
    private static final int PHASE_FALLING = 4;
    private static final int PHASE_AFTERFALLING = 5;
    private int phase = 1;

    private float fallCounter = 0;
    private float FALL_DELTA = 1/60f;
    private float softDropCounter = 0;
    private float SOFTDROP_DELTA = 1;
    private int lockdownTimer = 0;
    private int TIMER_LOCKDOWN = 60;
    private int forceLockdownTimer = 0;
    private int TIMER_FORCELOCKDOWN = 180;

    private int moveDirection = 0;
    private int firstMoveTimer = 0;
    private int TIMER_FIRSTMOVE = 5;
    private float moveCounter = 0;
    private float MOVE_DELTA = 0.5f;
    
    private float dropLineCounter = 0;
    private float DROPLINE_DELTA = 4;

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
	field = new GameField(30);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	    throws SlickException {
	Resource.field.draw();

	drawField(field, g);
	for(int i=0;i<6;i++){
	    drawNext(i);
	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
	    throws SlickException {
	Input i = gc.getInput();
	if(readyGo){
	    if(timer == 0) System.out.println("ready");
	    if(timer == TIMER_READY) System.out.println("go");
	    if(timer == TIMER_GO){
		timer = 0;
		readyGo = false;
	    } else {
		timer ++;
	    }
	} else {
	    if(i.isKeyPressed(Input.KEY_LEFT)){
		if(field.moveLeft()) lockdownTimer = 0;
		moveDirection = -1;
		firstMoveTimer = 0;
		moveCounter = 0;
	    }
	    if(i.isKeyPressed(Input.KEY_RIGHT)){
		if(field.moveRight()) lockdownTimer = 0;
		moveDirection = 1;
		firstMoveTimer = 0;
		moveCounter = 0;
	    }
	    if(i.isKeyDown(Input.KEY_LEFT)) {
		if(moveDirection == -1) {
		    if(firstMoveTimer == TIMER_FIRSTMOVE){
			moveCounter += MOVE_DELTA;
			while(moveCounter >= 1) {
			    if(field.moveLeft()) lockdownTimer = 0;
			    moveCounter -= 1;
			}
		    } else {
			firstMoveTimer++;
		    }
		}
	    }
	    if(i.isKeyDown(Input.KEY_RIGHT)) {
		if(moveDirection == 1) {
		    if(firstMoveTimer == TIMER_FIRSTMOVE){
			moveCounter += MOVE_DELTA;
			while(moveCounter >= 1) {
			    if(field.moveRight()) lockdownTimer = 0;
			    moveCounter -= 1;
			}
		    } else {
			firstMoveTimer++;
		    }
		}
	    }
	    switchhead:
		switch(phase) {
		case PHASE_WAITFORSPAWN:
		    if(timer == TIMER_WAITFORSPAWN) {
			timer = 0;
			phase = PHASE_CONTROLLING;

			field.newMino();
			fallCounter = 0;
			softDropCounter = 0;
			lockdownTimer = 0;
			forceLockdownTimer = 0;
			
			break switchhead;
		    }
		    timer++;
		    break;
		case PHASE_CONTROLLING:
		    if(i.isKeyDown(Input.KEY_DOWN)){
			softDropCounter += SOFTDROP_DELTA;
			while(softDropCounter >= 1) {
			    field.moveDown();
			    softDropCounter -= 1;
			}
		    }
		    if(i.isKeyPressed(Input.KEY_UP)) {
			field.hardDrop();
			timer = 0;
			if(field.judgeEraseLine().size() == 0) {
			    phase = PHASE_WAITFORSPAWN;
			} else {
			    phase = PHASE_WAITFORERASE;
			}
			break switchhead;
		    }
		    if(i.isKeyPressed(Input.KEY_X)) {
			field.rotateCW();
			lockdownTimer = 0;
		    }
		    if(i.isKeyPressed(Input.KEY_Z)) {
			field.rotateCCW();
			lockdownTimer = 0;
		    }

		    // freefall
		    fallCounter += FALL_DELTA;
		    while(fallCounter >= 1) {
			field.moveDown();
			fallCounter -= 1;
		    }

		    // lockdown
		    if(field.getCurrentMinoY() == field.getGhostY()) {
			if(lockdownTimer == TIMER_LOCKDOWN || forceLockdownTimer == TIMER_FORCELOCKDOWN) {
			    field.hardDrop();
			    timer = 0;
			    if(field.judgeEraseLine().size() == 0) {
				phase = PHASE_WAITFORSPAWN;
			    } else {
				phase = PHASE_WAITFORERASE;
			    }
			    break switchhead;
			}
			lockdownTimer++;
			forceLockdownTimer++;
		    }

		    break;
		case PHASE_WAITFORERASE:
		    if(timer == TIMER_WAITFORERASE) {
			timer = 0;
			phase = PHASE_WAITFORFALL;
			field.eraseLine();
			break switchhead;
		    }
		    timer++;
		    break;
		case PHASE_WAITFORFALL:
		    if(timer == TIMER_WAITFORFALL) {
			timer = 0;
			phase = PHASE_FALLING;
			break switchhead;
		    }
		    timer++;
		    break;
		case PHASE_FALLING:
		    dropLineCounter += DROPLINE_DELTA;
		    while(dropLineCounter >= 1) {
			if(field.dropOneLine()){
			    timer = 0;
			    phase = PHASE_AFTERFALLING;
			    dropLineCounter = 0;
			    break switchhead;
			}
			dropLineCounter -= 1;
		    }
		    break;
		case PHASE_AFTERFALLING:
		    if(timer == TIMER_AFTERFALLING) {
			timer = 0;
			phase = PHASE_WAITFORSPAWN;
			break switchhead;
		    }
		    timer++;
		    break;
		}
	}
	i.clearKeyPressedRecord();
    }

    @Override
    public int getID() {
	return stateId;
    }

    private void drawField(GameField field, Graphics g){
	g.pushTransform();
	g.translate(100, 100);
	CommonRenderHelper.drawField(field.getField());
	if(phase == PHASE_CONTROLLING) {
	    CommonRenderHelper.drawMino(field.getCurrentMino(), field.getCurrentMinoX() * 20, -field.getCurrentMinoY() * 20 + 380, 0);
	    CommonRenderHelper.drawMino(field.getCurrentMino(), field.getCurrentMinoX() * 20, -field.getGhostY() * 20 + 380, 0, 0.5f);
	}
	g.popTransform();
	g.drawString(
		String.format(
			"Phase: %d\nTimer: %d\nFall: %f\nSoft: %f\nLock: %d\nForce: %d\nDirection: %d\nFirstMove: %d\nMove: %f\nDrop: %f",
			phase,
			timer,
			fallCounter,
			softDropCounter,
			lockdownTimer,
			forceLockdownTimer,
			moveDirection,
			firstMoveTimer,
			moveCounter,
			dropLineCounter
			), 480, 0);
    }

    private void drawNext(int index){
	Mino mino = field.getNextMino(index);
	/* offset */
	CommonRenderHelper.drawMino(mino, 0, 48 * index + 48, 1);
    }

    private void afterDrop() {
    }

}
