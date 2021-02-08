package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	//*필드
	
	@Autowired
	private UserService userService;
	
	//*생성자
	//*메소드 g/s
	
	
	//*일반 메소드 / 컨트롤러는 기술이 달라질 수 있음 -> 분리시키면 기술이 달라도 독립적으로 받을 수 있음
	
	//회원가입 폼
	@RequestMapping(value = "/joinForm" , method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join" , method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo.toString());
		
		int count = userService.join(userVo);
		
		return "user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value = "/loginForm" , method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "/login" , method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {//Session은 브라우저마다 가지고있는 session에 접근하는 것
		System.out.println("/user/login");
		System.out.println(userVo.toString());
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser == null) {//로그인 실패했을 때
			
			return "redirect:/user/loginForm?result=fail";
			
		} else {//로그인 성공했을 때
			System.out.println("login 성공");
			
			session.setAttribute("authUser", authUser);
			
			return "redirect:/"; //메인으로 이동
		}
	}
	
	//로그아웃
	@RequestMapping(value = "/logout" , method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/"; //메인으로 이동
	}
	
	//회원정보 수정 폼
	@RequestMapping(value = "/modifyForm" , method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("/user/modifyForm");
		
		/*혼자 했을 때 세션값을 authUser에 담고 진행
		 
		//세션 authUser 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println(authUser); //값 확인
		//세션의 authUser에서 no값 가져오기
		int no = authUser.getNo();
		
		같은 코드지만 아래 코드는 한 번에 no값 가져온 것 - 쌤 코드 */
		
		//세션의 no값 가져오기 --> 회원정보 가져오기 위해서
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		
		//세션값이 없으면 --> 로그인 폼
		
		
		//회원정보 가져오기
		UserVo userVo = userService.modifyForm(no);
		
		//회원정보 userVo를 model로 전송해주기 --> jsp에 데이터 전송
		model.addAttribute("userVo", userVo);
		
		return "/user/modifyForm";
	}
	
	//회원정보 수정  http://localhost:8088/mysite5/user/modify?password=5678&name=김태현&gender=male
	@RequestMapping(value = "/modify" , method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) { //session은 브라우저에 한정된 기술이라 controller(프레젠테이션 계층에서 처리하는게 좋음)
		System.out.println("/user/modify");
		
		System.out.println("회원정보 수정 값 확인" + userVo.toString());
		
		//세션 authUser 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//userVo의 no는 받아온 값이 없고 -> 같은 사람이니 넘버가 같음 -> no는 세션에 있으니 세션에서 가져와서 넣어줌
		userVo.setNo(authUser.getNo()); //그림 그려보면 이해 바로 됨
		System.out.println("no받은 회원정보 수정 값 확인" + userVo.toString());
		
		/* 같은 코드
		 // 세션에서 no값 가져오기
		 int no = authUser.getNo();
		 // no값 넣기
		 userVo.setNo(no);
		 */
		
		
		//회원정보 수정
		int count = userService.modify(userVo);
		
		//수정되고 난 후 이름 바뀌면 메인페이지 이름도 바뀐 값으로
		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
	
	//회원가입 - 아이디 체크
	@ResponseBody //return을 순수한 데이터로 보고 응답(response)의 body영역에 보낸다(데이터만 보낸다) 
	@RequestMapping(value = "/idcheck" , method = {RequestMethod.GET, RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id) { //많으면 @ModelAttribute
		//패스워드는 테스트용으로 코드 추가한 것 @RequestParam("password") String password
		
		System.out.println("/user/idcheck");
		System.out.println("checkid = " + id);
		//System.out.println("password = " + password);
		
		String result = userService.idcheck(id);
		
		System.out.println(result);
		
		return result; //원래는 "result" == /WEB-INF/views/result.jsp (jsp찾는 문법) but 데이터만 보내려고 한다.
	}
	
	
}
