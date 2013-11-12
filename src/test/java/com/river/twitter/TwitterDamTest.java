package com.river.twitter;


import junit.framework.TestCase;
import org.junit.*;

import com.river.Dam;

public class TwitterDamTest extends TestCase {
	
	
	@Test
	public void testDam(){
		
		Dam dam = new TwitterDam();
		dam.log("test");
		dam.cleanup();
		
	}

}
