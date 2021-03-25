package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.Blog;
import pojo.Commit;
import pojo.User;
import pojo.UserDesc;
import service.BlogServiceImpl;
import service.CommitServiceImpl;
import service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private CommitServiceImpl commitService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/blog.action", method = RequestMethod.GET)
    public String toBlog(Model model, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserDesc desc;
        desc = userService.getUserDesc(user.getUid());
        List<Blog> blogs;
        blogs = blogService.getAllBlog();
        model.addAttribute("my_desc", desc);
        model.addAttribute("blog_list", blogs);
        return "blog_edit";
    }

    @RequestMapping(value = "/{blogId}/view.action", method = RequestMethod.GET)
    public String viewBlog(@PathVariable int blogId, Model model) {
        Blog blog = blogService.getBlog(blogId);
        List<Commit> commits = new ArrayList<>();
        for (int id : blog.getBlogCommitIdList()) {
            commits.add(commitService.getCommit(id));
        }
        model.addAttribute("blog", blog);
        model.addAttribute("commits", commits);
        return "blog_info";
    }
}

