package cn.qixqi.pan.user.service;

import cn.qixqi.pan.user.config.ServiceConfig;
import cn.qixqi.pan.user.model.UserBase;
import cn.qixqi.pan.user.model.UserExt;
import cn.qixqi.pan.user.repository.UserBaseRepository;
import cn.qixqi.pan.user.repository.UserExtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserBaseRepository userBaseRepository;

    @Autowired
    private UserExtRepository userExtRepository;

    @Autowired
    private ServiceConfig config;

    public UserBase getUserBaseByAuth(String authInfo, String password, String authType){
        UserBase userBase = null;
        switch (authType){
            case "phoneNumber":
                userBase = getUserBaseByPhoneNum(authInfo, password);
                break;
            case "uname":
                userBase = getUserBaseByUname(authInfo, password);
                break;
            case "email":
                userBase = getUserBaseByEmail(authInfo, password);
                break;
            default:
                logger.info(String.format("%s 登录失败，验证方式：%s", authInfo, authType));
        }
        userBase.setPassword(null);
        return userBase;
    }

    private UserBase getUserBaseByPhoneNum(String phoneNumber, String password){
        return userBaseRepository.findByPhoneNumberAndPassword(phoneNumber, password);
    }

    private UserBase getUserBaseByUname(String uname, String password){
        return userBaseRepository.findByUnameAndPassword(uname, password);
    }

    private UserBase getUserBaseByEmail(String email, String password){
        return userBaseRepository.findByEmailAndPassword(email, password);
    }

    public UserBase getUserBaseByUid(String uid){
        UserBase userBase =  userBaseRepository.findByUid(uid);
        userBase.setPassword(null);
        return userBase;
    }

    public UserBase addUserBase(UserBase userBase){
        userBase.setUid(UUID.randomUUID().toString());
        return userBaseRepository.save(userBase);
    }

    public UserBase updateUserBase(UserBase userBase){
        return userBaseRepository.save(userBase);
    }

    public void deleteUserBase(String uid){
        userBaseRepository.deleteById(uid);
    }

    public UserExt getUserExt(String uid){
        return userExtRepository.findByUid(uid);
    }

    public UserExt addUserExt(UserExt userExt){
        return userExtRepository.save(userExt);
    }

    public UserExt updateUserExt(UserExt userExt){
        return userExtRepository.save(userExt);
    }

    /** 由于数据库存在外键，不需要手动删除 */
    public void deleteUserExt(String uid){
        userExtRepository.deleteById(uid);
    }
}
