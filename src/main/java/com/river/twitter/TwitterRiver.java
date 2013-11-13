package com.river.twitter;


import java.util.ArrayDeque;
import java.util.Deque;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.json.DataObjectFactory;

import com.river.Dam;
import com.river.River;
import com.river.RiverState;


public class TwitterRiver extends River {
	
	
	int counter = Integer.MAX_VALUE;
	TwitterStream twitterStream;
	private Dam dam;	
	
	
	public void setDam(Dam dam) {
		this.dam = dam;
		setupExitHook();
	}
	
	private void setupExitHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() { 
		    	System.out.println("Shutting down...");
		    	if(dam != null) dam.cleanup();
		    	System.out.println("Done!");
		    }
		});
	}
	
	@Override
	public void run() {
		setState(RiverState.RUNNING);
		
	    StatusListener listener = new StatusListener() {
    	
			public void onStatus(Status status) {
				if(counter < 0) {
					setState(RiverState.STOPPED);
				} else counter--;
				
				String rawJSON = DataObjectFactory.getRawJSON(status);
				dam.log(rawJSON);
			}
	
			public void onException(Exception ex) { ex.printStackTrace(); }
			public void onTrackLimitationNotice(int arg0) {
				System.out.println("Limit Notice: "+arg0);
			}
			public void onStallWarning(StallWarning arg0) {
				System.out.println(arg0);
			}
			public void onScrubGeo(long arg0, long arg1)  {}
			public void onDeletionNotice(StatusDeletionNotice arg0) {}
	        
	    };
	    this.twitterStream = new TwitterStreamFactory().getInstance();
	    this.twitterStream.addListener(listener);
	    this.twitterStream.sample();

	    while(this.isRunning()){
			try { Thread.sleep(1000L); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	    
	    twitterStream.cleanUp();
		twitterStream.shutdown();
	    dam.cleanup();
		
	}

}
