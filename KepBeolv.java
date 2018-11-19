import java.awt.image.BufferedImage;
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


public class KepBeolv {
	public KepBeolv(){
		
	}
	public static ArrayList<BufferedImage>  kepolv(File s) throws IOException, FileNotFoundException{
		ArrayList<BufferedImage> tar =convert(s);
		return tar;
		
		
	}

	public static ArrayList<BufferedImage> convert(File file){
		ArrayList<BufferedImage> tar = new ArrayList<BufferedImage>();
		try {
		    String[] imageatt = new String[]{
		            "imageLeftPosition",
		            "imageTopPosition",
		            "imageWidth",
		            "imageHeight"
		    };    

		    ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName("gif").next();
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

		            if(nodeItem.getNodeName().equals("ImageDescriptor")){
		                Map<String, Integer> imageAttr = new HashMap<String, Integer>();

		                for (int k = 0; k < imageatt.length; k++) {
		                    NamedNodeMap attr = nodeItem.getAttributes();
		                    Node attnode = attr.getNamedItem(imageatt[k]);
		                    imageAttr.put(imageatt[k], Integer.valueOf(attnode.getNodeValue()));
		                }
		                if(i==0){
		                    master = new BufferedImage(imageAttr.get("imageWidth"), imageAttr.get("imageHeight"), BufferedImage.TYPE_INT_ARGB);
		                }
		                master.getGraphics().drawImage(image, imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"), null);
		            }
		        }
		       ImageIO.write(master, "GIF", new File( i + ".gif")); 
		       tar.add(ImageIO.read(new File(i + ".gif")));
		       File a =new File(i + ".gif");
		       a.delete();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return tar;
	}
	
	
	
}
