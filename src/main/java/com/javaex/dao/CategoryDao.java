package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 회원가입시 미분류 카테고리 생성
	public int insertBasicCategory(String id) {
		System.out.println("CategoryDao > insertBasicCategory()");
		int count = sqlSession.insert("category.insertBasicCategory", id);
		return count;
	}
	
	// 카테고리 리스트 불러오기 (카테고리 관리자 페이지 Rownum)
	public List<CategoryVo> categoryList(String id){
		System.out.println("CategoryDao > categoryList");
		List<CategoryVo> cList = sqlSession.selectList("category.categoryList", id);
		return cList;
	}
	
	// 카테고리 생성
	public int insertNewCategory(CategoryVo categoryVo) {
		System.out.println("CategoryDao > insertNewCategory");
		int count = sqlSession.insert("category.insertNewCategory", categoryVo);
		return count;
	}
	
	//카테고리 삭제
	public int deleteCateogry(int cateNo) {
		System.out.println("CategoryDao > deleteCateogry");
		int count = sqlSession.delete("category.deleteCateogry", cateNo);
		return count;
	}
	
	// 카테고리 이름 리스트 불러오기
	public List<CategoryVo> getCategoryName(String id){
		System.out.println("CategoryDao > getCategoryName");
		List<CategoryVo> cList = sqlSession.selectList("category.getCategoryName", id);
		return cList;
	}

}
