package principal;

public class ConvertidorDeString {
	public static String formatearStirngDouble(String precio) {
		if(precio.indexOf(",") >= 0) {
			String[] split = precio.split(",");
			String res = split[0] + "." + split[1];
			return res;
		} else {
			return precio;
		}
	}
}
