
public class Rgbcont {
 int red;
 int green;
 int blue;
 
 
 	public Rgbcont(){
 		
 	}
 	public Rgbcont(int r,int g, int b){
 		red = r;
 		green = g;
 		blue = b;
 	}
 	
 	
 	public void print(){
 		System.out.println(red + " "+green+" "+blue);
 	}
 	public int get_val(){
 		return red+green+blue;
 	}
}
