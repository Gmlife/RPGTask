package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.TaskService;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/task.action", method = RequestMethod.GET)
    public String toTask() {
        return "task";
    }

}

