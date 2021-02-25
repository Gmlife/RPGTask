package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Commit;
@Repository
public interface CommitDao {
    Commit getCommit(@Param("commitId") int commitId);
    int addCommit(Commit commit);
    int updateCommit(Commit commit);
    int deleteCommit(@Param("commitId") int commitId);
}
