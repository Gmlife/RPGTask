package pojo;

public class User {
    private int uid;
    private boolean userType;
    // user = false , admin = true
    private String userName;
    private String userPass;

    public User(int uid, boolean userType, String userName, String userPass) {
        this.uid = uid;
        this.userType = userType;
        this.userName = userName;
        this.userPass = userPass;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean getUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
