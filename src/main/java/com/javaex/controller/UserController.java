package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	//*필드
	@Autowired //자동으로 올려서 메모리에 넣어주는 기능
	private UserDao userDao;
	
	
	
	//*생성자
	//*메소드 g/s
	
	
	//*일반 메소드
	
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
		
		int count = userDao.insert(userVo);
		System.out.println("userController count" + count);
		
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
		
		UserVo authUser = userDao.selectUser(userVo);
		//System.out.println("controller-->" + authUser.toString()); 확인용
		
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
		
		//세션 authUser 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println(authUser); //값 확인
		
		//세션 authUser의 no값 가져오기 --> 회원정보 가져오기 위해서
		int no = authUser.getNo();
		
		UserVo userVo = userDao.selectOne(no);
		//System.out.println("controller-->" + userVo.toString());
		
		//회원정보 userVo를 model로 전송해주기
		model.addAttribute("userVo", userVo);
		
		return "/user/modifyForm";
	}
	
	//회원정보 수정  http://localhost:8088/mysite5/user/modify?password=5678&name=김태현&gender=male
	@RequestMapping(value = "/modify" , method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");
		
		System.out.println("회원정보 수정 값 확인" + userVo.toString());
		
		//세션 authUser 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//userVo의 no는 받아온 값이 없고 -> 같은 사람이니 넘버가 같음 -> no는 세션에 있으니 세션에서 가져와서 넣어줌
		userVo.setNo(authUser.getNo()); //그림 그려보면 이해 바로 됨
		System.out.println("no받은 회원정보 수정 값 확인" + userVo.toString());
		
		//회원정보 수정
		userDao.update(userVo);
		
		//수정되고 난 후 이름 바뀌면 메인페이지 이름도 바뀐 값으로
		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
	
}
