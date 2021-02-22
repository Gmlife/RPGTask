package service;

import pojo.User;
import pojo.UserDesc;

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
    UserDesc getUserDesc(String username);

    UserDesc getUserDescByUid(int uid);

    int addUserDesc(UserDesc desc);

    int updateUserDesc(UserDesc desc);

    int addCoin(int uid, int coin);

    int addExp(int uid, int exp);
}
