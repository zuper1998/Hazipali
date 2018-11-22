
package hf_Test;
/**
 * az rgb adatokat tárolja, ez is mint a {@link Monster_container} azért készült hogy a kód olvashatóbb legyen
 * 
 *  @author Mihály András
 */


public class Rgbcont {
	
	
	
 int red;
 int green;
 int blue;
 
 	/**
 	 * 
 	 * @param r  a vörös szint állitja be
 	 * @param g  a zöld szint állitja be
 	 * @param b  a kék szint állitja be
 	 */
 	
 	public Rgbcont(int r,int g, int b){
 		red = r;
 		green = g;
 		blue = b;
 	}
 	
 	
 	/**
 	 * összeadja az értékeket hogy azt a {@link Transformator} fel tudja dolgozni könyedén
 	 * @return  a szín értékek összege
 	 */
 	public int get_val(){
 		return red+green+blue;
 	}
}
