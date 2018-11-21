package hf_Test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TransformatorTest {
	BufferedImage test = null;
	ArrayList<BufferedImage> t_arr;
	Rgbcont[][] trgb;

	@Before
	public void setUp() throws Exception {

		// image

		test = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D t_g = test.createGraphics();
		t_g.setPaint(new Color(0, 0, 0));
		t_g.fillRect(20, 20, test.getWidth(), test.getHeight());
		t_arr = new ArrayList<BufferedImage>();
		t_arr.add(test);

		// rgbcont
		trgb = Transformator.alakito(test);

	}

	@Test
	public void testAlakito() {
		Rgbcont[][] a = Transformator.alakito(test);

		assertEquals(0, a[5][6].get_val());

	}

	@Test
	public void testCharacterizator() {
		char a = Transformator.characterizator(trgb, 2, 2, 0, 0);
		assertEquals('@', a);

	}

	@Test
	public void testSub_converter() {
		char a = Transformator.sub_converter(test).get(5).get(2);
		assertEquals('@', a);

	}

	@Test
	public void testConverter() {
		Monster_container t2 = Transformator.converter(t_arr);
		char t3 = t2.get(0).get(1).get(2);
		assertEquals('@', t3);

	}

}
