package twitter;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.json.DataObjectFactory;

import com.river.Dam;
import com.river.River;


public class TwitterRiver extends River {
	
	
	final Dam dam = new Dam();	
	
	public TwitterRiver() {
		
	}
	
	
	@Override
	public void run() {
	    StatusListener listener = new StatusListener() {
    	
		public void onException(Exception ex) {
			ex.printStackTrace();				
		}
		
		public void onStatus(Status status) {
//			try {
				String rawJSON = DataObjectFactory.getRawJSON(status);
				System.out.println(rawJSON);
				dam.log(rawJSON);
//				br.write(status.getUser().getName() + " : " + status.getText()+"\n");
//			} catch (IOException ex){
//				ex.printStackTrace();
//			}
		}

		public void onTrackLimitationNotice(int arg0) {}
		public void onStallWarning(StallWarning arg0) {}
		public void onScrubGeo(long arg0, long arg1)  {}
		public void onDeletionNotice(StatusDeletionNotice arg0) {}
        
    };
    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
    twitterStream.addListener(listener);
    twitterStream.sample();
		
	}
	
	

}
