package guestbook;
import java.util.*;

// 방명록 테이블 구조와 동일한 데이터 처리 클래
public class GuestBook {
	//멤버변수
	private int    gb_id;
	private String gb_name;
	private String gb_email;
	private String gb_tel;
	private Date   gb_date;
	private String gb_passwd;
	private String gb_contents;
	
	public String getGb_contents() {
		return gb_contents;
	}

	public void setGb_contents(String gb_contents) {
		this.gb_contents = gb_contents;
	}

	public Date getGb_date() {
		return gb_date;
	}

	public void setGb_date(Date gb_date) {
		this.gb_date = gb_date;
	}

	public String getGb_email() {
		return gb_email;
	}

	public void setGb_email(String gb_email) {
		this.gb_email = gb_email;
	}

	public int getGb_id() {
		return gb_id;
	}

	public void setGb_id(int gb_id) {
		this.gb_id = gb_id;
	}

	public String getGb_name() {
		return gb_name;
	}

	public void setGb_name(String gb_name) {
		this.gb_name = gb_name;
	}

	public String getGb_tel() {
		return gb_tel;
	}

	public void setGb_tel(String gb_tel) {
		this.gb_tel = gb_tel;
	}

	public String getGb_passwd() {
		return gb_passwd;
	}

	public void setGb_passwd(String gb_passwd) {
		this.gb_passwd = gb_passwd;
	}
}
