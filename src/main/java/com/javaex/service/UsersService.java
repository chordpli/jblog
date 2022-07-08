package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.UsersVo;

@Service
public class UsersService {

	@Autowired
	private UsersDao uDao;
	private CategoryDao cDao;
	
	// 회원 가입
	public int joinUser(UsersVo usersVo) {
		System.out.println("UsersService > joinUser");
		
		int insertCate = cDao.insertBasicCategory(usersVo.getId());
		int count = uDao.join(usersVo);
		
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
