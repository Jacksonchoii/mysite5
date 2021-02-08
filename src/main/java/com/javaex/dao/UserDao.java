package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원가입--> DB입장에서 회원정보저장 이니까 insert / 하나의 DAO는 한 가지 기능만 할 것(쿼리문하나)
	public int insert(UserVo userVo) {
		System.out.println("user dao : insert 확인");
		System.out.println(userVo.toString());
		
		//int count = sqlSession.insert("user.insert", userVo); 어느정도 확인했으면 지우기
		//System.out.println("user insert count: " + count);
		
		return sqlSession.insert("user.insert", userVo);
	}

	//로그인 --> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("user dao : selectUser 확인");
		System.out.println(userVo.toString());
		
		//UserVo vo = sqlSession.selectOne("user.selectUser", userVo); 확인되었으니 코드 정리
		//System.out.println(vo.toString());
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	//회원정보 수정 폼 --> 회원정보 가져오기
	public UserVo selectOne(int no) {
		System.out.println("user dao : selectOne 확인");
		System.out.println(no);
		
		return sqlSession.selectOne("user.selectOne",no);
	}
	
	//회원정보 수정
	public int update(UserVo userVo) { //int로 count 리턴이 굳이 필요한가?.. 없어보임 일단 void로 다시 int로
		System.out.println("user dao : update 확인");
		System.out.println(userVo);
		
		return sqlSession.update("user.update", userVo);
	}
	
	//회원가입 아이디 체크
	public UserVo selectOne(String id) {
		System.out.println("user dao : selectOne(idcheck)" + id);
		
		return sqlSession.selectOne("user.selectById", id);
		
	}
	
	
}
