package WeightTemplates;

import java.util.ArrayList;

public class PriestTemplate extends WeightTemplate{

	@Override
	public ArrayList<Double> getAggressiveTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(70.0);
		template.add(15.0);
		template.add(15.0);
		
		template.add(25.0);
		template.add(25.0);
		template.add(25.0);
		template.add(25.0);
		return template;
	}

	@Override
	public ArrayList<Double> getDefensiveTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(10.0);
		template.add(45.0);
		template.add(45.0);
		
		template.add(30.0);
		template.add(20.0);
		template.add(25.0);
		template.add(25.0);
		return template;
	}

	@Override
	public ArrayList<Double> getCCTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(10.0);
		template.add(20.0);
		template.add(70.0);
		
		template.add(25.0);
		template.add(25.0);
		template.add(35.0);
		template.add(15.0);
		return template;
	}

}
