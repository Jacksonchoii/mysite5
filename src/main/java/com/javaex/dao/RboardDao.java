package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	@Autowired
	private SqlSession sqlsession;

	// 댓글 게시판 리스트
	public List<RboardVo> rboardList() {
		System.out.println("dao : rboardList()");

		return sqlsession.selectList("rboard.selectList");

	}

	// 댓글 게시판 글쓰기
	public int rboardInsert(RboardVo rboardVo) {
		System.out.println("dao : rboardInsert()");

		return sqlsession.insert("rboard.insert", rboardVo);
	}

}
