package cn.qixqi.pan.user.repository;

import cn.qixqi.pan.user.model.UserExt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExtRepository extends CrudRepository<UserExt, String> {
    UserExt findByUid(String uid);
}
