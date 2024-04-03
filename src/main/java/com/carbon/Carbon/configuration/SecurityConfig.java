package com.carbon.Carbon.configuration;

import com.carbon.Carbon.filter.JwaAuth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwaAuth jwaAuth;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return
                http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(
                                req->req.requestMatchers("/login/**","/register/**")
                                        .permitAll()
                                        .requestMatchers(Auth_Whitelist).permitAll()
                                        .requestMatchers("/fetchApi/**").hasAuthority("MEMBER")
                                        .anyRequest()
                                        //.requestMatchers("/admin_only/**").hasAuthority("ADMIN")
                                        //.anyRequest()
                                        .authenticated()
                        ).userDetailsService(userDetailsService)
                        .sessionManagement(session->session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwaAuth, UsernamePasswordAuthenticationFilter.class)
                        .build();

    }
    private static final String[] Auth_Whitelist = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/fetchApi/**"
    };


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
