package com.javaex.vo;

public class BlogVo {
	private String id;
	private String blogTitle;
	private String logoFile;
	
	// users table
	private String userName;
	
	// category table
	private int cateNo;
	private String cateName;
	
	// post table
	private int postNo;
	private String postTitle;
	private String postRegDate;
	private String postContent;
	
	public BlogVo() {
	}

	public BlogVo(String id, String blogTitle, String logoFile) {
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}

	public BlogVo(String id, String blogTitle, String logoFile, String userName, String cateName, String postTitle,
			String postRegDate, String postContent) {
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.userName = userName;
		this.cateName = cateName;
		this.postTitle = postTitle;
		this.postRegDate = postRegDate;
		this.postContent = postContent;
	}

	
	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostRegDate() {
		return postRegDate;
	}

	public void setPostRegDate(String postRegDate) {
		this.postRegDate = postRegDate;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", userName=" + userName
				+ ", cateNo=" + cateNo + ", cateName=" + cateName + ", postNo=" + postNo + ", postTitle=" + postTitle
				+ ", postRegDate=" + postRegDate + ", postContent=" + postContent + "]";
	}

	
}
