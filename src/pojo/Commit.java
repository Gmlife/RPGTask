package pojo;

public class Commit {
    private int commitId;
    private int commitUserId;
    private String commitText;
    private String commitDate;

    public Commit(int commitId, int commitUserId, String commitText, String commitDate) {
        this.commitId = commitId;
        this.commitUserId = commitUserId;
        this.commitText = commitText;
        this.commitDate = commitDate;
    }

    public int getCommitId() {
        return commitId;
    }

    public void setCommitId(int commitId) {
        this.commitId = commitId;
    }

    public int getCommitUserId() {
        return commitUserId;
    }

    public void setCommitUserId(int commitUserId) {
        this.commitUserId = commitUserId;
    }

    public String getCommitText() {
        return commitText;
    }

    public void setCommitText(String commitText) {
        this.commitText = commitText;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }
}
