package pojo;

public class UserDesc {
    private int uid;
    private String nickName;
    private boolean sex;
    // male = false , female = true
    private int icon;
    private String signature;
    private String title;
    private int coin;
    private int exp;
    private String regTime;

    public UserDesc(int uid, String nickName, boolean sex, int icon, String signature, String title, int coin, int exp, String regTime) {
        this.uid = uid;
        this.nickName = nickName;
        this.sex = sex;
        this.icon = icon;
        this.signature = signature;
        this.title = title;
        this.coin = coin;
        this.exp = exp;
        this.regTime = regTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
}
