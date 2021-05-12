package cn.qixqi.pan.user.repository;

import cn.qixqi.pan.user.model.UserBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository extends CrudRepository<UserBase, String> {
    UserBase findByPhoneNumberAndPassword(String phoneNumber, String password);
    UserBase findByUnameAndPassword(String uname, String password);
    UserBase findByEmailAndPassword(String email, String password);

    UserBase findByUid(String uid);
}
