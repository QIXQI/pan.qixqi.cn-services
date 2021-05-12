package cn.qixqi.pan.authentication.repository;

import cn.qixqi.pan.authentication.model.UserBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository extends CrudRepository<UserBase, String> {
    UserBase findByPhoneNumberOrUnameOrEmail(String phoneNumber, String Uname, String Email);
}
