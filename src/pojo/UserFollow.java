package pojo;

import util.CodeListUtil;

import java.util.List;

public class UserFollow {
    private int uid;
    private String followId;
    private List<String> followIdList;

    public UserFollow(int uid, String followId) {
        this.uid = uid;
        this.followId = followId;
        this.followIdList= CodeListUtil.codeToList(followId);
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

    public List<String> getFollowIdList() {
        return followIdList;
    }

    public void setFollowIdList(List<String> followIdList) {
        this.followIdList = followIdList;
    }
}
