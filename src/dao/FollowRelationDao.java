package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.FollowRelation;
@Repository
public interface FollowRelationDao {
    FollowRelation getFollowFromRelation(@Param("followFromId") int followFromId);
    int getFollowToRelation(@Param("followToId") int followToId);
    int addFollowRelation(FollowRelation commit);
    int deleteFollowRelation(FollowRelation commit);
}
