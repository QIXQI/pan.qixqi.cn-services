package cn.qixqi.pan.authentication.service;

import cn.qixqi.pan.authentication.model.UserBase;
import cn.qixqi.pan.authentication.repository.UserBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBaseService{

    private final static Logger logger = LoggerFactory.getLogger(UserBaseService.class);

    @Autowired
    private UserBaseRepository userBaseRepository;

    public UserBase getUserBaseByAuth(String authInfo){
        UserBase userBase = userBaseRepository.findByPhoneNumberOrUnameOrEmail(authInfo, authInfo, authInfo);
        logger.debug(String.format("验证信息：%s, 密码：%s", authInfo, userBase.getPassword()));
        return userBase;
    }
}