package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.*;

@Service("userService")
@Transactional
public class UserService implements UserServiceImpl {
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
    public int addUserBlog(UserBlog blog) {
        return userDao.addUserBlog(blog);
    }

    @Override
    public int updateUserBlog(UserBlog blog) {
        return userDao.updateUserBlog(blog);
    }

    @Override
    public UserMessage getUserMessage(int uid) {
        return userDao.getUserMessage(uid);
    }

    @Override
    public int addUserMessage(UserMessage message) {
        return userDao.addUserMessage(message);
    }

    @Override
    public int updateUserMessage(UserMessage message) {
        return userDao.updateUserMessage(message);
    }

    @Override
    public UserTask getUserTask(int uid) {
        return userDao.getUserTask(uid);
    }

    @Override
    public int addUserTask(UserTask task) {
        return userDao.addUserTask(task);
    }

    @Override
    public int updateUserTask(UserTask task) {
        return userDao.updateUserTask(task);
    }

    @Override
    public UserFollow getUserFollow(int uid) {
        return userDao.getUserFollow(uid);
    }

    @Override
    public int addUserFollow(UserFollow follow) {
        return userDao.addUserFollow(follow);
    }

    @Override
    public int updateUserFollow(UserFollow follow) {
        return userDao.updateUserFollow(follow);
    }


}