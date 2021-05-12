package cn.qixqi.pan.user.repository;

import cn.qixqi.pan.user.model.UserBase;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserBaseRepository extends CrudRepository<UserBase, String> {
    UserBase findByPhoneNumberAndPassword(String phoneNumber, String password);
    UserBase findByUnameAndPassword(String uname, String password);
    UserBase findByEmailAndPassword(String email, String password);

    UserBase findByUid(String uid);

    // 更新密码，返回影响行数
    @Transactional      // 默认事务只读，需要添加事务注解
    @Modifying
    @Query("UPDATE UserBase u SET u.password = :newPass WHERE u.uid = :uid AND u.password = :oldPass")
    int updatePass(@Param("uid") String uid, @Param("oldPass") String oldPass, @Param("newPass") String newPass);
}
