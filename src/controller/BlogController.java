package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.*;
import service.BlogServiceImpl;
import service.CommitServiceImpl;
import service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
        fillUserInfo(model, session);
        List<Blog> blogs;
        blogs = blogService.getAllBlog();
        Map<Integer, List<Commit>> blogCommits = new HashMap<>();
        for (Blog b : blogs) {
            List<Integer> commits = b.getBlogCommitIdList();
            List<Commit> commitList = new ArrayList<>();
            for (int id : commits) {
                commitList.add(commitService.getCommit(id));
            }
            blogCommits.put(b.getBlogId(), commitList);
        }
        model.addAttribute("blog_list", blogs);
        model.addAttribute("cur_page", "blog");
        model.addAttribute("commit_map",blogCommits);
        return "blog";
    }

    public void fillUserInfo(Model model, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserDesc desc;
        desc = userService.getUserDesc(user.getUid());
        UserTask u_task = userService.getUserTask(user.getUid());
        boolean can_sign = true;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (u_task.getLastSignedTime() != null) {
            if (u_task.getLastSignedTime().equals(df.format(new Date()))) {
                can_sign = false;
            }
        }
        model.addAttribute("u_task", u_task);
        model.addAttribute("my_desc", desc);
        model.addAttribute("can_sign", can_sign);
    }
}

