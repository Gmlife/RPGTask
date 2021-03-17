package service;

import pojo.Commit;

public interface CommitServiceImpl {
    Commit getCommit(int commitId);

    int addCommit(Commit commit);

    int updateCommit(Commit commit);

    int deleteCommit(int commitId);
}
