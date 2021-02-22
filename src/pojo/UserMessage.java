package pojo;

import util.CodeListUtil;

import java.util.List;

public class UserMessage {
    private int uid;
    private String messageId;
    private List<String> messageIdList;

    public UserMessage(int uid, String messageId) {
        this.uid = uid;
        this.messageId = messageId;
        this.messageIdList = CodeListUtil.codeToList(messageId);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public List<String> getMessageIdList() {
        return messageIdList;
    }

    public void setMessageIdList(List<String> messageIdList) {
        this.messageIdList = messageIdList;
    }
}
