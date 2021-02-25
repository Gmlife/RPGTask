package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Message;
@Repository
public interface MessageDao {
    Message getMessage(@Param("commitId") int commitId);
    int addMessage(Message commit);
    int updateMessage(Message commit);
    int deleteMessage(@Param("commitId") int commitId);
}
