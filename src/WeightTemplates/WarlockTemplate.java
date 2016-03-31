package WeightTemplates;

import java.util.ArrayList;

public class WarlockTemplate extends WeightTemplate{

	@Override
	public ArrayList<Double> getAggressiveTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(40.0);
		template.add(40.0);
		template.add(20.0);
		
		template.add(30.0);
		template.add(30.0);
		template.add(10.0);
		template.add(30.0);
		return template;
	}

	@Override
	public ArrayList<Double> getDefensiveTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(30.0);
		template.add(30.0);
		template.add(40.0);
		
		template.add(40.0);
		template.add(5.0);
		template.add(35.0);
		template.add(20.0);
		return template;
	}

	@Override
	public ArrayList<Double> getCCTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(10.0);
		template.add(10.0);
		template.add(80.0);
		
		template.add(5.0);
		template.add(5.0);
		template.add(60.0);
		template.add(30.0);
		return template;
	}

}
