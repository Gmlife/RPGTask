package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;
import pojo.UserDesc;

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
    public UserDesc getUserDesc(String username) {
        return userDao.getUserDesc(username);
    }

    @Override
    public UserDesc getUserDescByUid(int uid) {
        return userDao.getUserDescByUid(uid);
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
}
