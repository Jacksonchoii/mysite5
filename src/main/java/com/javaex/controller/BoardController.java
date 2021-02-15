package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	//*필드
	
	@Autowired
	private BoardService boardService;
	
	//*생성자
	//*메소드 g/s
	
	//*일반 메소드
	
	//게시판 리스트
	@RequestMapping(value = "/list" , method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("/board/list");
		
		//리스트
		List<BoardVo> boardList = boardService.list();
		
		//데이터 전송
		model.addAttribute("bList", boardList);
		
		return "/board/list";
	}
	
	
	//리스트(리스트+검색기능)
	@RequestMapping(value = "/list2" , method = {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, Model model) {//있을 수도 있고 없을 수도 있고
		System.out.println("/boardController/list2");
		
		
		List<BoardVo> boardList = boardService.getBoardList2(keyword);
		model.addAttribute("bList", boardList);
		
		return "/board/list2";
	}
	
	// 리스트(리스트+검색+페이징)
	@RequestMapping(value = "/list3" , method = {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
						@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage,
						Model model) { //defaultValue 값은 문자열로
		System.out.println("/boardController/list3");
		System.out.println("keyword=" + keyword);
		System.out.println("crtPage=" + crtPage);
		
		
		Map<String, Object> pMap = boardService.getBoardList3(keyword, crtPage);
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		
		
		return "board/list3";
	}
	
	
	
	
	
	//게시판 글쓰기 폼
	@RequestMapping(value = "/writeForm" , method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("/board/writeForm");
		
		return "/board/writeForm";
	}
	
	//게시판 글쓰기
	@RequestMapping(value ="/write" , method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {//title, content
		System.out.println("/board/write");
		
		boardService.write(boardVo);
		
		return "redirect:/board/list";
	}
	
	//게시글 보기
	@RequestMapping(value = "/read" , method = {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("/board/read");
		
		BoardVo boardVo = boardService.read(no);
		
		//데이터 전송
		model.addAttribute("boardVo", boardVo);
		
		return "/board/read";
	}
	
	//게시글 수정 폼
	@RequestMapping(value = "/modifyForm" , method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("/board/modifyForm");
		
		BoardVo boardVo = boardService.modifyForm(no);
		
		//데이터 전송
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	
	//게시글 수정
	@RequestMapping(value = "/modify" , method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("/board/modify");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	//게시글 삭제
	@RequestMapping(value = "/delete" , method = {RequestMethod.GET, RequestMethod.POST})
	public String erase(@RequestParam("no") int no) {
		System.out.println("/board/delete");
		
		boardService.erase(no);
		
		return "redirect:/board/list";
	}	
	
}
