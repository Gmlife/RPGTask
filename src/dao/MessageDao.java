package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Message;
@Repository
public interface MessageDao {
    Message getMessage(@Param("messageId") int messageId);
    int addMessage(Message message);
    int updateMessage(Message message);
    int deleteMessage(@Param("messageId") int messageId);
}
