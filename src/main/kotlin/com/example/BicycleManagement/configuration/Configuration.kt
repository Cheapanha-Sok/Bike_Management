package com.example.BicycleManagement.configuration

import com.example.BicycleManagement.service.UserDetailsService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Configuration(private val userDetailsService: UserDetailsService) {
    //    // In memory authentication
//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val user = User.builder()
//            .username("user")
//            .password(passwordEncoder().encode("pass123"))
//            .roles("USER")
//            .build()
//        val admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("pass123"))
//            .roles("USER", "ADMIN")
//            .build()
//        return InMemoryUserDetailsManager(user, admin)
//    }
    @Bean
    fun authenticationProvider(): AuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setUserDetailsService(userDetailsService)
            it.setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}