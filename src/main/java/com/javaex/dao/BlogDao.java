package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo BlogOneData(String id){
		System.out.println("BlogDao > BlogData");
		BlogVo bVo = sqlSession.selectOne("blog.BlogOneData", id);
		return bVo;
	}
	
//	public BlogVo getLatelyPosting(String id){
//		System.out.println("BlogDao > getLatelyPosting");
//		BlogVo bVo = sqlSession.selectOne("blog.getLatelyPosting", id);
//		return bVo;
//	}
	
	public BlogVo getLatelyPosting(Map<String, String> cateMap){
		System.out.println("BlogDao > getLatelyPosting");
		BlogVo bVo = sqlSession.selectOne("blog.getLatelyPosting", cateMap);
		return bVo;
	}
	
	public List<BlogVo> getBlogCateList(String id){
		System.out.println("BlogDao > getBlogCateList");
		List<BlogVo> bVo = sqlSession.selectList("blog.getBlogCateList", id);
		return bVo;
	}
	
	public List<BlogVo> getBlogPostList(String id){
		System.out.println("BlogDao > getBlogPostList");
		List<BlogVo> bVo = sqlSession.selectList("blog.getBlogPostList", id);
		return bVo;
	}
}