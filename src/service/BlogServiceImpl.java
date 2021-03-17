package service;

import pojo.Blog;

import java.util.List;

public interface BlogServiceImpl {
    List<Blog> getAllBlog();

    Blog getBlog(int blogId);

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(int blogId);
}
