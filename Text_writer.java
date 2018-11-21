package hf_Test;



import java.util.ArrayList;

import javax.swing.JTextArea;

public class Text_writer extends Thread {
	JTextArea jta_curr = null;
	ArrayList<String> str_ar = null;

	Text_writer(JTextArea a, ArrayList<String> b) {
		jta_curr = a;
		str_ar = b;
	}

	public void run() {
		while (true) {
			for (int i = 0; i < str_ar.size(); i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jta_curr.setText(str_ar.get(i));
			}
		}
	}

}
