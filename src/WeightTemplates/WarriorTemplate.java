package WeightTemplates;

import java.util.ArrayList;

public class WarriorTemplate extends WeightTemplate{

	@Override
	public ArrayList<Double> getAggressiveTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(45.0);
		template.add(45.0);
		template.add(10.0);
		template.add(20.0);
		template.add(10.0);
		template.add(10.0);
		template.add(60.0);
		return template;
	}

	@Override
	public ArrayList<Double> getDefensiveTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(15.0);
		template.add(15.0);
		template.add(70.0);
		template.add(35.0);
		template.add(35.0);
		template.add(20.0);
		template.add(10.0);
		return template;
	}

	@Override
	public ArrayList<Double> getCCTemplate() {
		ArrayList<Double> template = new ArrayList<Double>();
		template.add(25.0);
		template.add(25.0);
		template.add(50.0);
		template.add(45.0);
		template.add(45.0);
		template.add(5.0);
		template.add(5.0);
		return template;
	}

}
