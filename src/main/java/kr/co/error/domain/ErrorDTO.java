package kr.co.error.domain;

import java.io.Serializable;
import java.util.Objects;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int eno;
	private String msg;
	private String staff;
	private String status;
	private String errordate;
	private String updatedate;
	
	
	public ErrorDTO() {
		
	}

	public ErrorDTO(int eno, String msg, String staff, String status, String errordate, String updatedate) {
		super();
		this.eno = eno;
		this.msg = msg;
		this.staff = staff;
		this.status = status;
		this.errordate = errordate;
		this.updatedate = updatedate;
	}


	public int getEno() {
		return eno;
	}


	public void setEno(int eno) {
		this.eno = eno;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getStaff() {
		return staff;
	}


	public void setStaff(String staff) {
		this.staff = staff;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getErrordate() {
		return errordate;
	}


	public void setErrordate(String errordate) {
		this.errordate = errordate;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		return Objects.hash(eno);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorDTO other = (ErrorDTO) obj;
		return eno == other.eno;
	}


	@Override
	public String toString() {
		return "ErrorDTO [eno=" + eno + ", staff=" + staff + ", status=" + status + "]";
	}
	
	

}
