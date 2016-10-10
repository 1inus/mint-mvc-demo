package demo;

public enum Gender {
	MALE(1, "男"), FEMALE(0, "女");
	
	public final Integer key;
	public final String value;
	
	private Gender(Integer key, String value){
		this.key = key;
		this.value = value;
	}
}
