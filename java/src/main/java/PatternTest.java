import java.util.regex.Pattern;


public class PatternTest {
	public static void main(String[] args){
		String str ="221";
		String rex = "^\\d+$";
	
		System.out.println(Pattern.matches(rex, str));
		str ="an";
		rex = "^[a-zA-Z]$";
		System.out.println(Pattern.matches(rex, str));
	}
}
