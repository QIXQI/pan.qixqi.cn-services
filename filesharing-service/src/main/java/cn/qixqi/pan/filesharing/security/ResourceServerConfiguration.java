package cn.qixqi.pan.filesharing.security;

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
                .antMatchers(HttpMethod.POST, "/v1/filesharing/share").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/filesharing/fileShare").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/filesharing/fileShare/").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/filesharing/fileShareLink").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/filesharing/fileShareLink/").hasAuthority("ADMIN")
                .anyRequest().authenticated();          // 其余任何请求，都需要权限
    }
}
