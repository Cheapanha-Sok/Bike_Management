package com.example.BicycleManagement.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    private var password: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    var role: Role? = null,
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    var invoices: List<Invoice>? = null,
    @OneToOne(mappedBy = "customer")
    var refreshToken : RefreshToken?=null,
    @Column(name = "is_enabled")
    private var isEnabled: Boolean = false,
) : UserDetails{

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        role?.let {
            authorities.add(SimpleGrantedAuthority(it.name)) // Assuming 'name' is the identifier for the role
        }
        return authorities
    }

    fun setPassword(password :String) {
        this.password = password
    }

    override fun getPassword(): String {
        return password ?: "" // Return the password for this user
    }

    override fun getUsername(): String {
        return email ?: "" // Return the username (email) for this user
    }

    override fun isAccountNonExpired(): Boolean {
        return true // Implement account expiration logic if needed
    }

    override fun isAccountNonLocked(): Boolean {
        return true // Implement account locking logic if needed
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true // Implement credentials expiration logic if needed
    }

    override fun isEnabled(): Boolean {
        return this.isEnabled // Implement account enabled/disabled logic if needed
    }
    fun setEnabled(isEnabled: Boolean) {
        this.isEnabled = isEnabled
    }
}
