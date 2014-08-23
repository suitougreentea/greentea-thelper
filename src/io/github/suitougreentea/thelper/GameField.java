package io.github.suitougreentea.thelper;

import java.util.ArrayList;
import java.util.List;


public class GameField {
    private List<Block[]> field = new ArrayList<Block[]>();
    private Mino currentMino;
    
    public GameField(int defaultHeight){
	if(defaultHeight < 20) defaultHeight = 20;
	for(int i=0;i<defaultHeight;i++) field.add(new Block[10]);
	
	setFieldBlock(0, 0, new Block(1));
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
}
