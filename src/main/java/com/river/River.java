package com.river;


public class River extends Thread {
	
	private ThreadState state;
	
	protected Dam dam;
	
	public boolean isRunning(){
		return this.state == ThreadState.RUNNING;
	}
	
}
