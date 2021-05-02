package cn.qixqi.pan.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_priority")
public class UserPriority {
    @Id
    @Column(name = "priority_id", nullable = false)
    private Integer priorityId;

    @Column(name = "priority_name", nullable = false)
    private String priorityName;

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }
}
