package WeightTemplates;

import java.util.ArrayList;

public class BardTemplate extends WeightTemplate{

	@Override
	public ArrayList<Integer> getAggressiveTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(70);
		template.add(15);
		template.add(15);
		
		template.add(30);
		template.add(15);
		template.add(15);
		template.add(40);
		return template;
	}

	@Override
	public ArrayList<Integer> getDefensiveTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(10);
		template.add(45);
		template.add(45);
		
		template.add(15);
		template.add(15);
		template.add(60);
		template.add(10);
		return template;
	}

	@Override
	public ArrayList<Integer> getCCTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(33);
		template.add(33);
		template.add(34);
		
		template.add(40);
		template.add(10);
		template.add(10);
		template.add(40);
		return template;
	}

}
