package kr.co.qna.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class QnaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int bno;
	private String title;
	private String content;
	private String pw;
	private String id;
	private int readcnt;
	private String regdate;
	private String updatedate;
	private List<String> uploadedFileList;

	public QnaDTO() {
	}

	public QnaDTO(String id, String pw, String title, String content) {
		this.title = title;
		this.content = content;
		this.pw = pw;
		this.id = id;
	}

	public QnaDTO(int bno, String title, String content, String pw, String id, int readcnt, String regdate,
			String updatedate) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.pw = pw;
		this.id = id;
		this.readcnt = readcnt;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}

	public QnaDTO(String id, Integer bno, String pw, String title, String content) {
		this.id = id;
		this.bno = bno;
		this.pw = pw;
		this.title = title;
		this.content = content;
	}

	public List<String> getUploadedFileList() {
		return uploadedFileList;
	}

	public void setUploadedFileList(List<String> uploadedFileList) {
		this.uploadedFileList = uploadedFileList;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
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
		QnaDTO other = (QnaDTO) obj;
		return bno == other.bno;
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", readcnt=" + readcnt + "]";
	}

}
