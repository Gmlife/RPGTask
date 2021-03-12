package service;

import org.apache.ibatis.annotations.Param;
import pojo.Message;

public interface MessageServiceImpl {
    Message getMessage(@Param("messageId") int messageId);

    int addMessage(Message message);

    int updateMessage(Message message);

    int deleteMessage(@Param("messageId") int messageId);
}
