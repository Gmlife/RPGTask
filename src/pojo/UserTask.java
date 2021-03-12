package pojo;

import util.CodeListUtil;

import java.util.List;

public class UserTask {
    private int uid;
    private String lastSignedTime;
    private int signedDay;
    private String publishTaskId;
    private String doingTaskId;
    private String finishTaskId;
    private List<String> publishTaskIdList;
    private List<String> doingTaskIdList;
    private List<String> finishTaskIdList;

    public UserTask(int uid, String lastSignedTime, int signedDay, String publishTaskId, String doingTaskId, String finishTaskId) {
        this.uid = uid;
        this.lastSignedTime = lastSignedTime;
        this.signedDay = signedDay;
        this.doingTaskId = doingTaskId;
        this.publishTaskId = publishTaskId;
        this.finishTaskId = finishTaskId;
        this.doingTaskIdList = CodeListUtil.codeToList(doingTaskId);
        this.publishTaskIdList = CodeListUtil.codeToList(publishTaskId);
        this.finishTaskIdList = CodeListUtil.codeToList(finishTaskId);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLastSignedTime() {
        return lastSignedTime;
    }

    public void setLastSignedTime(String lastSignedTime) {
        this.lastSignedTime = lastSignedTime;
    }

    public int getSignedDay() {
        return signedDay;
    }

    public void setSignedDay(int signedDay) {
        this.signedDay = signedDay;
    }

    public String getDoingTaskId() {
        return doingTaskId;
    }

    public void setDoingTaskId(String doingTaskId) {
        this.doingTaskId = doingTaskId;
    }

    public String getPublishTaskId() {
        return publishTaskId;
    }

    public void setPublishTaskId(String publishTaskId) {
        this.publishTaskId = publishTaskId;
    }

    public String getFinishTaskId() {
        return finishTaskId;
    }

    public void setFinishTaskId(String finishTaskId) {
        this.finishTaskId = finishTaskId;
    }

    public List<String> getDoingTaskIdList() {
        return doingTaskIdList;
    }

    public void setDoingTaskIdList(List<String> doingTaskIdList) {
        this.doingTaskIdList = doingTaskIdList;
    }

    public List<String> getPublishTaskIdList() {
        return publishTaskIdList;
    }

    public void setPublishTaskIdList(List<String> publishTaskIdList) {
        this.publishTaskIdList = publishTaskIdList;
    }

    public List<String> getFinishTaskIdList() {
        return finishTaskIdList;
    }

    public void setFinishTaskIdList(List<String> finishTaskIdList) {
        this.finishTaskIdList = finishTaskIdList;
    }
}
