package com.example.BicycleManagement.model

import jakarta.persistence.*

@Entity
@Table(name = "suppliers")
data class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long ? =null,
    var name : String ? = null,
    @Column(name = "phone_number")
    var phoneNumber :String ? =null,
    @OneToMany(mappedBy = "supplier" , fetch = FetchType.LAZY)
    var imports : List<Import>?=null,
)