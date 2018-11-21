package principal;

public class ConvertidorDeString {
	public static String formatearStirngDouble(String precio) {
		String[] split = precio.split(",");
		String res = split[0] + "." + split[1];
		return res;
	}
}
