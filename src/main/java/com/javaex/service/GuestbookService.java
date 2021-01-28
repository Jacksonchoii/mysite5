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
	
}
