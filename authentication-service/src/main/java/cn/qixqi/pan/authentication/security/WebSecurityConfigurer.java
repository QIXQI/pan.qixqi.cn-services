package cn.qixqi.pan.authentication.security;

import cn.qixqi.pan.authentication.service.PanUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private PanUserDetailsService panUserDetailsService;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 方式一：用户信息内存存储
     * @return
     */
    /*@Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return super.userDetailsServiceBean();
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("john.carnell").password("password1").roles("USER").build());
        return manager;
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(panUserDetailsService);
        /**
         * 方式二：用户信息内存存储
         */
        /* auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("john.carnell")
                // .password("{noop}password1")        // 强制使用明文密码，但似乎不太行，哈哈
                .password(passwordEncoder().encode("password1"))
                .roles("USER")
                .and()
                .withUser("william.woodward")
                .password(passwordEncoder().encode("password2"))
                .roles("USER", "ADMIN"); */
    }
}
