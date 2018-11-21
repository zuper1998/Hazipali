package hf_Test;


import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Transformator {

	public Transformator() {
		// TODO Auto-generated constructor stub
	}

	static int karakter_szam = 765 / 5;

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

	public static Monster_container converter(ArrayList<BufferedImage> kep_tar) {

		Monster_container char_tar = new Monster_container();

		for (int i = 0; i < kep_tar.size(); i++) {
			
			char_tar.add(sub_converter(kep_tar.get(i)));
		}

		return char_tar;

	}

	static ArrayList<ArrayList<Character>> sub_converter(BufferedImage img) {
		int szel = 3; 
		int mag = 2;
		int main_arr_counter = 0;
		Rgbcont[][] alak = Transformator.alakito(img);

		ArrayList<ArrayList<Character>> the_father = new ArrayList<ArrayList<Character>>();
		for (int i = 0; i < img.getWidth(); i = i + szel) {
			the_father.add(new ArrayList<Character>());

			for (int k = 0; k < img.getHeight(); k = k + mag) {
				if ((i + szel >= img.getWidth()) && (k + mag >= img.getHeight())) {
					the_father.get(main_arr_counter).add(characterizator(alak, 0, 0, i, k));

				} else if (k + mag >= img.getHeight()) {
					the_father.get(main_arr_counter).add(characterizator(alak, szel, 0, i, k));

				} else if (i + szel >= img.getWidth()) {
					the_father.get(main_arr_counter).add(characterizator(alak, 0, mag, i, k));

				} else {
					the_father.get(main_arr_counter).add(characterizator(alak, szel, mag, i, k));
				}
			}
			main_arr_counter++;
		}

		return the_father;

	}

}
