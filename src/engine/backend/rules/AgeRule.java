package engine.backend.rules;

public class AgeRule  implements RuleInterface {
	
	private int rulePriority;
	
	@Override
	public void setRulePriority(int p) {
		this.rulePriority = p;
		
	}

	@Override
	public int getRulePriority() {
		return this.rulePriority;
		
	}

}
