package co.micol.potal.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> memberSelectList(); //��ü����Ʈ ��ȸ
	MemberVO memberSelect(MemberVO vo); //�Ѹ��� ������ ��ȸ �Ǵ� �α��� üũ�� ����
	int memberInsert(MemberVO vo); 
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	boolean memberIdCheck(String id); //���̵� �ߺ�üũ�� ����
}
