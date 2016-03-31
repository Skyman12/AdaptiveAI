package WeightTemplates;

import java.util.ArrayList;

public class PriestTemplate extends WeightTemplate{

	@Override
	public ArrayList<Integer> getAggressiveTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(70);
		template.add(15);
		template.add(15);
		
		template.add(25);
		template.add(25);
		template.add(25);
		template.add(25);
		return template;
	}

	@Override
	public ArrayList<Integer> getDefensiveTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(10);
		template.add(45);
		template.add(45);
		
		template.add(30);
		template.add(20);
		template.add(25);
		template.add(25);
		return template;
	}

	@Override
	public ArrayList<Integer> getCCTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(10);
		template.add(20);
		template.add(70);
		
		template.add(25);
		template.add(25);
		template.add(35);
		template.add(15);
		return template;
	}

}
