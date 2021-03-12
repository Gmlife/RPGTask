package service;

import dao.CommitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Commit;

@Service("commitService")
@Transactional
public class CommitService implements CommitServiceImpl {
    @Autowired
    CommitDao commitDao;

    @Override
    public Commit getCommit(int commitId) {
        return commitDao.getCommit(commitId);
    }

    @Override
    public int addCommit(Commit commit) {
        return commitDao.addCommit(commit);
    }

    @Override
    public int updateCommit(Commit commit) {
        return commitDao.updateCommit(commit);
    }

    @Override
    public int deleteCommit(int commitId) {
        return commitDao.deleteCommit(commitId);
    }
}
