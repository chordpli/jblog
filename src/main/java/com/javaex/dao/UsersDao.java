package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UsersVo;

@Repository
public class UsersDao {
	
	@Autowired
	SqlSession sqlSession;
	
	// 회원 가입
	public int join(UsersVo userVo) {
		System.out.println("UsersDao > join()");
		int count = sqlSession.insert("users.join", userVo);
		return count;
	}
	
	// 로그인
	public UsersVo login(UsersVo userVo) {
		System.out.println("UsersDao > login()");
		UsersVo authUser = sqlSession.selectOne("users.login", userVo);
		return authUser;
	}
	
	// 아이디 중복 확인
	public UsersVo idCheck(String id) {
		System.out.println("UsersDao > idCheck()");
		UsersVo idCheck = sqlSession.selectOne("users.idCheck", id);
		return idCheck;
	}
}