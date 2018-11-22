package hf_Test;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Ez egy container osztály ami a kód olvashatóságát kivánt növelni mivel
 * senki nem szereti a ArrayList<ArrayList<ArrayList<Character>>> tipusú
 * fügvényeket
 * 
 * @author Mihály András
 */
public class Monster_container implements Serializable { // this is so none has
															// to read something
															// like a 3
															// dimensional
															// arraylist, if you
															// dont think the
															// name is apealin
															// imagine this in a
															// fucntion header
															// as input and
															// outpput 2

	

	private static final long serialVersionUID = -4659517989340682710L;
	/**
	 * 
	 */
	ArrayList<ArrayList<ArrayList<Character>>> treeray = null;

	/**
	 * egyszerű konstruktor, kreáll egy 3d arraylistet
	 */
	public Monster_container() {
		treeray = new ArrayList<ArrayList<ArrayList<Character>>>();
	}

	/**
	 * 
	 * @param i
	 *            a kért 2d arraylist indexe
	 * @return 2d arraylist az i helyen
	 */

	public ArrayList<ArrayList<Character>> get(int i) {
		return treeray.get(i);
	}

	/**
	 * 
	 * @param i
	 *            a kért arraylist 2d arraylistjének indexe
	 * @param k
	 *            a kért arraylist indexe
	 * @return A kért arraylist a i, k helyen
	 */

	public ArrayList<Character> get(int i, int k) {
		return treeray.get(i).get(k);
	}

	/**
	 * 
	 * @param i
	 *            a kért karakter arraylistjének, 2d arraylistjének indexe
	 * @param k
	 *            a kért karakter arraylistjének indexe
	 * @param z
	 *            a kért karakter indexe
	 * @return    a kért karakter
	 */

	public char get(int i, int k, int z) {
		return treeray.get(i).get(k).get(z);
	}

	/**
	 * add fügvény
	 * @param a a hozzáadni kivánt 2d arraylist
	 */
	public void add(ArrayList<ArrayList<Character>> a) {
		treeray.add(a);
	}

	/**
	 * size fügvény
	 * @return a fő array mérete
	 */
	
	public int size() {
		return treeray.size();
	}

}
