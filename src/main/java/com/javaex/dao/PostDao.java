package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public int insertPosting(PostVo postVo) {
		System.out.println("PostDao > insertPosting");
		int count = sqlSession.insert("post.insertPosting", postVo);
		return count;
	}

}