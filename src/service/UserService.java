package service;

import pojo.*;

public interface UserService {
    User tryLogin(String username, String password);

    // User
    User getUser(String username);

    User getUserByUid(int uid);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(String username);

    int deleteUserByUid(int uid);

    // UserDesc
    UserDesc getUserDesc(int uid);

    int addUserDesc(UserDesc desc);

    int updateUserDesc(UserDesc desc);

    int addCoin(int uid, int coin);

    int addExp(int uid, int exp);

    // UserBlog
    public UserBlog getUserBlog(int uid);

    public int addUserBlog(UserBlog desc);

    public int updateUserBlog(UserBlog desc);

    // UserMessage
    public UserMessage getUserMessage(int uid);

    public int addUserMessage(UserMessage desc);

    public int updateUserMessage(UserMessage desc);

    // UserTask
    public UserTask getUserTask(int uid);

    public int addUserTask(UserTask desc);

    public int updateUserTask(UserTask desc);
}
