package hf_Test;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
Ez az osztály felelős a gif jájlból való beolvasásáért,és több képé alakításáért


@author Mihály András







*/
public class KepBeolv {

	
	/**
	 * Esetleg változtatások esetére lett létrehozva, a beolvasás müveletének bövitését kivánt megkönyíteni
	 * 
	 * @param s egy gif file amelyet valahonan beolvastunk, ezt fogja majd átalakitani több BufferedImage-be
	 * 
	 * 
	 * @return tar egy arraylist mely a képeket tárolja
	 * @throws IOException a beolvaso miatt
	 *
	 * 
	 */
	

	public static ArrayList<BufferedImage> kepolv(File s) throws IOException {
		ArrayList<BufferedImage> tar = convert(s);
		return tar;

	}

	/**
	 * 
	 * A munka naggyát végző fügvény, szétszedi a gifben található képeket, adataikat egy fábarendezi majd egyenként beleírja őket az arrazlistbe
	 * 
	 * @param file a publikus fügvényből kapott file amit szétszed képekké
	 * @return tar  egy arraylist mely a képeket tárolja
	 */
	
	
	
	 static ArrayList<BufferedImage> convert(File file) {
		ArrayList<BufferedImage> tar = new ArrayList<BufferedImage>();
		try {
			String[] imageatt = new String[] { "imageLeftPosition", "imageTopPosition", "imageWidth", "imageHeight" };

			ImageReader reader = (ImageReader) ImageIO.getImageReadersByFormatName("gif").next();
			ImageInputStream ciis = ImageIO.createImageInputStream(file);
			reader.setInput(ciis, false);

			int noi = reader.getNumImages(true);
			BufferedImage master = null;

			for (int i = 0; i < noi; i++) {
				BufferedImage image = reader.read(i);
				IIOMetadata metadata = reader.getImageMetadata(i);

				Node tree = metadata.getAsTree("javax_imageio_gif_image_1.0");
				NodeList children = tree.getChildNodes();

				for (int j = 0; j < children.getLength(); j++) {
					Node nodeItem = children.item(j);

					if (nodeItem.getNodeName().equals("ImageDescriptor")) {
						Map<String, Integer> imageAttr = new HashMap<String, Integer>();

						for (int k = 0; k < imageatt.length; k++) {
							NamedNodeMap attr = nodeItem.getAttributes();
							Node attnode = attr.getNamedItem(imageatt[k]);
							imageAttr.put(imageatt[k], Integer.valueOf(attnode.getNodeValue()));
						}
						if (i == 0) {
							master = new BufferedImage(imageAttr.get("imageWidth"), imageAttr.get("imageHeight"),
									BufferedImage.TYPE_INT_ARGB);
						}
						master.getGraphics().drawImage(image, imageAttr.get("imageLeftPosition"),
								imageAttr.get("imageTopPosition"), null);
					}
				}
				tar.add(deepCopy(master));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tar;
	}

	 
	 
	 /**
	  *  Mivel a beolvasó fügvény mindig átirja azt a BufferedImage objektumot amin épp dolgozik a biztonságos mentés érdekében kell
	  *  egy copy fügvény melyel el lehet menteni az aktuális képet
	  * @param bi A mentendő kép
	  * @return a képpel megegyező BufferedImage objektum
	  */
	static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
