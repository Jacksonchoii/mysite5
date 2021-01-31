package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private HttpSession session;

	// 게시판 리스트
	public List<BoardVo> list() {
		System.out.println("BoardService list()");

		return boardDao.getBoardList();
	}

	// 게시판 글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("BoardService write()");

		//controller의 역할이 아닌 것 같아 Service에서 실행 -- 코드리뷰 때 확인 해 볼 것
		// 세션 authUser 가져오기 -- *UserVo
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		// 세션 no값 가져오기
		int no = authUser.getNo();

		// 세션에서 가져온 no값 넣어주기 한 방에 해도 되는데 일단 정리하고 알기 쉽게 나눠서 코딩
		boardVo.setUserNo(no);

		return boardDao.boardInsert(boardVo);
	}

	// 게시글 보기
	public BoardVo read(int no) {
		System.out.println("BoardService read()");
		
		//게시글 보기
		BoardVo boardVo = boardDao.selectOneRead(no);
		
		//조회수 1증가
		boardDao.updateHit(no);
	
		return boardVo;
	}
	
	// 게시글 수정 폼
	public BoardVo modifyForm(int no) {
		System.out.println("BoardService modifyForm()");
		
		return boardDao.selectOneRead(no);
	}
	
	// 게시글 수정
	public int modify(BoardVo boardVo) {
		System.out.println("BoardService modify()");
		
		return boardDao.boardUpdate(boardVo);
	}
	
	// 게시글 삭제
	public int erase(int no) {
		System.out.println("BoardService erase()");
		
		return boardDao.boardDelete(no);
	}
	
}
