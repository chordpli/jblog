package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UsersVo;

@Service
public class UsersService {

	@Autowired
	private UsersDao uDao;
	@Autowired
	private CategoryDao cDao;
	@Autowired
	private BlogDao bDao;
	
	// 회원 가입
	public int joinUser(UsersVo usersVo) {
		System.out.println("UsersService > joinUser");
		
		// 회원가입
		int count = uDao.join(usersVo);
		System.out.println("userID : " + usersVo.getId());
				
				
		///////////////////////////////////블로그///////////////////////////////////
		// 기본 블로그 생성
		int createBlog = bDao.createBlog(usersVo.getId());
		// 블로그 타이틀 수정
		BlogVo blogVo = bDao.getBlogData(usersVo.getId());
		blogVo.setBlogTitle(usersVo.getId() + blogVo.getBlogTitle());
		bDao.updateBasicBlogTitle(blogVo);
		
		//////////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////카테고리///////////////////////////////////
		// 기본 카테고리 생성
		int insertCate = cDao.insertBasicCategory(usersVo.getId());
		System.out.println("UsersService > joinUser > insertBasicCategory");
		/////////////////////////////////카테고리///////////////////////////////////

		System.out.println(createBlog + "개의 블로그가 생성되었습니다.");
		System.out.println("카테고리가 " + insertCate + "개 생성되었습니다.");
		System.out.println(count + "명의 회원이 가입되었습니다.");
		
		return count;
	}

	// 로그인
	public UsersVo login(UsersVo usersVo) {
		System.out.println("UsersService > joinUser");
		UsersVo authUser = uDao.login(usersVo);
		return authUser;
	}

	// 중복확인
	public String idCheck(String id) {

		UsersVo idCheck = uDao.idCheck(id);
		
		String result;

		if (idCheck == null) {
			result = "success";
		} else {
			result = "fail";
		}
		System.out.println(result);
		return result;
	}

}
