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

    int addUserBlog(UserBlog blog);

    int updateUserBlog(UserBlog blog);

    // UserMessage
    UserMessage getUserMessage(int uid);

    int addUserMessage(UserMessage message);

    int updateUserMessage(UserMessage message);

    UserTask getUserTask(int uid);

    int addUserTask(UserTask task);

    int updateUserTask(UserTask task);

    // UserFollow
    UserFollow getUserFollow(int uid);

    int addUserFollow(UserFollow follow);

    int updateUserFollow(UserFollow follow);
}
