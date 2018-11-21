package hf_Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class Rgbcont_TEST {
	public Rgbcont t1;
	
	
	
	@Before
	public void setUp() throws Exception {
		t1 = new Rgbcont(1,2,3);
		
	}




	@Test
	public void testGet_val() {
		assertEquals(6, t1.get_val());
	}

}
