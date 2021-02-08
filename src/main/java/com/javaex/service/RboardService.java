package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {

	@Autowired
	private RboardDao rboardDao;
	
	//댓글 게시판 리스트
	public List<RboardVo> rboardList(){
		System.out.println("RboardService rboardList()");
		
		return rboardDao.rboardList();
	}
	
	//댓글 게시판 글쓰기
	public void write(RboardVo rboardVo) {
		System.out.println("RboardService write()");
		
		if(rboardVo.getGroupNo() > 0) {
			rboardVo.setOrderNo(rboardVo.getOrderNo());
			
			
		} else {
			rboardDao.rboardInsert(rboardVo);
		}
		
	}
	
	
	
}
