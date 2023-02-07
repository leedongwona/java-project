package kr.co.member.domain;

import java.io.Serializable;
import java.util.Objects;

public class MemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String birth;
	private int age;
	private String address;
	private String pw;
	private String email;
	private String grade;

	public MemberDTO() {

	}

	public MemberDTO(String id, String name, String birth, int age, String address, String pw, String email,
			String grade) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.age = age;
		this.address = address;
		this.pw = pw;
		this.email = email;
		this.grade = grade;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getpw() {
		return pw;
	}

	public void setpw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", name=" + name + ", grade=" + grade + "]";
	}

}
