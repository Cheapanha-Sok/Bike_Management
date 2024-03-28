package com.example.BicycleManagement.service.impl

import com.example.BicycleManagement.repository.CustomerRepository
import com.example.BicycleManagement.service.UserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl(private val customerRepository: CustomerRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        return customerRepository.findByEmail(email!!).orElseThrow { UsernameNotFoundException("User not found with email with $email") }
    }
}