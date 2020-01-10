package enums;

import java.util.ArrayList;
import java.util.List;

public enum Rules {
 
	FOREST("forest", new int[] {0,1,2,3}),
	FIRE("fire", new int[] {0,1,2,3,4,5}),
	INSECT("insect", new int[] {0,1,2,3,4});


	private String label;
	private int[] state;
	
	

	 Rules(String label, int[] state) {
	        this.label = label;
	        this.state = state;
	        
	    }
	 public String getLabel() {
	        return label;
	    }

	    public static List<String> getRuleSetLabels() {
	        List<String> allLabels = new ArrayList<>();
	        for (Rules ruleSet : Rules.values()) {
	            allLabels.add(ruleSet.getLabel());
	        }
	        return allLabels;
	    }

	    public int[]getstate() {
	        return state ;
	    }
	    public void setState(int[] state) {
	    	this.state = state;
	    }
}
