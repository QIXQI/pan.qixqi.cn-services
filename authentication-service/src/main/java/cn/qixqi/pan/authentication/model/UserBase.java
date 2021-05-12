package cn.qixqi.pan.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_base")
public class UserBase {
    @Id
    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "uname", nullable = false)
    private String uname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "priority_id")
    private Integer priorityId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "sex")
    private char sex;

    @Column(name = "disk_capacity")
    private Integer diskCapacity;

    @Column(name = "free_disk_capacity")
    private double freeDiskCapacity;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "join_time")
    private Date joinTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Integer getDiskCapacity() {
        return diskCapacity;
    }

    public void setDiskCapacity(Integer diskCapacity) {
        this.diskCapacity = diskCapacity;
    }

    public double getFreeDiskCapacity() {
        return freeDiskCapacity;
    }

    public void setFreeDiskCapacity(double freeDiskCapacity) {
        this.freeDiskCapacity = freeDiskCapacity;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public UserBase withUid(String uid){
        this.setUid(uid);
        return this;
    }

    public UserBase withPhoneNumber(String phoneNumber){
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public UserBase withUname(String uname){
        this.setUname(uname);
        return this;
    }

    public UserBase withPassword(String password){
        this.setPassword(password);
        return this;
    }

    public UserBase withEmail(String email){
        this.setEmail(email);
        return this;
    }

    public UserBase withPriorityId(Integer priorityId){
        this.setPriorityId(priorityId);
        return this;
    }

    public UserBase withStatusId(Integer statusId){
        this.setStatusId(statusId);
        return this;
    }

    public UserBase withSex(char sex){
        this.setSex(sex);
        return this;
    }

    public UserBase withDiskCapacity(Integer diskCapacity){
        this.setDiskCapacity(diskCapacity);
        return this;
    }

    public UserBase withFreeDiskCapacity(double freeDiskCapacity){
        this.setFreeDiskCapacity(freeDiskCapacity);
        return this;
    }

    public UserBase withBirthday(Date birthday){
        this.setBirthday(birthday);
        return this;
    }

    public UserBase withJoinTime(Date joinTime){
        this.setJoinTime(joinTime);
        return this;
    }
}
