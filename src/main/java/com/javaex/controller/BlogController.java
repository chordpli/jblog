package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService bService;
	@Autowired
	private CategoryService cService;
	
	///////////////////////////////// 블로그 메인 /////////////////////////////////
	// 블로그 메인
		@RequestMapping(value = {"/{id}", "/{id}/{cateNo}/{postNo}", "/{id}/{cateNo}"}, method = {RequestMethod.GET, RequestMethod.POST})
		public String blog(@PathVariable("id") String id
							,@PathVariable(value = "cateNo", required = false) Integer cateNo
							,@PathVariable(value = "postNo", required = false) Integer postNo
							, Model model) {
			System.out.println("BlogController > blog");
			Map <String, Object> bMap;
			
			if(cateNo == null && postNo == null) {
				System.out.println("BlogController > getBlogData");
				bMap = bService.getBlogData(id);
			}else if (postNo == null){
				System.out.println("BlogController > getBlogDataInCategory(NopostNo)");
				bMap = bService.getBlogDataInCategory(id, cateNo);
			}else {
				System.out.println("BlogController > getBlogDataInCategory");
				System.out.println("cateNo : "  + cateNo);
				bMap = bService.getBlogDataInCategory(id, cateNo, postNo);
			}
			
			System.out.println(bMap.get("cateList"));
			model.addAttribute("blog", bMap);
			
			return "blog/blog-main";
		}
	/*	
	// 블로그 메인
	@RequestMapping(value = {"/{id}", "/{id}/{cateNo}", "/{id}/{cateNo}/{postNo}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String blog(@PathVariable("id") String id
						,@PathVariable(value = "cateName", required = false) String cateName
						,@PathVariable(value = "cateNo", required = false) int cateNo
						,@PathVariable(value = "postNo", required = false) int postNo
						, Model model) {
		System.out.println("BlogController > blog");
		Map <String, Object> bMap;
		
		if(cateNo == 0) {
			
		}
		bMap = bService.getBlogData(id, cateName);
		
		System.out.println(bMap.get("cateList"));
		model.addAttribute("blog", bMap);
		
		return "blog/blog-main";
	}
	
	// 블로그 메인 카테고리 선택
	
	@RequestMapping(value = "/{id}/{cateNo}/{postNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public String Read(@PathVariable("id") String id
						,@PathVariable(value = "cateNo", required = false) int cateNo
						,@PathVariable(value = "postNo", required = false) int postNo
						, Model model) {
		System.out.println("BlogController > blog > Read");
		Map <String, Object> bMap;
		
		bMap = bService.getBlogDataInCategory(id, cateNo, postNo);
		
		System.out.println(bMap.get("cateList"));
		model.addAttribute("blog", bMap);
		
		return "blog/blog-main";
	}
	
	*/
	
	
	///////////////////////////////// 블로그 메인 /////////////////////////////////
	
	///////////////////////////////// 블로그 관리 /////////////////////////////////
	// 블로그 관리 - 기본설정
	@GetMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id
								, HttpSession session
								, Model model) {
		System.out.println("BlogController > adminBasic");
		
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		if(user == null) {
			System.out.println("로그인이 되어있지 않습니다..");
			return "redirect:../../" + id ;
		}else {
			if(user.getId().equals(id)) { // 어드민 페이지 접속 성공
				System.out.println("로그인 아이디 : " + user.getId());
				System.out.println("현재 아이디 : " + id);
				
				Map <String, Object> bData = bService.getBlogAdminData(user.getId());
				model.addAttribute("blog", bData);
				return "blog/admin/blog-admin-basic";
			}else {
				System.out.println("해당 블로그의 주인이 아닙니다.");
				return "redirect:../../" + id ;
			}
		}
	}
	
	// 블로그 관리 - 카테고리
	@RequestMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id
								, HttpSession session
								, Model model) {
		System.out.println("BlogController > adminCategory");
		
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		if(user == null) {
			System.out.println("로그인이 되어있지 않습니다..");
			return "redirect:../../" + id ;
		}else {
			if(user.getId().equals(id)) { // 어드민 페이지 접속 성공
				Map <String, Object> bData = bService.getBlogAdminData(user.getId());
				model.addAttribute("blog", bData);
				
				return "blog/admin/blog-admin-cate";
			}else {
				System.out.println("해당 블로그의 주인이 아닙니다.");
				return "redirect:../../" + id ;
			}
		}
	}
	
	// 카테고리 리스트 불러오기
	@ResponseBody
	@RequestMapping("/{id}/admin/category/list")
	public List<CategoryVo> categoryList(HttpSession session, Model model) {
		System.out.println("BlogController > adminCategory > List");
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		// 리스트 불러오기
		List<CategoryVo> cList = cService.getCategoryList(user.getId());
		model.addAttribute("cList", cList);
		return cList;
	}
	
	// 카테고리 생성
	@ResponseBody
	@RequestMapping("/{id}/admin/category/insertCategory")
	public String newCategory(@RequestBody CategoryVo categoryVo, HttpSession session ) {
		System.out.println("BlogController > adminCategory > newCategory");
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		categoryVo.setId(user.getId());
				
		String state = cService.insertNewCategory(categoryVo);
		
		return state;
	}
	
	// 카테고리 삭제
	@ResponseBody
	@RequestMapping("/{id}/admin/category/deleteCategory")
	public String deleteCategory(@RequestBody String cateNo, HttpSession session ) {
		System.out.println("BlogController > adminCategory > deleteCategory");
		
		String state = cService.deleteCateogry(cateNo);
		
		return state;
	}
	
	
	// 블로그 관리 - 글작성 폼
	@GetMapping("/{id}/admin/writeform")
	public String adminWriteForm(@PathVariable("id") String id
								, HttpSession session
								, Model model) {
		System.out.println("BlogController > adminCategory");
		
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		if(user == null) {
			System.out.println("로그인이 되어있지 않습니다..");
			return "redirect:../../" + id ;
		}else {
			if(user.getId().equals(id)) { // 어드민 페이지 접속 성공
				Map <String, Object> bData = bService.getWriteFormData(user.getId());
				System.out.println(bData.get("cList"));
				model.addAttribute("blog", bData);
				return "blog/admin/blog-admin-write";
			}else {
				System.out.println("해당 블로그의 주인이 아닙니다.");
				return "redirect:../../" + id ;
			}
		}
	}
	
	// 블로그 관리 - 글 업로드
	@PostMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id
						, @ModelAttribute PostVo postVo
						, HttpSession session) {
		System.out.println("BlogController > adminCategory");
		
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		if(user == null) {
			System.out.println("로그인이 되어있지 않습니다..");
			return "redirect:../../" + id ;
		}else {
			if(user.getId().equals(id)) { // 어드민 페이지 접속 성공
				bService.insertPosting(postVo);
				return "redirect:./writeform";
			}else {
				System.out.println("해당 블로그의 주인이 아닙니다.");
				return "redirect:../../" + id ;
			}
		}
	}
	
	// 블로그 업데이트
	@RequestMapping(value = "/{id}/admin/basic/update/", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateBlogTitle(@ModelAttribute BlogVo blogVo
									, @RequestParam("file") MultipartFile file
									, HttpSession session
									, Model model) {
		System.out.println("BlogController > updateBlogTitle");
		UsersVo user = (UsersVo) session.getAttribute("authUser");
		
		blogVo.setId(user.getId());
		
		bService.updateBasicBlog(file, blogVo);
		System.out.println("모든것을 처리하였습니다.");
		
		return "redirect:../";
	}
	
///////////////////////////////// 블로그 관리 /////////////////////////////////
	
}
