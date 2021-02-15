package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//리스트(리스트+검색) - 글 전체가져오기(키워드)
	public List<BoardVo> selectList2(String keyword){
		System.out.println("dao : selectList2()");
		System.out.println("keyword = " + keyword);
		
		
		return sqlsession.selectList("board.selectList2", keyword);
		
	}
	
	//리스트(리스트+검색+페이징) - 글 전체가져오기(키워드+페이징)
		public List<BoardVo> selectList3(String keyword, int startRNum, int endRNum){
			System.out.println("dao : selectList3()");
			
			//map으로 묶는게 낫다 --> 다른 곳에선 안 쓸 것 같아서
			Map<String, Object> map = new HashMap<String, Object>(); //Map만 생성
			
			map.put("keyword", keyword);
			map.put("startRNum", startRNum);
			map.put("endRNum", endRNum);
			
			System.out.println("map=" + map);
			
			return sqlsession.selectList("board.selectList3", map);
				
	}
	
	// 전체 글 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("dao : selectTotalCount");
		
		return sqlsession.selectOne("board.selectTotalCnt", keyword);
		
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
