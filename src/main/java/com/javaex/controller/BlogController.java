package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService bService;
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blog(@PathVariable("id") String id
						,@PathVariable(value = "cateName", required = false) String cateName
						, Model model) {
		System.out.println("BlogController > blog");
		Map <String, Object> bMap;
		
		if(cateName == null) {
			bMap = bService.getBlogData(id);
			System.out.println("BlogController > blog > getBlogData(id)");
		}else {
			bMap = bService.getBlogData(id, cateName);
			System.out.println("BlogController > blog > getBlogData(id, cateName)");
		}
		
		
		model.addAttribute("blog", bMap);
		
		return "blog/blog-main";
	}

}
