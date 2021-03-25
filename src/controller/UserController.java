package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.*;
import service.BlogServiceImpl;
import service.TaskServiceImpl;
import service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private TaskServiceImpl taskService;

    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login(String username, String password, Model model, HttpSession session) {
        // 通过账号和密码查询用户
        User user = userService.tryLogin(username, password);
        if (user != null) {
            // 将用户对象添加到Session
            session.setAttribute("USER_SESSION", user);
            // 跳转到主页面
            return "redirect:task.action?sort=Date";
        }
        model.addAttribute("msg", "账号或密码错误，请重新输入！");
        // 返回到登录页面
        return "login";
    }

    @RequestMapping(value = "/logout.action")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:login.action";
    }

    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/register.action", method = RequestMethod.GET)
    public String toRegister(Model model) {
        addIconList(model);
        model.addAttribute("icon_num", 0);
        model.addAttribute("sex", false);
        return "register";
    }

    @RequestMapping(value = "/register.action", method = RequestMethod.POST)
    public String register(String username, String password, String nickname, boolean sex, int iconId, Model model) {
        User user = userService.getUser(username);
        if (user != null) {
            addIconList(model);
            model.addAttribute("icon_num", iconId);
            model.addAttribute("sex", sex);
            model.addAttribute("msg", "该用户名已经被注册！");
            return "register";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User temp = new User(0, false, username, password, false);
        userService.addUser(temp);
        int uid = temp.getUid();
        userService.addUserDesc(new UserDesc(uid, nickname, sex, iconId + 1, "", "", 0, 0, df.format(new Date())));
        userService.addUserBlog(new UserBlog(uid, "[]"));
        userService.addUserMessage(new UserMessage(uid, "[]"));
        userService.addUserFollow(new UserFollow(uid, "[]"));
        userService.addUserTask(new UserTask(uid, null, 0, "[]", "[]", "[]"));
        model.addAttribute("msg", "注册成功！");
        model.addAttribute("username", username);
        return "login";
    }

    public void addIconList(Model model) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            l.add(i);
        }
        model.addAttribute("icon_list", l);
    }

    @RequestMapping(value = "/user.action", method = RequestMethod.GET)
    public String toUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("USER_SESSION");
        int uid = user.getUid();
        addUserAllInfo(uid, model, "my");
        addUserSignInfo(uid, model);
        addIconList(model);
        return "user_edit";
    }

    @PostMapping("/user/update.action")
    @ResponseBody
    public String saveUser(int uid, int iconId, String nickname, String signature) {
        UserDesc desc = userService.getUserDesc(uid);
        desc.setIcon(iconId);
        desc.setNickName(nickname);
        desc.setSignature(signature);
        int row = userService.updateUserDesc(desc);
        if (row > 0)
            return "ok";
        else
            return "fail";
    }

    @PostMapping("/signed.action")
    @ResponseBody
    public String signed(@RequestParam("uid") String uid) {
        int userId = Integer.parseInt(uid);
        UserTask task = userService.getUserTask(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (task.getLastSignedTime() == null || (!task.getLastSignedTime().equals(df.format(new Date())))) {
            task.setLastSignedTime(df.format(new Date()));
            task.setSignedDay(task.getSignedDay() + 1);
            userService.addCoin(userId, 100);
            userService.updateUserTask(task);
            return "ok";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/{uid}/main.action", method = RequestMethod.GET)
    public String viewOtherUser(@PathVariable("uid") int uid, HttpSession session, Model model) {
        User user = (User) session.getAttribute("USER_SESSION");
        int s_uid = user.getUid();
        if (s_uid == uid) {
            return "redirect:/user.action";
        }
        UserDesc desc = userService.getUserDesc(s_uid);
        User o_user = userService.getUserByUid(uid);
        UserFollow follow = userService.getUserFollow(s_uid);
        List<Integer> f_list = follow.getFollowIdList();
        if (f_list.contains(uid)) {
            model.addAttribute("is_follow", true);
        } else {
            model.addAttribute("is_follow", false);
        }
        model.addAttribute("other_user", o_user);
        model.addAttribute("my_desc", desc);
        addUserAllInfo(uid, model, "other");
        addUserSignInfo(s_uid, model);
        return "user";
    }

    @RequestMapping(value = "/follow.action", method = RequestMethod.GET)
    public String toFollow(HttpSession session, Model model) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserFollow follow = userService.getUserFollow(user.getUid());
        List<UserDesc> others = new ArrayList<>();
        for (int id : follow.getFollowIdList()) {
            others.add(userService.getUserDesc(id));
        }
        model.addAttribute("other_desc", others);
        addUserAllInfo(user.getUid(), model, "my");
        addUserSignInfo(user.getUid(), model);
        return "follow";
    }

    @PostMapping("/user/follow.action")
    @ResponseBody
    public String followUser(@RequestParam("uid") int uid, @RequestParam("f_uid") int f_uid) {
        UserFollow follow = userService.getUserFollow(uid);
        List<Integer> f_list = follow.getFollowIdList();
        if (f_list.contains(f_uid))
            return "is_follow";
        f_list.add(f_uid);
        follow.setFollowId(f_list.toString());
        int row = userService.updateUserFollow(follow);
        if (row > 0)
            return "ok";
        else
            return "fail";
    }

    @PostMapping("/user/unFollow.action")
    @ResponseBody
    public String unFollowUser(@RequestParam("uid") int uid, @RequestParam("f_uid") int f_uid) {
        UserFollow follow = userService.getUserFollow(uid);
        List<Integer> f_list = follow.getFollowIdList();
        if (!f_list.contains(f_uid))
            return "is_not_follow";
        f_list.removeIf(n -> (n == f_uid));
        follow.setFollowId(f_list.toString());
        int row = userService.updateUserFollow(follow);
        if (row > 0)
            return "ok";
        else
            return "fail";
    }

    public void addUserAllInfo(int uid, Model model, String per_fix) {
        UserDesc desc = userService.getUserDesc(uid);
        List<Blog> blogs = new ArrayList<>();
        for (int id : userService.getUserBlog(uid).getBlogIdList()) {
            blogs.add(blogService.getBlog(id));
        }
        List<Task> tasks = new ArrayList<>();
        for (int id : userService.getUserTask(uid).getDoingTaskIdList()) {
            tasks.add(taskService.getTask(id));
        }
        List<Task> p_tasks = new ArrayList<>();
        for (int id : userService.getUserTask(uid).getPublishTaskIdList()) {
            p_tasks.add(taskService.getTask(id));
        }

        model.addAttribute(per_fix + "_desc", desc);
        model.addAttribute(per_fix + "_blogs", blogs);
        model.addAttribute(per_fix + "_tasks", tasks);
        model.addAttribute(per_fix + "_p_tasks", p_tasks);
    }

    public void addUserSignInfo(int uid, Model model) {
        UserTask u_task = userService.getUserTask(uid);
        boolean can_sign = true;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (u_task.getLastSignedTime() != null) {
            if (u_task.getLastSignedTime().equals(df.format(new Date()))) {
                can_sign = false;
            }
        }
        model.addAttribute("u_task", u_task);
        model.addAttribute("can_sign", can_sign);
    }
}
