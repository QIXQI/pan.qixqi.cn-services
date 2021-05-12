package cn.qixqi.pan.authentication.service;

import cn.qixqi.pan.authentication.config.ServiceConfig;
import cn.qixqi.pan.authentication.model.UserBase;
import cn.qixqi.pan.authentication.util.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class PanUserDetailsService implements UserDetailsService {

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private ServiceConfig config;



    /**
     * @param authInfo 不一定是用户名，可以是：手机号、用户名、邮箱
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String authInfo) throws UsernameNotFoundException {
        UserBase userBase = userBaseService.getUserBaseByAuth(authInfo);
        if (userBase == null){
            throw new UsernameNotFoundException("手机号或用户名或邮箱不存在！");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(
                new SimpleGrantedAuthority(
                        config.getPRIORITYMAP().get(
                                userBase.getPriorityId())));
        // 保存uid
        UserContextHolder.get().setUid(userBase.getUid());
        return new User(authInfo, userBase.getPassword(), simpleGrantedAuthorities);
    }
}































