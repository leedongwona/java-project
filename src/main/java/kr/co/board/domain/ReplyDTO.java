package kr.co.board.domain;

import java.io.Serializable;
import java.util.Objects;

public class ReplyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rno;
	private int bno;
	private String id;
	private String pw;
	private String reply;
	private String regdate;
	private String updatedate;

	public ReplyDTO() {
	}

	public ReplyDTO(int rno, int bno, String id, String pw, String reply, String regdate, String updatedate) {
		this.rno = rno;
		this.bno = bno;
		this.id = id;
		this.pw = pw;
		this.reply = reply;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
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
		return Objects.hash(bno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyDTO other = (ReplyDTO) obj;
		return bno == other.bno;
	}

	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", bno=" + bno + ", id=" + id + "]";
	}

}
