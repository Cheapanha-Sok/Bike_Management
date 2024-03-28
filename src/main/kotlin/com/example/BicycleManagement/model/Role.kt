package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*


@Entity
@Table(name = "roles")
@JsonIgnoreProperties("customers")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int ?=null,
    var name : String?=null,
    @OneToMany(mappedBy = "role" , fetch = FetchType.LAZY)
    var customers : List<Customer> ?=null
)
