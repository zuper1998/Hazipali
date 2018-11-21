package hf_Test;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster_container implements Serializable{  //this is so none has to read something like a 3 dimensional arraylist, if you dont think the name is apealin imagine this in a fucntion header as input and outpput 2

	/**
	 * 
	 */
	private static final long serialVersionUID = -4659517989340682710L;
	/**
	 * 
	 */
	ArrayList<ArrayList<ArrayList<Character>>> treeray=null;
	
	
	public Monster_container() {
		treeray = new ArrayList<ArrayList<ArrayList<Character>>>();
	}
	
	public ArrayList<ArrayList<Character>>  get(int i){
		return treeray.get(i);
	}
	
	public ArrayList<Character>  get(int i, int k){
		return treeray.get(i).get(k);
	}
	
	public char  get(int i, int k,int z){
		return treeray.get(i).get(k).get(z);
	}
	
	public void add(ArrayList<ArrayList<Character>> a){
		treeray.add(a);
	}
	
	
	
	public int size(){
		return treeray.size();
	}
	
	
}
