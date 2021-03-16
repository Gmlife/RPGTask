package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.Task;
import pojo.User;
import pojo.UserDesc;
import service.TaskServiceImpl;
import service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/task.action", method = RequestMethod.GET)
    public String toTask(Model model, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserDesc desc;
        desc = userService.getUserDesc(user.getUid());
        List<Task> tasks;
        tasks=  taskService.getAllTask();
        model.addAttribute("desc", desc);
        model.addAttribute("task_list",tasks);
        return "task";
    }

}

