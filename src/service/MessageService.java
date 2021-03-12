package service;

import dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Message;

@Service("messageService")
@Transactional
public class MessageService implements MessageServiceImpl {
    @Autowired
    MessageDao messageDao;

    @Override
    public Message getMessage(int messageId) {
        return messageDao.getMessage(messageId);
    }

    @Override
    public int addMessage(Message message) {
        return messageDao.addMessage(message);
    }

    @Override
    public int updateMessage(Message message) {
        return messageDao.updateMessage(message);
    }

    @Override
    public int deleteMessage(int messageId) {
        return messageDao.deleteMessage(messageId);
    }
}
