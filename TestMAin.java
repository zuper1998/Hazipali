
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TestMAin {

	public static void main(String[] args) {

		ArrayList<BufferedImage> tar = null;

	/*	try {
	//		tar = KepBeolv.kepolv("/home/andris/eclipse-workspace/Hazipali/src/tenor.gif");
		} catch (IOException e) {

			e.printStackTrace();

		}
		for (int i=0;i<tar.size()-1;i++){
			System.out.println(tar.get(i).equals(tar.get(i+1)));
		}*/
		
		
		Graph g = new Graph(null);
		try {
			g.ui_main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("FIle not found");
		}

	}

}