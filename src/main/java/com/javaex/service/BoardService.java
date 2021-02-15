package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 리스트(리스트+검색)
	public List<BoardVo> getBoardList2(String keyword){
		System.out.println("BoardService getBoardlist2()");
		System.out.println("keyword = " + keyword);
		
		List<BoardVo> boardList = boardDao.selectList2(keyword);
	
		return boardList;
	}
	
	
	// 리스트(리스트+검색+페이징)
		public Map<String, Object> getBoardList3(String keyword, int crtPage){
			System.out.println("BoardService getBoardlist3()");
			System.out.println("keyword = " + keyword);
			
			
			//crtPage --> 시작번호, 끝번호 1 --> 1,10          2페이지 --> 11,20      3페이지 -->   21,30
			
			////////////////////////////////////
			////리스트 구하기
			///////////////////////////////////
			
			//페이지당 글갯수
			int listCnt = 10;
			
			//현재 페이지
			crtPage = (crtPage > 0)? crtPage : 1; 
			
			/* 같은 의미
			 if(crtPage > 0) { 
			 	crtPage = crtPage; 
			 }else { crtPage = 1; 
			 }
			 */
			
			//시작글 번호 startRNum
			int startRNum = (crtPage-1) * listCnt + 1;
						
			//끝 글 번호 endRNum
			int endRNum = (startRNum + listCnt) - 1;
			
			List<BoardVo> boardList = boardDao.selectList3(keyword, startRNum, endRNum);
		
			///////////////////////////////
			////페이징 계산
			//////////////////////////////
			
			//페이지당 버튼 갯수
			int pageBtnCount = 5;
			
			
			//전체 글 갯수 구하기
			int totalCount = boardDao.selectTotalCnt(keyword);
			
			
			//1 --> 1~5
			//2 --> 1~5
			//3 --> 1~5
			//4 --> 1~5
			//5 --> 1~5
			//6 --> 6~10
			
			//마지막 버튼 번호
			int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount; //(1/5)*5 --> 0*5-->0
			
			//시작 버튼 번호
			int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
			
			//다음 버튼 boolean
			boolean next;
			
			if(endPageBtnNo * listCnt < totalCount) {
				next = true;
			}else {
				next = false;
				endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
			}
			
			//이전 버튼 boolean
			boolean prev ;
			
			if(startPageBtnNo != 1) {
				prev = true;
			}else {
				prev = false;
			}
			
			//boardList, prev, startPageBtnNo, endPageBtnNo, next --> jsp 전달
			
			Map<String, Object> pMap = new HashMap<String, Object>();
			
			pMap.put("boardList", boardList);
			pMap.put("prev", prev);
			pMap.put("startPageBtnNo", startPageBtnNo);
			pMap.put("endPageBtnNo", endPageBtnNo);
			pMap.put("next", next);
			
			return pMap;
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
		
		for(int i = 1; i<=1234; i++) {
			boardVo.setTitle(i + "번째 글 입니다.");
			

		}

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
