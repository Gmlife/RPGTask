package service;

import pojo.*;

public interface UserServiceImpl {
    User tryLogin(String userName, String userPass);

    // User
    User getUser(String userName);

    User getUserByUid(int uid);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserByUid(int uid);

    int deleteUser(String userName);

    // UserDesc
    UserDesc getUserDesc(int uid);

    int addUserDesc(UserDesc desc);

    int updateUserDesc(UserDesc desc);

    int addCoin(int uid, int coin);

    int addExp(int uid, int exp);

    //UserBlog
    UserBlog getUserBlog(int uid);

    int addUserBlog(UserBlog desc);

    int updateUserBlog(UserBlog desc);

    // UserMessage
    UserMessage getUserMessage(int uid);

    int addUserMessage(UserMessage desc);

    int updateUserMessage(UserMessage desc);

    UserTask getUserTask(int uid);

    int addUserTask(UserTask desc);

    int updateUserTask(UserTask desc);

    // UserFollow
    UserFollow getUserFollow(int uid);

    int addUserFollow(UserFollow desc);

    int updateUserFollow(UserFollow desc);
}
