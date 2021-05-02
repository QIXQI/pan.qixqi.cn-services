package cn.qixqi.pan.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_ext")
public class UserExt {

    @Id
    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "avatar")
    private String avatar;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserExt withUid(String uid){
        this.setUid(uid);
        return this;
    }

    public UserExt withAvatar(String avatar){
        this.setAvatar(avatar);
        return this;
    }
}

