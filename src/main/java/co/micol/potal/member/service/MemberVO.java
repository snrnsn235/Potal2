package co.micol.potal.member.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String tel;
	private String address;
	private String author;
	private String imgFile;
	private String pimgFile;
	
	public MemberVO() {
	}
}
