package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.User;
import pojo.UserDesc;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login(String username, String password, Model model, HttpSession session) {
        // 通过账号和密码查询用户
        User user = userService.tryLogin(username, password);
        if (user != null) {
            // 将用户对象添加到Session
            session.setAttribute("USER_SESSION", user);
            // 跳转到主页面
            return "redirect:task.action";
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
        model.addAttribute("icon_num",0);
        model.addAttribute("sex",false);
        return "register";
    }

    @RequestMapping(value = "/register.action", method = RequestMethod.POST)
    public String register(String username, String password, String nickname, boolean sex, int iconId, Model model) {
        User user = userService.getUser(username);
        if (user != null) {
            addIconList(model);
            model.addAttribute("icon_num",iconId);
            model.addAttribute("sex",sex);
            model.addAttribute("msg", "该用户名已经被注册！");
            return "register";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User new_user = new User(0, false, username, password);
        UserDesc new_desc = new UserDesc(0, nickname, sex, iconId, "", "", 0, 0, df.format(new Date()));
        userService.addUser(new_user);
        userService.addUserDesc(new_desc);
        model.addAttribute("msg", "注册成功！");
        model.addAttribute("username", username);
        return "login";
    }
    public void addIconList(Model model){
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            l.add(i);
        }
        model.addAttribute("icon_list", l);
    }
}

