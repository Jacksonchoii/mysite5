package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//방명록 리스트
	public List<GuestbookVo> addList(){
		System.out.println("GuestbookService addList()");
		
		return guestbookDao.getGuestList();
	}
	
	//방명록 등록
	public int add(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService add()");
		
		return guestbookDao.guestInsert(guestbookVo);
	}
	
	//방명록 삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService delete()");
	
		int count = guestbookDao.guestDelete(guestbookVo);
		return count;
	}
	
	//ajax 글 저장 + 저장된 글 조회
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService writeResultVo");
		//기존 글 저장 
		//guestbookDao.guestInsert(guestbookVo);
		//고민이 생김 -> 저장한 글 조회 , 글번호?? 최근글 -> 저장하고 최근글 , 방금 저장된 글이 아닐 수도 있음 
		
		//글 저장 --> 번호 알기
		//int no = guestbookDao.insertSelectKey(guestbookVo); //no값을 알 수 있다.
		System.out.println("service : dao실행전 -->" + guestbookVo);
		
		guestbookDao.insertSelectKey(guestbookVo);
		System.out.println("service : dao실행후 -->" + guestbookVo);
		
		int no = guestbookVo.getNo(); //여기서 no를 가져오면된다.
		
		//글 1개 가져오기
		
		return guestbookDao.selectOne(no);
	}
	
	
	
}
