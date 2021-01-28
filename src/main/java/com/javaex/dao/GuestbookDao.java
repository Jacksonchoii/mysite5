package com.javaex.dao;

import java.util.List; 

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;
	

	// 전체리스트
	public List<GuestbookVo> getGuestList() {
		
		System.out.println("dao : getGuestList()");
	
		return sqlSession.selectList("guestbook.selectList");
	}
	
	// 방명록 등록
	public int guestInsert(GuestbookVo guestbookVo) {
		
		System.out.println("dao : guesInsert()");
		
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	// 방명록 삭제
	public int guestDelete(GuestbookVo guestbookVo) {
		
		System.out.println("dao : guesDelete()");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		
		return count;
	}

	
	
	
	
	

}
