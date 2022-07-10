package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao cDao;
	@Autowired
	private BlogDao bDao;
	
	// 카테고리 리스트 받아오기
	public List<CategoryVo> getCategoryList(String id){
		System.out.println("CategoryService > getCategoryList ");
		// 카테고리 리스트 받아오기
		List<CategoryVo> cList = cDao.categoryList(id);
		
		return cList;
	}
	
	// 카테고리 생성
	public String insertNewCategory(CategoryVo categoryVo) {
		System.out.println("CategoryService > insertNewCategory");
		String state;
		
		int count = cDao.insertNewCategory(categoryVo);
		
		if(count > 0) {
			state = "success";
		}else {
			state = "fail";
		}
		
		return state;
	}
	
	// 카테고리 삭제
	public String deleteCateogry(String cateNo) {
		System.out.println("CategoryService > deleteCateogry");
		String state;
		
		int categoryNumber = Integer.parseInt(cateNo);
		
		int count = cDao.deleteCateogry(categoryNumber);
		
		if(count > 0) {
			state = "success";
		}else {
			state = "fail";
		}
		
		return state;
	}

}
