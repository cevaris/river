package com.river;


public class River extends Thread {
	
	private RiverState state;
	
	protected Dam dam;
	
	public void setState(RiverState state) {
		this.state = state;
	}
	
	public boolean isRunning(){
		return this.state == RiverState.RUNNING;
	}
	
}
