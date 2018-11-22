package hf_Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
/**
 * Ez a fő megjelenitő osztály, ezen keresztűl "látjuk" az animáéciót.
 *Tesztelés esetén a IS_THIS_A_TEST változót 0ra kell állitani.
 * 
 * 
 *@author Mihály András
 *
 *
 *
 */

public class Graph extends JFrame implements Serializable {

	static final int IS_THIS_A_TEST = 0; // if testing set it to 1
	public String test_file_loc = "";
	private static final long serialVersionUID = -2718930649317233168L;
	Monster_container cont = null;

	/**
	A konstruktor, a kovetkező dolgokat állitja be: Lazout, Font, size, Resizable, Visible
	@see JFrame
	
	
	*/
	public Graph() {

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("Courier", Font.BOLD, 4);
		setFont(font);
		setSize(1600, 1000);
		setResizable(true);
		setVisible(true);
	}

	/**
	 A kapott karakter ArrayListet egy string-é alakítja
	 @param a egy charactereket tároló arraylist
	 @return b a kapott arraylist stringként
	 
	  
	  
	  
	*/
	static String convertSA(ArrayList<Character> a) {
		String b = "";
		for (int i = 0; i < a.size(); i++) {
			b += a.get(i);
		}

		return b;
	}

	/**
	Fő ui (mint a neve is mondja), 4 gomb segitsegevel vegezzuk el átalakítást:
	 -save elmenti .yeet fájlba a cont objektumot
	 -load betölti a cont objektumot
	 -choose egy gifet betölt és átlakít karakter sorozattá majd a cont objektumba rakja
	 -start megkezdi az animációt
	
	
	
	
	
	*/
	public void ui_main() {
		JPanel jpan = new JPanel();
		add(jpan);
		// save and load button
		JButton save = new JButton("Save");
		jpan.add(save);
		JButton load = new JButton("Load");
		jpan.add(load);
		// txt button
		JButton jb_choose = new JButton("Choose File");
		jpan.add(jb_choose);
		// Inf text field
		JTextArea jta_inf = new JTextArea();
		jpan.add(jta_inf);
		// button
		JButton jb_start = new JButton("Start");
		jpan.add(jb_start);

		// file chooser
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JIF Images", "gif");
		FileNameExtensionFilter Lfilt = new FileNameExtensionFilter("Yote", "yeet");

		// inline action listeners
		jb_start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == null) {
					jta_inf.setText("Select a File you cunt");
				}
				try {
					graph();
				} catch (InterruptedException | NullAnimExepton e1) {
					e1.printStackTrace();

				}
			}
		});
		jb_choose.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setFileFilter(filter);

				try {
					if (IS_THIS_A_TEST == 1) {
						ArrayList<BufferedImage> t = new ArrayList<BufferedImage>();

						BufferedImage test = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
						Graphics2D t_g = test.createGraphics();
						t_g.setPaint(new Color(0, 0, 0));
						t_g.fillRect(20, 20, test.getWidth(), test.getHeight());
						t.add((test));

						cont = Transformator.converter(t);

					} else {
						int returnVal = chooser.showOpenDialog(jpan);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							jta_inf.setText("Error wrong file type");
							cont = Transformator.converter(KepBeolv.kepolv(chooser.getSelectedFile()));
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				jta_inf.setText("Finished owo");
			}
		});
		save.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override

			public void actionPerformed(ActionEvent e) {
				FileOutputStream f;

				try {
					if (IS_THIS_A_TEST == 1) {
						cont = new Monster_container();
						ArrayList<ArrayList<Character>> cont_t = new ArrayList<ArrayList<Character>>();
						ArrayList<Character> cont_t_t = new ArrayList<Character>();
						
						cont.add(cont_t);cont.get(0).add(cont_t_t);cont.get(0, 0).add('l');


						File t_f = File.createTempFile("temp", "yeet");
						f = new FileOutputStream(t_f);
						ObjectOutputStream out = new ObjectOutputStream(f);
						test_file_loc = t_f.getAbsolutePath();
						out.writeObject(cont);
						out.close();
						f.close();
						t_f.deleteOnExit();
					} else {

						f = new FileOutputStream("gsave.yeet");
						ObjectOutputStream out = new ObjectOutputStream(f);
						out.writeObject(cont);
						jta_inf.setText("It has been saved as gsave.yeet in " + f.getFD());

						out.close();
						f.close();
					}
				} catch (IOException e1) {
					jta_inf.setText("nothing to save");

				}

			}
		});
		load.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override

			public void actionPerformed(ActionEvent e) {
				FileInputStream f;

				try {

					if (IS_THIS_A_TEST == 1) {
						f = new FileInputStream(new File(test_file_loc));
						ObjectInputStream in = new ObjectInputStream(f);
						cont = (Monster_container) in.readObject();
						in.close();
						f.close();
					} else {

						chooser.setFileFilter(Lfilt);

						int returnVal = chooser.showOpenDialog(jpan);
						if (returnVal == JFileChooser.APPROVE_OPTION) {

							f = new FileInputStream(chooser.getSelectedFile());

							ObjectInputStream in = new ObjectInputStream(f);
							cont = (Monster_container) in.readObject();
							in.close();
							f.close();

							jta_inf.setText("It has been loaded");
						}
					}
				} catch (IOException | ClassNotFoundException e1) {
					jta_inf.setText("Error while loading");

				}
			}
		});

	}

	/**
	az animációt végrahajtó fügvény, egy JTextArea objektumot helyez el a felületen, melyre meghivja az azt változtató Text_writer threadet
	
	@throws InterruptedException   a thread miatt 
	@throws NullAnimExepton      ha üres a cont ezt dobja
	
	
	
	
	
	*/
	public void graph() throws InterruptedException, NullAnimExepton {

		JPanel jp_anim = new JPanel();
		add(jp_anim);
		String text = "";
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		jp_anim.add(txt);

		txt.setFont(new Font("Courier", Font.BOLD, 4));
		ArrayList<String> jta_cont = new ArrayList<String>();
		if (cont == null) {
			throw new NullAnimExepton();
		} else {
			for (int j = 0; j < cont.size(); j++) {
				text = "";
				for (int i = 0; i < cont.get(j).size(); i++) {

					text += convertSA(cont.get(j, i)) + "\n";

				}
				jta_cont.add((text));

			}

			Thread t_w = new Thread(new Text_writer(txt, jta_cont));
			t_w.start();
		}
	}

}
