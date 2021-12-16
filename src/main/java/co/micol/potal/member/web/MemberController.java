package co.micol.potal.member.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.micol.potal.member.service.MemberService;
import co.micol.potal.member.service.MemberVO;

@Controller
public class MemberController {
   @Autowired
   MemberService memberDao; //DAO �ڵ�����
   
   @Autowired
   ServletContext servletContext; //����Ǵ� ������ ��Ʈ �н��� ����ϱ� ���ؼ�
   
//   @Autowired
//   String upload; // ��������� bean ��ü�� ����°�
   
   @RequestMapping("/loginForm.do")
   public String loginForm() {
      return "member/loginForm"; // ��ȣ��
   }
   @PostMapping("/memberLogin.do")
   public String memberLogin(MemberVO vo, Model model, HttpSession session) {
      vo = memberDao.memberSelect(vo); //���̵� �н����带 ������ ����� vo��ü�� �޴´�.
      if(vo !=null) {
         session.setAttribute("id", vo.getId());
         session.setAttribute("name", vo.getName());
         session.setAttribute("author", vo.getAuthor());
         model.addAttribute("message","�� ȯ���մϴ�.");
      }else {
         model.addAttribute("message","���̵� �Ǵ� �н����尡 Ʋ�Ƚ��ϴ�.");
      }
      return "member/memberLoginResult";
   }
   @RequestMapping("/memberLogout.do")
   public String memberLogout(HttpSession session, Model model) {
      String name =(String) session.getAttribute("name");//������ ������ �̸� ��������
      model.addAttribute("message", name + " �� ���������� �α׾ƿ� �Ǿ����ϴ�.");
      session.invalidate(); //session�� ���� �����Ѵ�
   
      return "member/memberLoginResult";
   }
   @RequestMapping("/memberJoinForm.do")
   public String memberJoinForm() {
      return "member/memberJoinForm";
   }
   @PostMapping("/idCheck.do")
   @ResponseBody
   public String idCheck(@RequestParam("id") String id) {
      boolean b = memberDao.memberIdCheck(id);
      if(b) {
         return "0";   //�����Ҷ�
      }else {
         return "1";   //�������� ���� ��   
      }
   }
   //�ؽ�Ʈ ������ �ƴ� ���ϵ��� Mutipartfile�� ��´� ��)����,��������
   @PostMapping("/memberJoin.do")
   public String memberJoin(MemberVO vo, MultipartFile file, Model model) {
      //String filePath="c://fileTemp//";
      String upload = servletContext.getRealPath("resources");   
      upload= upload + "//fileUpload//";//������ ����� ���� �������
      String sourceFileName = file.getOriginalFilename(); // �������� �� 
      String uuid = UUID.randomUUID().toString(); // �������� ���ϸ� �浹������ ���� �˸��ƽ����� ���(UUID)
      String targetFileName = uuid + sourceFileName.substring(sourceFileName.lastIndexOf("."));
      
      if(!sourceFileName.isEmpty()) {   //������ ���� �� �� �� ������ �ش�.
         try {
            file.transferTo(new File(upload, targetFileName)); //������ ���� �� ���ϸ� ����
            vo.setImgFile(sourceFileName);
            vo.setPimgFile(targetFileName);
            int result = memberDao.memberInsert(vo);
            if(result != 0) {
               model.addAttribute("message","<br>ȸ�������� ���� �߽��ϴ�.<br> �α��� �� �̿밡�� �մϴ�.");
            }else {
               model.addAttribute("message","ȸ�������� ���� �߽��ϴ�.,<br>�ٽ� �������ּ���.");
            }
         }catch (Exception e) {
            e.printStackTrace();
         }
      }else {
         int result = memberDao.memberInsert(vo);
         if(result != 0) {
            model.addAttribute("message","<br>ȸ�������� ���� �߽��ϴ�.<br> �α��� �� �̿밡�� �մϴ�.");
         }else {
            model.addAttribute("message","ȸ�������� ���� �߽��ϴ�.<br>�ٽ� �������ּ���.");
         }
      }
      return "member/memberJoin";
   }
   
   @RequestMapping("/memberInfo.do")
   public String memberInfo(MemberVO vo, Model model, HttpSession session) {
	   vo.setId((String)session.getAttribute("id"));
	   model.addAttribute("member", memberDao.memberSelect(vo));
	   return "member/memberInfo";
   }
   
   @RequestMapping("/ajaxMemberList.do")
   @ResponseBody
   public List<MemberVO> ajaxMemberList(){
	   return memberDao.memberSelectList();
   }
}
   