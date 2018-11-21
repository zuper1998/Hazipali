package hf_Test;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.im.InputContext;
import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class GraphTest { // dont forget to set IS_THIS_A_TEST to 1
	Graph g = new Graph();
	Robot bot;
	ArrayList<Character> a;

	@Before
	public void setUp() throws Exception {
		a = new ArrayList<Character>();
		a.add('e');
		a.add('e');
		a.add('e');
		bot = new Robot();

	}

	@Test
	public void testConvertSA() {
		String test_str = Graph.convertSA(a);
		assertEquals("eee", test_str);

	}

	@Test(expected = NullAnimExepton.class)
	public void testGraph() throws InterruptedException, NullAnimExepton {
		g.graph();

	}

	//800 choose (aka txt) 900 for start
	@Test
	public void testUi_main_choose_button() {
		g.ui_main();

		bot.mouseMove(800, 50);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);

		assertEquals('@', g.cont.get(0, 0, 0));
	

	}
	@Test (timeout = 5000)
	public void testUi_main_start_button() {
		g.ui_main();

		bot.mouseMove(800, 50);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseMove(900, 50);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		

	}
	@Test
	public void testUi_main_Save_button() {
		g.ui_main();

		bot.mouseMove(700, 50);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		File t_fil = new File(g.test_file_loc);
		assertTrue(t_fil.exists());

	}
	@Test
	public void testUi_main_load_button() {
		g.ui_main();

		bot.mouseMove(700, 50);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		
		bot.mouseMove(780, 50);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);
		assertEquals('l', g.cont.get(0, 0, 0));

	}
}
