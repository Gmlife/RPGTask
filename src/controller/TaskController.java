package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.*;
import service.MessageServiceImpl;
import service.TaskServiceImpl;
import service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = "/task.action", method = RequestMethod.GET)
    public String toTask(Model model, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserDesc desc;
        desc = userService.getUserDesc(user.getUid());
        List<Task> tasks;
        tasks = taskService.getAllTask();
        UserTask u_task = userService.getUserTask(user.getUid());
        boolean can_sign = true;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (u_task.getLastSignedTime() != null) {
            if (u_task.getLastSignedTime().equals(df.format(new Date()))) {
                can_sign = false;
            }
        }

        model.addAttribute("u_task", u_task);
        model.addAttribute("desc", desc);
        model.addAttribute("task_list", tasks);
        model.addAttribute("can_sign", can_sign);
        model.addAttribute("cur_page", "task");
        return "task";
    }

    @PostMapping("/delete_task.action")
    @ResponseBody
    public Map<String, String> deleteTask(@RequestParam("tid") String tid) {
        int taskId = Integer.parseInt(tid);
        Task task = taskService.getTask(taskId);
        if (task.getReceiveUserId() != 0) {
            Message message = new Message(0, task.getPublishUserId(), "任务" + task.getTaskTitle() + "已经被发布者删除", task.getReceiveUserId());
            int message_id = messageService.addMessage(message);
            UserMessage box = userService.getUserMessage(task.getReceiveUserId());
            List<Integer> mails = box.getMessageIdList();
            mails.add(message_id);
            box.setMessageId(mails.toString());
            userService.updateUserMessage(box);
        }
        Map<String, String> res = new HashMap<>();
        res.put("res", "success");
        return res;
    }
}

