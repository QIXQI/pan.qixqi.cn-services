package cn.qixqi.pan.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class TestTable {
    @Id
    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "email", nullable = true)
    private String email;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TestTable withUid(String uid){
        this.setUid(uid);
        return this;
    }

    public TestTable withEmail(String email){
        this.setEmail(email);
        return this;
    }
}
