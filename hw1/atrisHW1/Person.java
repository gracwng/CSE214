import java.io.PrintStream;

public class Person {
	
	private int age;
	private double height;
	private double weight;
	private String name;
	private String gender;


	public Person(int age, double height, double weight, String name, String gender) {
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.name = name;
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

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
	
	public String toString()
	{
		 System.out.printf("| %04d | %04d | %04d | %-20s | %-10s | %n" + age + height + weight + name + gender);
		
	}
	
	
	
	

}
