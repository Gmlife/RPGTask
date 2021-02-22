package dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.User;
import pojo.UserDesc;

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

    // UserDesc
    UserDesc getUserDesc(String userName);

    UserDesc getUserDescByUid(int uid);

    int addUserDesc(UserDesc desc);

    int updateUserDesc(UserDesc desc);

    int addCoin(@Param("uid") int uid, @Param("coin") int coin);

    int addExp(@Param("uid") int uid, @Param("exp") int exp);
}
