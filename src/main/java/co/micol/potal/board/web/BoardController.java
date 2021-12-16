package co.micol.potal.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.micol.potal.board.service.BoardService;
import co.micol.potal.board.service.BoardVO;

@Controller
public class BoardController {
	@Autowired
	BoardService boardDao;
	
	//���� mapper �޼ҵ带 ������ش�.
	@RequestMapping("/noticeList.do")
	public String noticeList(Model model) {
		model.addAttribute("boards", boardDao.boardSelectList());
		return "board/noticeList";
	}
	
	@PostMapping("/boardRead.do")
	public String boardRead(BoardVO vo, Model model) {
		vo=boardDao.boardSelect(vo);
		if(vo!=null) {
			model.addAttribute("board",vo); //���� ���
			boardDao.boardHit(vo.getNo()); //��ȸ�� ����
		}else {
			model.addAttribute("message","�Խñ��� �������� �ʽ��ϴ�.");
		}
		return "board/boardRead";
		
	}
}	
