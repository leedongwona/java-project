package kr.co.admin.domain;

import java.io.Serializable;
import java.util.Objects;

public class DMemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String deletedate;
	
	public DMemberDTO() {
		
	}

	public DMemberDTO(String id, String name, String deletedate) {
		this.id = id;
		this.name = name;
		this.deletedate = deletedate;
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

	public String getDeletedate() {
		return deletedate;
	}

	public void setDeletedate(String deletedate) {
		this.deletedate = deletedate;
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
		DMemberDTO other = (DMemberDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DMemberDTO [id=" + id + ", name=" + name + ", deletedate=" + deletedate + "]";
	}
	
}
