package pojo;

import util.CodeListUtil;

import java.util.List;

public class UserFollow {
    private int uid;
    private String followId;

    public UserFollow(int uid, String followId) {
        this.uid = uid;
        this.followId = followId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public List<Integer> getFollowIdList() {
        return CodeListUtil.codeToList(followId);
    }
}
