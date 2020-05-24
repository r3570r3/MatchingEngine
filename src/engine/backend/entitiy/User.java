package engine.backend.entitiy;

public class User implements SystemEntity {
	
	private String name;
	private String gender;
	private String age;
	private String[] interests;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String[] getInterests() {
		return interests;
	}
	public void setInterests(String[] interests) {
		this.interests = interests;
	}
	public User(String name, String gender, String age, String[] interests) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.interests = interests;
	}
	
	public User(String name, String gender, int age, String[] interests) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = String.valueOf(age);
		this.interests = interests;
	}
	
	public String toString() {
		return name + " # " + gender + " # " + age + " # " + interests;
	}
}

//class InterestComparator implements Comparator<SystemEntity>	{
//	public int compare(SystemEntity u1, SystemEntity u2) {
//		
//	}
//}

