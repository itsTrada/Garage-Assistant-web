package model;

public class Member {
    private String id;
    private String name;
    private String mobile;
    private String email;
    private String password;
    
	public Member(String id, String name, String mobile, String email,
			String password) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
	}
    
	public Member() {
	}

	//log in purpose
	public Member(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
