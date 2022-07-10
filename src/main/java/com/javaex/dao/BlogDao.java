package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////* 회원가입 *////////////////////////////////////
	// 회원가입시 블로그 생성
	public int createBlog(String id) {
		System.out.println("BlogDao > createBlog");
		int count = sqlSession.insert("blog.createBlog", id);
		return count;
	}
	// 회원가입시 블로그 타이틀을 수정하기 위해 블로그 정보 가지고 오기
	public BlogVo getBlogData(String id) {
		System.out.println("BlogDao > BlogData");
		BlogVo blogVo = sqlSession.selectOne("blog.getBlogDataForUpdateTitle", id);
		return blogVo;
	}
	
	// 회원가입시 기본 블로그명 설정
	public int updateBasicBlogTitle(BlogVo blogVo) {
		System.out.println("BlogDao > updateBasicBlogTitle");
		int count = sqlSession.update("blog.updatecBlogTitle", blogVo);
		return count;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////* 블로그 메인 정보 얻어오기 */////////////////////////////
	// 유저 이름, 블로그 제목, 로고, id
	public BlogVo BlogOneData(String id){
		System.out.println("BlogDao > BlogData");
		BlogVo bVo = sqlSession.selectOne("blog.BlogOneData", id);
		return bVo;
	}
	// 블로그 최근 포스팅 정보 유저 이름, 게시글 제목, 게시글 등록일, 내용
	public BlogVo getLatelyPosting(String id){
		System.out.println("BlogDao > getLatelyPosting");
		BlogVo bVo = sqlSession.selectOne("blog.getLatelyPosting", id);
		return bVo;
	}
	// 카테고리 목록 불러오기
	public List<CategoryVo> getBlogCateList(String id){
		System.out.println("BlogDao > getBlogCateList");
		List<CategoryVo> cVo = sqlSession.selectList("blog.getBlogCateList", id);
		return cVo;
	}
	// 최초 블로그 카테고리 블러오기
	public BlogVo getFirstCategory(String id) {
		System.out.println("BlogDao > getFirstCategory");
		BlogVo bVo = sqlSession.selectOne("blog.getFirstCategory", id);
		return bVo;
	}
	// 최초 카테고리 블로그 게시물 불러오기
	public List<BlogVo> getBlogPostList(Map<String, Object> cateMap){
		System.out.println("BlogDao > getBlogPostList");
		List<BlogVo> bVo = sqlSession.selectList("blog.getBlogPostList", cateMap);
		return bVo;
	}
	//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////* 블로그 메인 선택 *//////////////////////////////////
	// 카테고리 선택 후 최초 게시물
	public BlogVo getLatelyPostingInCategory(Map<String, Object> blogVo) {
		System.out.println("BlogDao > getLatelyPostingInCategory");
		BlogVo bVo = sqlSession.selectOne("blog.getLatelyPostingInCategory", blogVo);
		return bVo;
	}
	// 카테고리 선택 후 블로그 게시물 불러오기
	public List<BlogVo> getBlogPostListInCategory(Map<String, Object> cateMap){
		System.out.println("BlogDao > getBlogPostListInCategory");
		List<BlogVo> bVo = sqlSession.selectList("blog.getBlogPostListInCategory", cateMap);
		return bVo;
	}
	
	// 게시물 선택 읽기
	public BlogVo getReadPosting(Map<String, Object> blogVo) {
		System.out.println("BlogDao > getReadPosting");
		BlogVo bVo = sqlSession.selectOne("blog.getReadPosting", blogVo);
		return bVo;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////* 블로그 관리자 페이지 관리 *///////////////////////////////
	// 블로그 관리페이지에 필요한 데이터
	public BlogVo getBlogAdminData(Map<String, Object> id) {
		System.out.println("BlogDao > getBlogAdminData");
		BlogVo bVo = sqlSession.selectOne("blog.getBlogAdminData", id);
		return bVo;
	}
	
	// 블로그 기본 업데이트
	public int updateBasicBlog(BlogVo blogVo) {
		int count = sqlSession.update("blog.updateBlog", blogVo);
		return count;
	}
	
	// 기존의 로고 이미지 불러오기
	public String loadLogoFile(String id) {
		String existingPath = sqlSession.selectOne("blog.loadLogoFile", id);
		return existingPath;
	}
	
}