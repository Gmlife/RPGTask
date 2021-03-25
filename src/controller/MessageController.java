package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.*;
import service.MessageServiceImpl;
import service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/mail.action", method = RequestMethod.GET)
    public String toMail(Model model, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        UserDesc desc;
        desc = userService.getUserDesc(user.getUid());
        addUserSignInfo(user.getUid(), model);
        List<Message> messages = new ArrayList<>();
        Map<Integer, UserDesc> map = new HashMap<>();
        for (Integer id : userService.getUserMessage(user.getUid()).getMessageIdList()) {
            Message message = messageService.getMessage(id);
            messages.add(message);
            map.put(id, userService.getUserDesc(message.getMessageUserId()));
        }

        model.addAttribute("mid2user", map);
        model.addAttribute("my_desc", desc);
        model.addAttribute("message_list", messages);
        model.addAttribute("cur_page", "message");
        return "message";
    }

    @PostMapping("/mail/delete.action")
    @ResponseBody
    public String deleteMessage(@RequestParam("mid") int mid) {
        Message message = messageService.getMessage(mid);
        UserMessage userMessage = userService.getUserMessage(message.getMessageTargetId());
        List<Integer> mails = userMessage.getMessageIdList();
        mails.removeIf(n -> (n == mid));
        userMessage.setMessageId(mails.toString());
        userService.updateUserMessage(userMessage);
        int row = messageService.deleteMessage(mid);
        if (row > 0)
            return "ok";
        else
            return "fail";
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

    @PostMapping("/mail/send.action")
    @ResponseBody
    public String sendMessage(@RequestParam("m_target") String target, @RequestParam("m_text") String text,
                              @RequestParam("s_uid") int s_uid) {
        User user;
        if (target.matches("^[0-9]*$")) {
            int t_uid = Integer.parseInt(target);
            user = userService.getUserByUid(t_uid);
            if (user == null)
                user = userService.getUser(target);
        } else {
            user = userService.getUser(target);
        }
        if (user == null) {
            return "no user";
        }
        Message message = new Message(0, s_uid, text, user.getUid());
        messageService.addMessage(message);
        UserMessage userMessage = userService.getUserMessage(user.getUid());
        List<Integer> mails = userMessage.getMessageIdList();
        mails.add(message.getMessageId());
        userMessage.setMessageId(mails.toString());
        int row = userService.updateUserMessage(userMessage);
        if (row > 0)
            return "ok";
        else
            return "fail";
    }
}