package QuizeMVC;

import java.util.ArrayList;
import java.util.List;

public class HobbyExpert {

	public List<String> getBrands(String hobby) {
		
		List<String> brands = new ArrayList<String>();
		
		// String a = "";
		if(hobby.equals("m")) {
			brands.add("���ǻ�");
			brands.add("���ѻ�");
			// a = "���ǻ�, ���ѻ�";
			
		}else if(hobby.equals("t")) {
			brands.add("���ؾ�");
			brands.add("���ؾ�");
			// a = "���ؾ�, ���ؾ�";
			
		}else if(hobby.equals("s")) {
			brands.add("������");
			// a = "������";
			
		}else if(hobby.equals("r")) {
			brands.add("������");
			brands.add("100���� �޸���");
			// a = "������, 100���� �޸���";
		}
		// return a;
		return brands;
	}

}
