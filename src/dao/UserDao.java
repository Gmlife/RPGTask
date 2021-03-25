package dao;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.*;

@Repository
public interface UserDao {
    User tryLogin(@Param("userName") String userName, @Param("userPass") String userPass);

    // User
    User getUser(@Param("userName") String userName);

    User getUserByUid(@Param("uid") int uid);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserByUid(@Param("uid") int uid);

    int deleteUser(@Param("userName") String userName);

    int setUserBan(@Param("isBan") boolean isBan);

    // UserDesc
    UserDesc getUserDesc(int uid);

    int addUserDesc(UserDesc desc);

    int updateUserDesc(UserDesc desc);

    int addCoin(@Param("uid") int uid, @Param("coin") int coin);

    int subCoin(@Param("uid") int uid, @Param("coin") int coin);

    int addExp(@Param("uid") int uid, @Param("exp") int exp);

    UserBlog getUserBlog(@Param("uid") int uid);

    int addUserBlog(UserBlog blog);

    int updateUserBlog(UserBlog blog);

    UserMessage getUserMessage(@Param("uid") int uid);

    int addUserMessage(UserMessage message);

    int updateUserMessage(UserMessage message);

    UserTask getUserTask(@Param("uid") int uid);

    int addUserTask(UserTask task);

    int updateUserTask(UserTask task);

    UserFollow getUserFollow(@Param("uid") int uid);

    int addUserFollow(UserFollow follow);

    int updateUserFollow(UserFollow follow);

}
