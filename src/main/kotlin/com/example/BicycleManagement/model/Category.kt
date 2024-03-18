package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "categorys")
@JsonIgnoreProperties("bicycles")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ? =null,
    @Column(unique = true)
    var code :String ? =null,
    var name: String ? =null,
    @OneToMany(mappedBy = "category")
    var bicycles: List<Bicycle>? = null
)
