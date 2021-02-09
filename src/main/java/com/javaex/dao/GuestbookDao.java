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

	// selectKey 글저장
	public void insertSelectKey(GuestbookVo guestbookVo) {
		
		System.out.println("dao : insertSelectKey()");
		System.out.println("dao : 실행전" + guestbookVo); // name , password, content 3개
		
		sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		System.out.println("실행 후" + guestbookVo); // no, name, password, content 4개
		
		//return guestbookVo.getNo(); 같은 번호이기 때문에 굳이 받을 필요 없다. 
	}
	
	// 글 1개 가져오기
	public GuestbookVo selectOne(int no) {
		
		System.out.println("dao : selectOne()");
		
		return sqlSession.selectOne("guestbook.select", no);
		
	}
	
	

}
