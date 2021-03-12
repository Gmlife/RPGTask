package service;

import org.apache.ibatis.annotations.Param;
import pojo.Blog;

public interface BlogServiceImpl {
    Blog getBlog(@Param("blogId") int blogId);

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(@Param("blogId") int blogId);
}
