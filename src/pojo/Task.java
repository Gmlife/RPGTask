package pojo;

public class Task {
    private int taskId;
    private String taskTitle;
    private int taskIcon;
    private String taskDesc;
    private int awardCoin;
    private int taskState;
    //release = 0 , accept = 1, complete = 2
    private int publishUserId;
    private int receiveUserId;
    private boolean verifyState;

    public Task(int taskId, String taskTitle, int taskIcon, String taskDesc, int awardCoin, int taskState, int publishUserId, int receiveUserId, boolean verifyState) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskIcon = taskIcon;
        this.taskDesc = taskDesc;
        this.awardCoin = awardCoin;
        this.taskState = taskState;
        this.publishUserId = publishUserId;
        this.receiveUserId = receiveUserId;
        this.verifyState = verifyState;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public int getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(int taskIcon) {
        this.taskIcon = taskIcon;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getAwardCoin() {
        return awardCoin;
    }

    public void setAwardCoin(int awardCoin) {
        this.awardCoin = awardCoin;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public int getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(int publishUserId) {
        this.publishUserId = publishUserId;
    }

    public int getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(int receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public boolean isVerifyState() {
        return verifyState;
    }

    public void setVerifyState(boolean verifyState) {
        this.verifyState = verifyState;
    }

}
