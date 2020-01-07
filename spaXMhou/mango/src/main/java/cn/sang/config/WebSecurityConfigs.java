//package cn.sang.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
//@Configuration
//public class WebSecurityConfigs extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setSecurityMetadataSource(cfisms());
//                        o.setAccessDecisionManager(cadm());
//                        return o;
//                    }
//                }).and()
//                .formLogin()
//                .loginProcessingUrl("/login").permitAll()
//                .and()
//                .csrf().disable();
//    }
//    @Bean
//    CustomFilterInvocationSecurityMetadataSource cfisms(){
//        return new CustomFilterInvocationSecurityMetadataSource();
//    }
//    @Bean
//    AccessDecisionManager cadm(){
//        return new AccessDecisionManager();
//    }
//
//}
