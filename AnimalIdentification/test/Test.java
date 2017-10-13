
import java.util.ArrayList;

import com.ai.controller.Inference;
import com.ai.dao.FeatureDAO;
import com.ai.dao.RulesDAO;

public class Test {
	@org.junit.Test
	public void test() {
		String aString = "有黑发";
		String bString = "反刍";
		String cString = "颜色是白色";
		String dString = "有黑色条纹";
		
		ArrayList<String> inputlist = new ArrayList<String>();
		inputlist.add(aString);
		inputlist.add(bString);
		inputlist.add(cString);
		inputlist.add(dString);
//		
//		Inference inference = new Inference(inputlist);
//		String result = inference.MatchAndFindResult();
//		
//		System.out.println("++"+result);
		
		//new RulesDAO().addRule(inputlist, "外星人","1");
		
		System.out.println(new FeatureDAO().getFeatureList().size());
	}
	
	

}
