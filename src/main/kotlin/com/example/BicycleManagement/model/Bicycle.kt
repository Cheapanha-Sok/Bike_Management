package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "bicycles")
data class Bicycle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long ? = null,
    var name :String ? = null,
    @Column(name = "manufacture_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    var manufactureDate : LocalDate ? = null,
    @Column(name = "unit_price")
    var unitPrice : Float ? = null,
    @Column(name = "sell_price")
    var sellPrice : Float ? = null,
    var quantity :Int ? = null,
    var status : Boolean ? = true,
    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    var category : Category ?=null,
    @ManyToMany
    @JoinTable(
        name = "bicycle_suppliers",
        joinColumns = [JoinColumn(name = "bicycle_id" , referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "supplier_id" , referencedColumnName = "id")]
    )
    var suppliers : List<Supplier> ? = null
)
