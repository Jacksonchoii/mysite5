package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//전체 리스트 가져오기(ajax)
	@ResponseBody //데이터로 보고 json으로 넣어줌 . 순수한 데이터로 보기 
	@RequestMapping(value = "/list")
	public List<GuestbookVo> list() {
		System.out.println("[ApiGuestbookController] /ajaxlist");
		
		return guestbookService.addList();
	}
	
	
	
	//글 작성(ajax)
	@ResponseBody
	@RequestMapping(value = "/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController] /write");
		System.out.println(guestbookVo);
		
		//입력된 vo값 전달하고 저장된vo 받아야함 --> 그래야 화면에 전달 출력
		return guestbookService.writeResultVo(guestbookVo);
	}
}
