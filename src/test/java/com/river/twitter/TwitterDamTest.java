package com.river.twitter;


import java.util.Random;

import junit.framework.TestCase;
import org.junit.*;

import com.river.Dam;

public class TwitterDamTest extends TestCase {
	
	
	@Test
	public void testDam(){
		
		Dam dam = new TwitterDam();
		dam.start();
		Random rand = new Random(System.currentTimeMillis());
		for(int i=0; i <= 100; i++){
			Double d = rand.nextDouble();
			dam.log(d.toString());
		}
		
		dam.cleanup();
		
	}

}
