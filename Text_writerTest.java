package hf_Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

public class Text_writerTest {
	JTextArea jta = new JTextArea();
	ArrayList<String> str_t = new ArrayList<String>();
	Thread t1;
	
	
	@Before
	public void setUp() throws Exception {
		for (int i =0;i<10;i++){
		str_t.add(i+"r"+i+i+"r"+i+i+"r"+i+i+"r"+i+i+"r"+i);
	}
		t1= new Thread(new Text_writer(jta, str_t));

	}

	
	
	@Test (timeout=500)
	public void testRun() throws InterruptedException {
		
		
		
		t1.start();
		String test1 = jta.getText();
		Thread.sleep(200);
		String test2 = jta.getText();
		assertFalse(test1.equals(test2));
		
		
		
	}

}
