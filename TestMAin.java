
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TestMAin {

	public static void main(String[] args) {

		ArrayList<BufferedImage> tar = null;

		try {
			tar = KepBeolv.kepolv("C://Users//Narcano//workspace//Hazipali//tenor1.gif");
		} catch (IOException e) {

			e.printStackTrace();

		}
		for (int i=0;i<tar.size()-1;i++){
			System.out.println(tar.get(i).equals(tar.get(i+1)));
		}
		
		
		Graph g = new Graph(Transformator.converter(tar));
		try {
			g.graph();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}