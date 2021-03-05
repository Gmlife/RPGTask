package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.User;
import pojo.UserDesc;
import service.TaskService;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/task.action", method = RequestMethod.GET)
    public String toTask(Model model, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserDesc desc;
        desc = userService.getUserDescByUid(user.getUid());
        model.addAttribute("desc", desc);
        return "task";
    }

}

