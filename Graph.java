import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Graph extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2718930649317233168L;
	ArrayList<ArrayList<ArrayList<Character>>> cont = null;

	public Graph() {

	}

	public Graph(ArrayList<ArrayList<ArrayList<Character>>> c) {
		cont = c;

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("Consolas", Font.BOLD, 4);
		setFont(font);
		setSize(1600, 1000);
		setResizable(true);
		setVisible(true);
	}

	private String convertSA(ArrayList<Character> a) {
		String b = "";
		for (int i = 0; i < a.size(); i++) {
			b += a.get(i);
		}

		return b;
	}

	public void graph() throws InterruptedException {
		String text = "";
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		add(txt);

		txt.setLocation(500, 0);
		txt.setFont(new Font("Consolas", Font.BOLD, 4));

		System.out.println(cont.size());
		for (int j = 0; j < cont.size(); j++) {
			text = "";
			for (int i = 0; i < cont.get(j).size(); i++) { // int i =
															// cont.get(j).size()
															// - 1; i > 0; i--

				text += convertSA(cont.get(j).get(i)) + "\n";
				
			}

			TimeUnit.MILLISECONDS.sleep(500);
			// TimeUnit.MILLISECONDS.sleep(5000);

			txt.setVisible(false);
			txt.setText(text);
			txt.setVisible(true);

			/*
			 * SwingUtilities.invokeLater(new Runnable() { public void run() {
			 * txt.append(text1); } }); TimeUnit.MILLISECONDS.sleep(70);
			 * txt.setText(""); add(txt);
			 */

		}
	}
}
