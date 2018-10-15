import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Transformator {

	public Transformator() {
		// TODO Auto-generated constructor stub
	}

	static int karakter_szam = 765 / 4;

	private static Rgbcont[][] alakito(BufferedImage img) {
		Rgbcont[][] alak = new Rgbcont[img.getWidth()][img.getHeight()];

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int rgb = img.getRGB(x, y);
				alak[x][y] = new Rgbcont((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
			}

		}

		return alak;
	}

	private static char characterizator(Rgbcont[][] alak, int szel, int mag, int kezd_s, int kezd_m) {
		int loc_val = 0;
		for (int i = 0; i < szel; i++) {

			for (int k = 0; k < mag; k++) {
				loc_val += alak[k + kezd_m][i + kezd_s].get_val();

			}
		}
		loc_val = loc_val / (szel * mag);
		if (loc_val < karakter_szam) {
			return 'a';
		} else if (loc_val < karakter_szam * 2) {
			return '0';
		} else if (loc_val < karakter_szam * 3) {
			return '/';
		} else {
			return '*';
		}
	}

	public static ArrayList<ArrayList<ArrayList<Character>>> converter(ArrayList<BufferedImage> kep_tar) {

		ArrayList<ArrayList<ArrayList<Character>>> char_tar = new ArrayList<ArrayList<ArrayList<Character>>>();

		for (int i = 0; i < kep_tar.size(); i++) {
			char_tar.add(sub_converter(kep_tar.get(i)));
		}

		return char_tar;

	}

	public static ArrayList<ArrayList<Character>> sub_converter(BufferedImage img) {
		int szel = 2;
		int mag = 3;
		int dady_counter = 0;
		Rgbcont[][] alak = Transformator.alakito(img);
		ArrayList<ArrayList<Character>> the_father = new ArrayList<ArrayList<Character>>();
		for (int i = 0; i < img.getWidth() / szel; i = i + szel) {
			the_father.add(new ArrayList<Character>());

			for (int k = 0; k < img.getHeight() / mag; k = k + mag) {
				the_father.get(dady_counter).add(characterizator(alak, szel, mag, i, k));

			}
			dady_counter++;
		}

		return the_father;

	}

}
