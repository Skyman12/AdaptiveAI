package WeightTemplates;

import java.util.ArrayList;

public abstract class WeightTemplate {
	
	public abstract ArrayList<Integer> getAggressiveTemplate();
	
	public abstract ArrayList<Integer> getDefensiveTemplate();
	
	public ArrayList<Integer> getBalancedTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(33);
		template.add(33);
		template.add(34);
		template.add(25);
		template.add(25);
		template.add(25);
		template.add(25);
		return template;
	}
	
	public abstract ArrayList<Integer> getCCTemplate();
	
}
