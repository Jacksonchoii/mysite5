package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("userService join()");
		
		return userDao.insert(userVo);
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService login()");
		
		return userDao.selectUser(userVo);
		
	}
	
	//회원정보 수정 폼
	public UserVo modifyForm(int no) {
		System.out.println("userService modifyForm()");
	
		//값 확인해보려면
		//UserVo userVo = userDao.selectOne(no); 
		//System.out.println(userVo)
		
		return userDao.selectOne(no);
	}
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("userService modify()");
		
		return userDao.update(userVo);
	}
	
	//회원가입 아이디 체크
	public String idcheck(String id) {
		System.out.println("userService idcheck()" + id);
		
		UserVo userVo = userDao.selectOne(id);
		
		String result = "";
		
		if(userVo == null) {
			//사용할 수 있는 id
			result = "can";
		} else {
			//사용할 수 없는 id
			result = "cant";
		}
		
		return result;
	}
	
}
