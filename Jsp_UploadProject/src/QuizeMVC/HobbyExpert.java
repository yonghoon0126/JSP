package QuizeMVC;

import java.util.ArrayList;
import java.util.List;

public class HobbyExpert {

	public List<String> getBrands(String hobby) {
		
		List<String> brands = new ArrayList<String>();
		
		// String a = "";
		if(hobby.equals("m")) {
			brands.add("설악산");
			brands.add("북한산");
			// a = "설악산, 북한산";
			
		}else if(hobby.equals("t")) {
			brands.add("남해안");
			brands.add("동해안");
			// a = "남해안, 동해안";
			
		}else if(hobby.equals("s")) {
			brands.add("수영장");
			// a = "수영장";
			
		}else if(hobby.equals("r")) {
			brands.add("마라톤");
			brands.add("100미터 달리기");
			// a = "마라톤, 100미터 달리기";
		}
		// return a;
		return brands;
	}

}
