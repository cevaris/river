package com.river.twitter;


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
	
	
	int counter = 10;
	TwitterStream twitterStream;
	private Dam dam;	
	
	public void setDam(Dam dam) {
		this.dam = dam;
	}
	
	@Override
	public void run() {
		setState(RiverState.RUNNING);
		
	    StatusListener listener = new StatusListener() {
    	
			public void onStatus(Status status) {
				
				if(counter < 0) {
					setState(RiverState.STOPPED);
				} else counter--;
				
				
				
	//			try {
					String rawJSON = DataObjectFactory.getRawJSON(status);
					dam.log(rawJSON);
	//				br.write(status.getUser().getName() + " : " + status.getText()+"\n");
	//			} catch (IOException ex){
	//				ex.printStackTrace();
	//			}
					
			}
	
			public void onException(Exception ex) { ex.printStackTrace(); }
			public void onTrackLimitationNotice(int arg0) {}
			public void onStallWarning(StallWarning arg0) {}
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
