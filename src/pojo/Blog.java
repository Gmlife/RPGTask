package pojo;

import util.CodeListUtil;

import java.util.List;

public class Blog {
    private int blogId;
    private String blogTitle;
    private String blogText;
    private String blogCommit;
    private boolean verifyState;

    public Blog(int blogId, String blogTitle, String blogText, String blogCommit, boolean verifyState) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogText = blogText;
        this.blogCommit = blogCommit;
        this.verifyState = verifyState;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public String getBlogCommit() {
        return blogCommit;
    }

    public void setBlogCommit(String blogCommit) {
        this.blogCommit = blogCommit;
    }

    public boolean isVerifyState() {
        return verifyState;
    }

    public void setVerifyState(boolean verifyState) {
        this.verifyState = verifyState;
    }

    public List<Integer> getBlogCommitIdList() {
        return CodeListUtil.codeToList(blogCommit);
    }
}
