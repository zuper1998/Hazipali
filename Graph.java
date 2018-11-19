import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Graph extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2718930649317233168L;
	ArrayList<ArrayList<ArrayList<Character>>> cont = null;

	public Graph() {

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("Courier", Font.BOLD, 4);
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

	public void ui_main() throws FileNotFoundException, IOException {
		JPanel jpan = new JPanel();
		add(jpan);
		// save and load button
		JButton save = new JButton("Save");
		jpan.add(save);
		JButton load = new JButton("Load");
		jpan.add(load);
		// txt button
		JButton jb_text = new JButton("Choose File");
		jpan.add(jb_text);
		// Inf text field
		JTextArea jta_inf = new JTextArea();
		jpan.add(jta_inf);
		// button
		JButton jb_start = new JButton("Start");
		jpan.add(jb_start);

		// file chooser
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JIF Images", "gif");
		chooser.setFileFilter(filter);
		// inline action listeners
		jb_start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == null) {
					jta_inf.setText("Select a File you cunt");
				}
				try {
					graph();
				} catch (InterruptedException e1) {
					e1.printStackTrace();

				}
			}
		});
		jb_text.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = chooser.showOpenDialog(jpan);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					jta_inf.setText("Loading...");

					try {

						cont = Transformator.converter(KepBeolv.kepolv(chooser.getSelectedFile()));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				jta_inf.setText("Finished owo");
			}
		});
		save.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FileOutputStream f;
				try {
					f = new FileOutputStream("gsave.gif");
					ObjectOutputStream out = new ObjectOutputStream(f);
					out.writeObject(cont);
					out.close();
					f.close();
					jta_inf.setText("It has been saved as gsave.gif");
				} catch (IOException e1) {
					jta_inf.setText("nothing to save");

				}
			}
		});
		load.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FileInputStream f;
				try {
					int returnVal = chooser.showOpenDialog(jpan);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						f = new FileInputStream(chooser.getSelectedFile());
						ObjectInputStream in = new ObjectInputStream(f);
						cont = (ArrayList<ArrayList<ArrayList<Character>>>) in.readObject();
						in.close();
						f.close();

						jta_inf.setText("It has been loaded");
					}
				} catch (IOException | ClassNotFoundException e1) {
					jta_inf.setText("Error while loading");

				}
			}
		});

	}

	public void graph() throws InterruptedException {

		JPanel jp_anim = new JPanel();
		add(jp_anim);
		String text = "";
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		jp_anim.add(txt);

		txt.setFont(new Font("Courier", Font.BOLD, 4));
		ArrayList<String> jta_cont = new ArrayList<String>();

		for (int j = 0; j < cont.size(); j++) {
			text = "";
			for (int i = 0; i < cont.get(j).size(); i++) {

				text += convertSA(cont.get(j).get(i)) + "\n";

			}
			jta_cont.add((text));

		}

		Thread t_w = new Thread(new Text_writer(txt, jta_cont));
		t_w.start();

	}
}
