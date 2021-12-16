package co.micol.potal.member.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.micol.potal.member.service.MemberMapper;
import co.micol.potal.member.service.MemberService;
import co.micol.potal.member.service.MemberVO;

@Repository("memberDao") //컨테이너에 들어가있는것
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper map; //의존주입을 자동주입으로 바꿔달라
	//(member-map.xml이 자동주입된다 member-map.xml이 MemberMapper를 namespace 하고있기때문에.)
	
	
	@Override
	public List<MemberVO> memberSelectList() {
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		return map.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return map.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		return map.memberDelete(vo);
	}

	@Override
	public boolean memberIdCheck(String id) {
		return map.memberIdCheck(id);
	}

}
