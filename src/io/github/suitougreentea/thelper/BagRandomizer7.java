package io.github.suitougreentea.thelper;

import java.util.Random;

public class BagRandomizer7 {
    protected Random random;
    private boolean[] bag;
    private int bagRemain = 7;

    public BagRandomizer7(Random random){
	this.random = random;
	resetBag();
    }
    
    private void resetBag(){
	this.bag = new boolean[7];
	this.bagRemain = 7;
    }
    
    public int next(){
	if(bagRemain == 0) resetBag();
	int n = random.nextInt(bagRemain);
	int i = 0;
	while(true){
	   if(!bag[i]){
	       if(n == 0) {
		   bag[i] = true;
		   bagRemain--;
		   return i;
	       }
	       n--;
	   }
           i++;
	}
    }
}
