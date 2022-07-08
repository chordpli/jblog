package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao bDao;
	
	
	public Map<String, Object> getBlogData(String id){
		System.out.println("BlogService > BlogData(String id)");
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		Map<String, String> cateMap = new HashMap<String, String>();
		cateMap.put("id", id);
		cateMap.put("cateName", null);
		
		BlogVo blogOneData = bDao.BlogOneData(id);
		BlogVo latelyPosting = bDao.getLatelyPosting(cateMap);
		List<BlogVo> cateList = bDao.getBlogCateList(id);
		List<BlogVo> postList = bDao.getBlogPostList(id);
		
		System.out.println(blogOneData);
		System.out.println(latelyPosting);
		System.out.println(cateList);
		System.out.println(postList);
		
		bMap.put("blogOneData", blogOneData);
		bMap.put("latelyPosting", latelyPosting);
		bMap.put("cateList", cateList);
		bMap.put("postList", postList);
		
		return bMap;
	}
	
	public Map<String, Object> getBlogData(String id, String cateName){
		System.out.println("BlogService > BlogData(String id, String cateName)");
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		Map<String, String> cateMap = new HashMap<String, String>();
		
		cateMap.put("id", id);
		cateMap.put("cateName", cateName);
		
		BlogVo blogOneData = bDao.BlogOneData(id);
		BlogVo latelyPosting = bDao.getLatelyPosting(cateMap);
		List<BlogVo> cateList = bDao.getBlogCateList(id);
		List<BlogVo> postList = bDao.getBlogPostList(id);
		
		System.out.println(blogOneData);
		System.out.println(latelyPosting);
		System.out.println(cateList);
		System.out.println(postList);
		
		bMap.put("blogOneData", blogOneData);
		bMap.put("latelyPosting", latelyPosting);
		bMap.put("cateList", cateList);
		bMap.put("postList", postList);
		
		return bMap;
	}
	

}
