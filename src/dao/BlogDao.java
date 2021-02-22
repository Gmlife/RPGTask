package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Blog;
@Repository
public interface BlogDao {
    Blog getBlog(@Param("blogId") int blogId);
    int addBlog(Blog blog);
    int updateBlog(Blog blog);
    int deleteBlog(@Param("blogId") int blogId);
}
