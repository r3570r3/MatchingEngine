package engine.backend.rules;

public class InterestesRule  implements RuleInterface {

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
