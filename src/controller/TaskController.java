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
import java.util.Random;

@Controller
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = "/task.action", method = RequestMethod.GET)
    public String toTask(Model model, HttpSession session, @RequestParam("sort") String key) {
        fillUserInfo(model, session);
        List<Task> tasks;
        tasks = taskService.getAllTask(key);
        addSortWord(key, model);
        model.addAttribute("task_list", tasks);
        model.addAttribute("cur_page", "task");
        return "task";
    }

    private void addSortWord(String sort, Model model) {
        switch (sort) {
            case "date_desc" -> {
                model.addAttribute("sort", "发布日期");
                model.addAttribute("sd", "_desc");
            }
            case "award" -> model.addAttribute("sort", "任务奖励");
            case "award_desc" -> {
                model.addAttribute("sort", "任务奖励");
                model.addAttribute("sd", "_desc");
            }
            default -> model.addAttribute("sort", "发布日期");
        }
    }

    @RequestMapping(value = "/task/receive.action", method = RequestMethod.GET)
    public String toReceive(Model model, HttpSession session, @RequestParam("sort") String key) {
        fillUserInfo(model, session);
        List<Task> tasks;
        tasks = taskService.getAllTask(key);
        addSortWord(key, model);
        tasks.removeIf(n -> (n.getTaskState() != 0));
        model.addAttribute("task_list", tasks);
        model.addAttribute("cur_page", "receive");
        return "task";
    }

    @RequestMapping(value = "/judge.action", method = RequestMethod.GET)
    public String toJudge(Model model, HttpSession session) {
        fillUserInfo(model, session);
        List<Task> tasks;
        tasks = taskService.getAllTask("judge");
        model.addAttribute("task_list", tasks);
        model.addAttribute("cur_page", "judge");
        return "judge";
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

    @PostMapping("/task/add.action")
    @ResponseBody
    public String addTask(@RequestParam("task_title") String taskTitle, @RequestParam("task_desc") String taskDesc,
                          @RequestParam("task_award") int awardCoin, @RequestParam("p_uid") int p_uid) {
        if (taskTitle.isEmpty() || taskDesc.isEmpty())
            return "fail";
        UserDesc userDesc = userService.getUserDesc(p_uid);
        if (userDesc.getCoin() < awardCoin) {
            return "coin not enough";
        }
        Random random = new Random();
        int task_icon = Math.abs(random.nextInt() % 15);
        Task task = new Task(0, taskTitle, task_icon + 1, taskDesc, awardCoin, 0, p_uid, 0, false);
        taskService.addTask(task);
        int tid = task.getTaskId();
        UserTask userTask = userService.getUserTask(p_uid);
        List<Integer> tasks = userTask.getPublishTaskIdList();
        tasks.add(tid);
        userTask.setPublishTaskId(tasks.toString());
        userService.updateUserTask(userTask);
        userService.subCoin(p_uid, awardCoin);
        return "ok";
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
            if (task.getTaskState() == 1) //accept
            {
                sendMessage(task.getPublishUserId(), r_uid, "任务" + task.getTaskTitle() + "已经被发布者删除");
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
            taskService.updateTask(task);
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
            taskService.updateTask(task);
        } else {
            return "fail";
        }
        return "ok";
    }

    @PostMapping("/task/accept.action")
    @ResponseBody
    public String acceptTask(@RequestParam("a_uid") int a_uid, @RequestParam("tid") String tid) {
        if (isNotAdmin(a_uid)) {
            return "fail";
        }
        int taskId = Integer.parseInt(tid);
        Task task = taskService.getTask(taskId);
        if (task == null)
            return "fail";
        task.setVerifyState(true);
        int row = taskService.updateTask(task);
        if (row > 0)
            return "ok";
        else
            return "fail";
    }

    @PostMapping("/task/refuse.action")
    @ResponseBody
    public String refuseTask(@RequestParam("a_uid") int a_uid, @RequestParam("tid") String tid) {
        if (isNotAdmin(a_uid)) {
            return "fail";
        }
        int taskId = Integer.parseInt(tid);
        Task task = taskService.getTask(taskId);
        if (task == null)
            return "fail";
        sendMessage(a_uid, task.getPublishUserId(), "很遗憾，您发布的任务 《" + task.getTaskTitle() + "》 因不符合任务规范，未能通过审核！金币:"
                + task.getAwardCoin() + "已退还！");
        UserTask userTask = userService.getUserTask(task.getPublishUserId());
        List<Integer> tasks = userTask.getPublishTaskIdList();
        tasks.removeIf(n -> (n == taskId));
        userTask.setPublishTaskId(tasks.toString());
        userService.updateUserTask(userTask);
        userService.addCoin(task.getPublishUserId(), task.getAwardCoin());
        int row = taskService.deleteTask(taskId);
        if (row > 0)
            return "true";
        else
            return "fail";
    }

    @RequestMapping(value = "/task/search.action", method = RequestMethod.POST)
    public String taskSearch(@RequestParam("search_text") String key, Model model, HttpSession session) {
        fillUserInfo(model, session);
        List<Task> tasks;
        tasks = taskService.searchTask(key);
        addSortWord(key, model);
        model.addAttribute("task_list", tasks);
        model.addAttribute("cur_page", "task");
        return "task";
    }

    public boolean isNotAdmin(int uid) {
        User user = userService.getUserByUid(uid);
        return !user.getUserType();
    }

    public void sendMessage(int from_uid, int to_uid, String content) {
        Message message = new Message(0, from_uid, content, to_uid);
        messageService.addMessage(message);
        int message_id = message.getMessageId();
        UserMessage box = userService.getUserMessage(to_uid);
        List<Integer> mails = box.getMessageIdList();
        mails.add(message_id);
        box.setMessageId(mails.toString());
        userService.updateUserMessage(box);
    }

}

