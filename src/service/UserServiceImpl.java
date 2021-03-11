package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User tryLogin(String username, String password) {
        return userDao.tryLogin(username, password);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUserByUid(int uid) {
        return userDao.deleteUserByUid(uid);
    }

    @Override
    public int deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    @Override
    public UserDesc getUserDesc(int uid) {
        return userDao.getUserDesc(uid);
    }

    @Override
    public int addUserDesc(UserDesc desc) {
        return userDao.addUserDesc(desc);
    }

    @Override
    public int updateUserDesc(UserDesc desc) {
        return userDao.updateUserDesc(desc);
    }

    @Override
    public int addCoin(int uid, int coin) {
        return userDao.addCoin(uid, coin);
    }

    @Override
    public int addExp(int uid, int exp) {
        return userDao.addExp(uid, exp);
    }

    @Override
    public UserBlog getUserBlog(int uid) {
        return userDao.getUserBlog(uid);
    }

    @Override
    public int addUserBlog(UserBlog desc) {
        return userDao.addUserBlog(desc);
    }

    @Override
    public int updateUserBlog(UserBlog desc) {
        return userDao.updateUserBlog(desc);
    }

    @Override
    public UserMessage getUserMessage(int uid) {
        return userDao.getUserMessage(uid);
    }

    @Override
    public int addUserMessage(UserMessage desc) {
        return userDao.addUserMessage(desc);
    }

    @Override
    public int updateUserMessage(UserMessage desc) {
        return userDao.updateUserMessage(desc);
    }

    @Override
    public UserTask getUserTask(int uid) {
        return userDao.getUserTask(uid);
    }

    @Override
    public int addUserTask(UserTask desc) {
        return userDao.addUserTask(desc);
    }

    @Override
    public int updateUserTask(UserTask desc) {
        return userDao.updateUserTask(desc);
    }
}