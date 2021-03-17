package service;

import dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Blog;

import java.util.List;

@Service("blogService")
@Transactional
public class BlogService implements BlogServiceImpl {
    @Autowired
    BlogDao blogDao;

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public Blog getBlog(int blogId) {
        return blogDao.getBlog(blogId);
    }

    @Override
    public int addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(int blogId) {
        return blogDao.deleteBlog(blogId);
    }
}
