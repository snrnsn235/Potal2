package co.micol.potal.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> memberSelectList(); //전체리스트 조회
	MemberVO memberSelect(MemberVO vo); //한명의 저보를 조회 또는 로그인 체크도 수행
	int memberInsert(MemberVO vo); 
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	boolean memberIdCheck(String id); //아이디 중복체크를 위해
}
