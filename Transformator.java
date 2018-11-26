package hf_Test;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Ez az osztály végzi el a BufferedImage átalakitását Character tömbé
 * 
 * 
 * @author Mihály András
 */
public class Transformator {
	
	
	
	
	
	static int karakter_szam = 765 / 5; // ez alapján bontja fel a képet karakterekre

	
	
	/**
	 * A képből kiolvassa annak szinkódját majd egy 2d Rgbcont-ba rakja
	 * @param img Kapott kép
	 * @return alak egy 2d a szinkódok tárolására specializált Rgbcont
	 */
	 static Rgbcont[][] alakito(BufferedImage img) {
		Rgbcont[][] alak = new Rgbcont[img.getWidth()][img.getHeight()];

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int rgb = img.getRGB(x, y);
				alak[x][y] = new Rgbcont((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
			}

		}

		return alak;
	}

	 /**
	  * a kapott szinkódtömb egy darabját egy karakterre redukálja
	  * @param alak kapott 2d szinkód tároló
	  * @param szel mekkora szélességi határig kell beolvasni a szinkódokat
	  * @param mag  mekkora magassági határig kell beolvasni a szinkódokat
	  * @param kezd_s  kezdő szélességi koordináta
	  * @param kezd_m  kezdő magassági koodináta
	  * @return
	  */
	 
	 static char characterizator(Rgbcont[][] alak, int szel, int mag, int kezd_s, int kezd_m) {
		int loc_val = 0;
		if ((kezd_m >= alak.length) || (kezd_s >= alak[kezd_m].length)) {
			return '-';
		}

		for (int i = 0; i < szel; i++) {

			for (int k = 0; k < mag; k++) {
				loc_val += alak[kezd_m + k][kezd_s + i].get_val();

			}
		}
		loc_val = loc_val / (szel * mag + 1);

		if (loc_val < karakter_szam * 1.3) {
			return '@';
		} else if (loc_val < karakter_szam * 2) {
			return '#';
		} else if (loc_val < karakter_szam * 3) {
			return '+';
		} else if (loc_val < karakter_szam * 4) {
			return ',';
		} else {
			return '.';
		}
	}

	 
	 
	 
	 /**
	  * a fő fügvény melyenen keresztül folyik a teljes átalakítás
	  * @param kep_tar gif-ből kiolvasott képek
	  * @return char_tar tartalmazza az átalakított karakter tömböket
	  */
	public static Monster_container converter(ArrayList<BufferedImage> kep_tar) {

		Monster_container char_tar = new Monster_container();

		for (int i = 0; i < kep_tar.size(); i++) {
			
			char_tar.add(sub_converter(kep_tar.get(i)));
		}

		return char_tar;

	}

	
	/**
	 * a képet alakitja ASCII képpé, ahol a szélesség az eredet / szél és a magasság az eredeti / mag
	 * @param img egy átalakitandó kép
	 * @return the_father a képt karakterekre átalakítva 
	 */
	static ArrayList<ArrayList<Character>> sub_converter(BufferedImage img) {
		int szel = 1; 
		int mag = 1;
		int main_arr_counter = 0;
		Rgbcont[][] alak = Transformator.alakito(img);
System.out.println(img.getWidth()+" "+img.getHeight());
		ArrayList<ArrayList<Character>> the_father = new ArrayList<ArrayList<Character>>();
		for (int k = 0; k < img.getHeight(); k = k + szel) {
			the_father.add(new ArrayList<Character>());

			for (int i = 0; i < img.getWidth(); i = i + mag) {
					if ((i + szel >= img.getWidth()) && (k + mag >= img.getHeight())) {
					the_father.get(main_arr_counter).add(characterizator(alak, 0, 0, k, i));

				} else if (k + mag >= img.getHeight()) {
					the_father.get(main_arr_counter).add(characterizator(alak, szel, 0, k, i));

				} else if (i + szel >= img.getWidth()) {
					the_father.get(main_arr_counter).add(characterizator(alak, 0, mag, k, i));

				} else {
					the_father.get(main_arr_counter).add(characterizator(alak, szel, mag, k, i));
				}
			}
			main_arr_counter++;
		}

		return the_father;

	}

}
