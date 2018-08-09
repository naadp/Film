package com.yao.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security 配置
 * @author Administrator
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * 配置用户认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("yao")
			.password("j`4@2oi#3sj$d.s/")
			.roles("ADMIN");
	}

	/**
	 * 请求授权
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().frameOptions().disable();

		http.csrf().disable().cors().disable().headers().disable()
		 .authorizeRequests()
				// 配置不需要身份认证的请求地址
		 .antMatchers("/","/static/**","/film/**","/webSite/**","/webSiteInfo/**","/aboutMe").permitAll()
				// 其他所有访问路径需要身份认证。除了上面配置的，其它的路径均需要登录才能访问。
				 .anyRequest().authenticated()
		 .and()
		 .formLogin()
		 .loginPage("/login") // 指定登录请求地址
		 .defaultSuccessUrl("/admin") // 登录成功后的默认跳转页面
		 .permitAll()
		 .and()
		 .logout()
		 .logoutSuccessUrl("/login")
		 .permitAll();
	}

	
}
