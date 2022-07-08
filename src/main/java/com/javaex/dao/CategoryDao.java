package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 회원가입시 미분류 카테고리 생성
	public int insertBasicCategory(String id) {
		System.out.println("UsersDao > login()");
		int count = sqlSession.insert("category.insertBasicCategory", id);
		return count;
	}
	


}
