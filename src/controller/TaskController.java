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
import java.util.List;

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

    @PostMapping(value = "/task/add.action")
    @ResponseBody
    public String addTask(@RequestParam("taskTitle") String taskTitle, @RequestParam("taskIcon") int taskIcon,
                          @RequestParam("taskDesc") String taskDesc, @RequestParam("awardCoin") int awardCoin,
                          @RequestParam("p_uid") int p_uid) {
        Task task = new Task(0, taskTitle, taskIcon, taskDesc, awardCoin, 0, p_uid, 0, false);
        int tid = taskService.addTask(task);
        UserTask userTask = userService.getUserTask(p_uid);
        List<Integer> tasks = userTask.getPublishTaskIdList();
        tasks.add(tid);
        userTask.setPublishTaskId(tasks.toString());
        userService.updateUserTask(userTask);
        return "redirect:task.action";
    }

    @PostMapping("/task/delete.action")
    @ResponseBody
    public String deleteTask(@RequestParam("tid") String tid) {
        int taskId = Integer.parseInt(tid);
        Task task = taskService.getTask(taskId);
        if (task == null)
            return "fail";
        //有人曾接受过该任务，需要发送任务已被删除邮件，同时根据完成状态，去除接收者的任务信息中的id索引。
        if (task.getReceiveUserId() != 0) {
            int r_uid = task.getReceiveUserId();
            Message message = new Message(0, task.getPublishUserId(), "任务" + task.getTaskTitle() + "已经被发布者删除", task.getReceiveUserId());
            int message_id = messageService.addMessage(message);
            UserMessage box = userService.getUserMessage(r_uid);
            List<Integer> mails = box.getMessageIdList();
            mails.add(message_id);
            box.setMessageId(mails.toString());
            userService.updateUserMessage(box);
            if (task.getTaskState() == 1) //accept
            {
                UserTask userTask = userService.getUserTask(r_uid);
                List<Integer> tasks = userTask.getDoingTaskIdList();
                tasks.removeIf(n -> (n == taskId));
                userTask.setDoingTaskId(tasks.toString());
                userService.updateUserTask(userTask);
            } else if (task.getTaskState() == 2) //complete
            {
                UserTask userTask = userService.getUserTask(r_uid);
                List<Integer> tasks = userTask.getFinishTaskIdList();
                tasks.removeIf(n -> (n == taskId));
                userTask.setFinishTaskId(tasks.toString());
                userService.updateUserTask(userTask);
            }
        }
        //删除接收者相关索引后，删除作者任务索引信息
        int p_uid = task.getPublishUserId();
        UserTask userTask = userService.getUserTask(p_uid);
        List<Integer> tasks = userTask.getPublishTaskIdList();
        tasks.removeIf(n -> (n == taskId));
        userTask.setPublishTaskId(tasks.toString());
        userService.updateUserTask(userTask);
        //最后删除该任务信息
        taskService.deleteTask(taskId);
        return "ok";
    }


    @PostMapping("/task/receive.action")
    @ResponseBody
    public String receiveTask(@RequestParam("r_uid") String r_uid, @RequestParam("tid") String tid) {
        int taskId = Integer.parseInt(tid);
        int receiver = Integer.parseInt(r_uid);
        Task task = taskService.getTask(taskId);
        if (task.getPublishUserId() == receiver || !task.isVerifyState())
            return "fail";
        if (task.getTaskState() == 0) {
            task.setTaskState(1);
            task.setReceiveUserId(receiver);
            taskService.updateTask(task);
            UserTask userTask = userService.getUserTask(receiver);
            List<Integer> tasks = userTask.getDoingTaskIdList();
            tasks.add(taskId);
            userTask.setDoingTaskId(tasks.toString());
            userService.updateUserTask(userTask);
            return "ok";
        }
        return "fail";
    }

    @PostMapping("/task/complete.action")
    @ResponseBody
    public String completeTask(@RequestParam("tid") String tid) {
        int taskId = Integer.parseInt(tid);
        Task task = taskService.getTask(taskId);
        if (task == null)
            return "fail";
        if (task.getTaskState() == 0) {
            task.setTaskState(2);
            userService.addCoin(task.getPublishUserId(), task.getAwardCoin());
        } else if (task.getTaskState() == 1) {
            task.setTaskState(2);
            userService.addCoin(task.getReceiveUserId(), task.getAwardCoin());
            UserTask userTask = userService.getUserTask(task.getReceiveUserId());
            List<Integer> tasks = userTask.getDoingTaskIdList();
            tasks.removeIf(n -> (n == taskId));
            userTask.setDoingTaskId(tasks.toString());
            tasks = userTask.getFinishTaskIdList();
            tasks.add(taskId);
            userTask.setFinishTaskId(tasks.toString());
            userService.updateUserTask(userTask);
        } else {
            return "fail";
        }
        return "ok";
    }
}

