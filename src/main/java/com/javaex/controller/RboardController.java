package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/rboard")
public class RboardController {

	@Autowired
	private RboardService rboardService;

	// 댓글 게시판 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("RboardController list()");

		// 리스트
		List<RboardVo> rboardList = rboardService.rboardList();

		// 데이터 전송
		model.addAttribute("rbList", rboardList);

		return "/rboard/rboardList";
	}

	// 댓글 게시판 글쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("RboardController writeForm()");

		return "rboard/rboardWriteForm";
	}

	// 댓글 게시판 글쓰기
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute RboardVo rboardVo, HttpSession session) {// title, content
		System.out.println("RboardController write()");

		// 강사님이 session 웹 개념이라 컨트롤러에서 사용하라고 했음 -- 어제 코드리뷰 확인
		// 세션 authUser 가져오기 
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 세션 no값 가져오기
		int no = authUser.getNo();

		// 세션에서 가져온 no값 넣어주기 한 방에 해도 되는데 일단 정리하고 알기 쉽게 나눠서 코딩
		rboardVo.setUserNo(no);

		rboardService.write(rboardVo);

		return "redirect:/rboard/list";
	}

	
}
