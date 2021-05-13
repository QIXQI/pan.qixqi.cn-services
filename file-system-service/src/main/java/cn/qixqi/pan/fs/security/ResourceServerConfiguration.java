package cn.qixqi.pan.fs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/v1/filesystem/file/**").hasRole("ADMIN")    // 管理员获取文件实体
                .antMatchers(HttpMethod.GET, "/v1/filesystem/folderLink").hasRole("ADMIN")      // 管理员获取所有文件夹列表
                .antMatchers(HttpMethod.GET, "/v1/filesystem/folderLink/").hasRole("ADMIN")
                .anyRequest().authenticated();          // 其余任何请求，都需要权限
    }
}
