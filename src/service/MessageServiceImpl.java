package service;

import pojo.Message;

public interface MessageServiceImpl {
    Message getMessage(int messageId);

    int addMessage(Message message);

    int updateMessage(Message message);

    int deleteMessage(int messageId);
}
