package hackthenorth.sobriety;

public class Contacts {
	private int id;
	private String name;
	private String phone;
	
	public Contacts(){}
	
	public Contacts(String name, String phone){
		super();
		this.name = name;
		this.phone = phone;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String toString() {
		return "Contacts [id=" + id + ", name=" + name + ", phone=" + phone+"]";
	}
	
}
