package enums;

import java.util.ArrayList;
import java.util.List;


public enum StateRules {
 
	FOREST("Forest", new int[] {0,1,2,3}),
	FIRE("Fire", new int[] {0,1,2,3,4,5}),
	INSECT("Insect", new int[] {0,1,2,3,4});


	private String label;
	private int[] state;
	
	

	 StateRules(String label, int[] state) {
	        this.label = label;
	        this.state = state;
	        
	    }
	 public String getLabel() {
	        return label;
	    }

	    public static List<String> getRuleSetLabels() {
	        List<String> allLabels = new ArrayList<>();
	        for (StateRules ruleSet : StateRules.values()) {
	            allLabels.add(ruleSet.getLabel());
	        }
	        return allLabels;
	    }
	    public static StateRules fromString(String text) {
	        for (StateRules ruleSet : StateRules.values()) {
	            if (ruleSet.label.equalsIgnoreCase(text)) {
	                return ruleSet;
	            }
	        }
	        return null;
	    }

	    public int[]getstate() {
	        return state ;
	    }
	    public void setState(int[] state) {
	    	this.state = state;
	    }
}
