package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {

	//*필드
	@Autowired
	private GuestbookService guestbookService;
	
	//*생성자
	//*메소드 g/s
	
	//*일반 메소드
	
	//방명록 리스트
	@RequestMapping(value = "/addList" , method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("/guestbook/addList");
		
		List<GuestbookVo> guestbookList = guestbookService.addList();
		//데이터 전송 
		model.addAttribute("gList", guestbookList);
		
		return "/guestbook/addList";
	}
	
	//방명록 등록 http://localhost:8088/mysite5/guestbook/add?name=최태현&password=1234&content=테스트&action=add
	@RequestMapping(value = "/add" , method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) { //name,password,content
		System.out.println("/guestbook/add");
		
		guestbookService.add(guestbookVo);
		
		return "redirect:/guestbook/addList";
	}
	
	//방명록 삭제 폼 http://localhost:8088/mysite5/guestbook/deleteForm?no=2
	@RequestMapping(value = "/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("/guestbook/deleteForm");
		
		return "/guestbook/deleteForm";
	}
	
	//방명록 삭제
	@RequestMapping(value = "/delete" , method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) { //no, password
		System.out.println("/guestbook/delete");
		
		int count = guestbookService.delete(guestbookVo);
		
		if(count == 1) {//삭제 성공
			return "redirect:/guestbook/addList";
		} else { //실패
			return "redirect:/guestbook/deleteForm?count=0&no=" + guestbookVo.getNo();
		}
	}
		
	
}
