package pojo;

public class Message {
    private int messageId;
    private int messageUserId;
    private String messageText;
    private int messageTargetId;

    public Message(int messageId, int messageUserId, String messageText, int messageTargetId) {
        this.messageId = messageId;
        this.messageUserId = messageUserId;
        this.messageText = messageText;
        this.messageTargetId = messageTargetId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(int messageUserId) {
        this.messageUserId = messageUserId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getMessageTargetId() {
        return messageTargetId;
    }

    public void setMessageTargetId(int messageTargetId) {
        this.messageTargetId = messageTargetId;
    }
}
