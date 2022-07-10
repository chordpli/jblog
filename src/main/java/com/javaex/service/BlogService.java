package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao bDao;
	@Autowired
	private CategoryDao cDao;
	@Autowired
	private PostDao pDao;

	// 블로그 처음 들어갔을 때.
	public Map<String, Object> getBlogData(String id) {
		System.out.println("BlogService > BlogData(String id, String cateName)");

		Map<String, Object> bMap = new HashMap<String, Object>();
		Map<String, Object> cateMap = new HashMap<String, Object>();

		cateMap.put("id", id);
		BlogVo cateNo = bDao.getFirstCategory(id);
		cateMap.put("cateNo", cateNo.getCateNo());
		
		BlogVo blogOneData = bDao.BlogOneData(id);
		BlogVo latelyPosting = bDao.getLatelyPosting(id);
		List<CategoryVo> cateList = bDao.getBlogCateList(id);
		List<BlogVo> postList = bDao.getBlogPostList(cateMap);

		System.out.println("blogOneData" + blogOneData);
		System.out.println("latelyPosting" + latelyPosting);
		System.out.println("cateList" + cateList);
		System.out.println("postList" + postList);

		bMap.put("blogOneData", blogOneData);
		bMap.put("posting", latelyPosting);
		bMap.put("cateList", cateList);
		bMap.put("postList", postList);

		return bMap;
	}
	
	public Map<String, Object> getBlogDataInCategory(String id, Integer cateNo) {
		System.out.println("BlogService > getBlogDataInCategory(String id, Integer cateNo)");

		Map<String, Object> bMap = new HashMap<String, Object>();
		Map<String, Object> cateMap = new HashMap<String, Object>();
		BlogVo Post;
		cateMap.put("id", id);
		cateMap.put("cateNo", cateNo);
		
		Post = bDao.getLatelyPostingInCategory(cateMap);
		

		BlogVo blogOneData = bDao.BlogOneData(id);
		
		List<CategoryVo> cateList = bDao.getBlogCateList(id);
		List<BlogVo> postList = bDao.getBlogPostListInCategory(cateMap);

		System.out.println("blogOneData" + blogOneData);
		System.out.println("latelyPosting" + Post);
		System.out.println("cateList" + cateList);
		System.out.println("postList" + postList);

		bMap.put("blogOneData", blogOneData);
		bMap.put("posting", Post);
		bMap.put("cateList", cateList);
		bMap.put("postList", postList);

		return bMap;
	}
	
	public Map<String, Object> getBlogDataInCategory(String id, Integer cateNo, Integer postNo) {
		System.out.println("BlogService > getBlogData(String id, Integer cateNo, Integer postNo)");

		Map<String, Object> bMap = new HashMap<String, Object>();
		Map<String, Object> cateMap = new HashMap<String, Object>();
		BlogVo Post;
		cateMap.put("id", id);
		cateMap.put("cateNo", cateNo);
		
		cateMap.put("postNo", postNo);
		Post = bDao.getReadPosting(cateMap);

		BlogVo blogOneData = bDao.BlogOneData(id);
		
		List<CategoryVo> cateList = bDao.getBlogCateList(id);
		List<BlogVo> postList = bDao.getBlogPostListInCategory(cateMap);

		System.out.println("blogOneData" + blogOneData);
		System.out.println("latelyPosting" + Post);
		System.out.println("cateList" + cateList);
		System.out.println("postList" + postList);

		bMap.put("blogOneData", blogOneData);
		bMap.put("posting", Post);
		bMap.put("cateList", cateList);
		bMap.put("postList", postList);

		return bMap;
	}

	// 블로그 관리자 메뉴에 필요한 정보들 받아오기
	public Map<String, Object> getBlogAdminData(String id) {
		System.out.println("BlogService > getBlogData()");
		Map<String, Object> idInfo = new HashMap<String, Object>();
		idInfo.put("id", id);
		BlogVo bVo = bDao.getBlogAdminData(idInfo);

		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("blogOneData", bVo);

		return bMap;
	}

	// 블로그 기본 정보 업데이트
	// 자 일단 BlogVo에는 jsp로 도달한 타이틀, id, path가 들어가 있다.
	// 기존과 같을수도 있고 다를수도 있지만 blogVo를 넣어주면 같으면 같은대로 다르면 다른대로 업데이트가 될것이다.
	// id든 뭐든 들어가있을거니까 null을 생각하지 말자.
	// file null일때 조건주려고 했더니만 file에는 기본적으로 뭐가 들어가있네..
	public String updateBasicBlog(MultipartFile file, BlogVo blogVo) {
		System.out.println("BlogService > updateBasicBlog()");
		System.out.println("file : " + file);
		System.out.println("blogVo : " + blogVo);

		int count;
		String saveName;
		
		if (file.isEmpty()) {
			saveName = bDao.loadLogoFile(blogVo.getId());
			blogVo.setLogoFile(saveName);

			count = bDao.updateBasicBlog(blogVo);
		} else {
			// 블로그 로고 업데이트
			System.out.println("BlogService > updateBasicBlog()");
			String saveDir = "C:\\javaStudy\\upload";

			// (1) 파일 정보(DB저장) 추출 저장

			// 오리지날 파일명, 저장경로 + 파일(랜덤)명, 파일 사이즈
			String orgName = file.getOriginalFilename();

			// 확장자
			String exName = orgName.substring(orgName.lastIndexOf("."));

			// 저장 파일명
			saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

			// 파일 경로(디렉토리 + 저장파일명)
			String filePath = saveDir + "\\" + saveName;

			// DB 저장 --> 과제
			blogVo.setLogoFile(saveName);

			count = bDao.updateBasicBlog(blogVo);
			System.out.println("fileService > insertFile()" + blogVo.getLogoFile());
			System.out.println(count + " 저장되었습니다. ");

			// (2) 파일 저장
			try {
				byte[] fileData = file.getBytes();

				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);

				bos.write(fileData);
				bos.close();

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		System.out.println("updateBasicBlog 종료");
		System.out.println(count + "건 수정 완료");

		return saveName;
	}

	
	public Map<String, Object> getWriteFormData(String id) {
		System.out.println("BlogService > getWriteFormData()");
		Map<String, Object> idInfo = new HashMap<String, Object>();
		idInfo.put("id", id);
		BlogVo bVo = bDao.getBlogAdminData(idInfo);
		List<CategoryVo> cList = cDao.getCategoryName(id);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("blogOneData", bVo);
		bMap.put("cList", cList);

		return bMap;
	}
	
	public int insertPosting(PostVo postVo) {
		System.out.println("BlogService > insertPosting");
		int count = pDao.insertPosting(postVo);
		return count;
	}

}
