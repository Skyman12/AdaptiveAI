package WeightTemplates;

import java.util.ArrayList;

public abstract class WeightTemplate {
	
	public abstract ArrayList<Double> getAggressiveTemplate();
	
	public abstract ArrayList<Double> getDefensiveTemplate();
	
	public ArrayList<Double> getBalancedTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(33.0);
		template.add(33.0);
		template.add(34.0);
		template.add(25.0);
		template.add(25.0);
		template.add(25.0);
		template.add(25.0);
		return template;
	}
	
	public abstract ArrayList<Double> getCCTemplate();
	
}
