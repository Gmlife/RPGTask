package pojo;

public class FollowRelation {
    private int followFromId;
    private int followToId;

    public FollowRelation(int followFromId, int followToId) {
        this.followFromId = followFromId;
        this.followToId = followToId;
    }

    public int getFollowFromId() {
        return followFromId;
    }

    public void setFollowFromId(int followFromId) {
        this.followFromId = followFromId;
    }

    public int getFollowToId() {
        return followToId;
    }

    public void setFollowToId(int followToId) {
        this.followToId = followToId;
    }
}
