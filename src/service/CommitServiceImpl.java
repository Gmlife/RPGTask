package service;

import org.apache.ibatis.annotations.Param;
import pojo.Commit;

public interface CommitServiceImpl {
    Commit getCommit(@Param("commitId") int commitId);

    int addCommit(Commit commit);

    int updateCommit(Commit commit);

    int deleteCommit(@Param("commitId") int commitId);
}
