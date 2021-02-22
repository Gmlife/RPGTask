package pojo;

import util.CodeListUtil;

import java.util.List;

public class UserTask {
    private int uid;
    private String lastSignedTime;
    private int signedDay;
    private String finishSysTaskId;
    private String publishTaskId;
    private String finishTaskId;
    private List<String> finishSysTaskIdList;
    private List<String> publishTaskIdList;
    private List<String> finishTaskIdList;

    public UserTask(int uid, String lastSignedTime, int signedDay, String finishSysTaskId, String publishTaskId, String finishTaskId) {
        this.uid = uid;
        this.lastSignedTime = lastSignedTime;
        this.signedDay = signedDay;
        this.finishSysTaskId = finishSysTaskId;
        this.publishTaskId = publishTaskId;
        this.finishTaskId = finishTaskId;
        this.finishSysTaskIdList = CodeListUtil.codeToList(finishSysTaskId);
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

    public String getFinishSysTaskId() {
        return finishSysTaskId;
    }

    public void setFinishSysTaskId(String finishSysTaskId) {
        this.finishSysTaskId = finishSysTaskId;
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

    public List<String> getFinishSysTaskIdList() {
        return finishSysTaskIdList;
    }

    public void setFinishSysTaskIdList(List<String> finishSysTaskIdList) {
        this.finishSysTaskIdList = finishSysTaskIdList;
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
