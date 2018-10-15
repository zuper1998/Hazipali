import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph {
	ArrayList<ArrayList<ArrayList<Character>>> cont = null;

	public Graph() {

	}

	public Graph(ArrayList<ArrayList<ArrayList<Character>>> c) {
		cont = c;
	}

	public void graph() throws InterruptedException {
		JFrame frame = new JFrame("doot");
		JPanel panel = new JPanel();
		Font font = new Font("Consolas", Font.BOLD, 4);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for (int j = 0; j < cont.size(); j++) {
			
			for (int i = cont.size() - 1; i > 0; i--) {
				JLabel label = new JLabel(cont.get(j).get(i).toString());
				label.setFont(font);
				panel.add(label);
			}
			frame.add(panel);
			frame.setVisible(true);

			Thread.sleep(10);
			
		}
		frame.setState(Frame.NORMAL);
	}
}
