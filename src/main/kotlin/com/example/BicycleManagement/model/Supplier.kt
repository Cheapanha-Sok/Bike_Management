package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "suppliers")
data class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? =null,
    var name : String ? = null,
    @Column(name = "phone_number")
    var phoneNumber :String ? =null,
    @JsonIgnore
    @ManyToMany(mappedBy = "suppliers")
    var bicycles : List<Bicycle> ? = null
)