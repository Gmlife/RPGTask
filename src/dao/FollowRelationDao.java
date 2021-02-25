package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.FollowRelation;
@Repository
public interface FollowRelationDao {
    FollowRelation getFollowRelation(@Param("commitId") int commitId);
    int addFollowRelation(FollowRelation commit);
    int updateFollowRelation(FollowRelation commit);
    int deleteFollowRelation(@Param("commitId") int commitId);
}
