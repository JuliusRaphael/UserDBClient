public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String age;
	
	

	public User() {
		
	}
	
	public User(String firstName, String lastName, String age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	
	public User(String string, String string2, String string3, String string4) {
		this.id = string;
		this.firstName = string2;
		this.lastName = string3;
		this.age = string4;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String toString(){
		return "id = " + id + ", first name: " + firstName + ", last name:  " + lastName + ", age: " + age;
	}
	
	public Object[] toList(){
		Object[] ret = {id,firstName,lastName,age};
		return ret;
	}
}