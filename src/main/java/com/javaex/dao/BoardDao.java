package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	//필드
	@Autowired
	private SqlSession sqlsession;
	
	//게시판 리스트
	public List<BoardVo> getBoardList(){
		System.out.println("dao : getBoardList()");
		
		return sqlsession.selectList("board.selectList");	
	}
	
	//게시판 글쓰기
	public int boardInsert(BoardVo boardVo) {
		System.out.println("dao : boardInsert()");
		
		return sqlsession.insert("board.insert", boardVo);
	}
	
	//조회수 증가
	public int updateHit(int no) {
		System.out.println("dao : updateHit()");
		
		return sqlsession.update("board.updateHit", no);
	}
	
	//게시글 보기
	public BoardVo selectOneRead(int no) {
		System.out.println("dao : selectOneRead()");
		
		return sqlsession.selectOne("board.selectOneRead", no);
	}
	
	//게시글 수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("dao : boardUpdate()");
		
		return sqlsession.update("board.update", boardVo);
	}
	
	//게시글 삭제
	public int boardDelete(int no) {
		System.out.println("dao : boardDelete()");
		
		return sqlsession.delete("board.delete", no);
	}
	
}
