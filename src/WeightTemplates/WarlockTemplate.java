package WeightTemplates;

import java.util.ArrayList;

public class WarlockTemplate extends WeightTemplate{

	@Override
	public ArrayList<Integer> getAggressiveTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(40);
		template.add(40);
		template.add(20);
		
		template.add(30);
		template.add(30);
		template.add(10);
		template.add(30);
		return template;
	}

	@Override
	public ArrayList<Integer> getDefensiveTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(30);
		template.add(30);
		template.add(40);
		
		template.add(40);
		template.add(5);
		template.add(35);
		template.add(20);
		return template;
	}

	@Override
	public ArrayList<Integer> getCCTemplate() {
		ArrayList<Integer> template = new ArrayList<Integer>();
		template.add(10);
		template.add(10);
		template.add(80);
		
		template.add(5);
		template.add(5);
		template.add(60);
		template.add(30);
		return template;
	}

}
