package pojo;

import util.CodeListUtil;

import java.util.List;

public class UserBlog {
    private int uid;
    private String blogId;

    public UserBlog(int uid, String blogId) {
        this.uid = uid;
        this.blogId = blogId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public List<Integer> getBlogIdList() {
        return CodeListUtil.codeToList(blogId);
    }

}
