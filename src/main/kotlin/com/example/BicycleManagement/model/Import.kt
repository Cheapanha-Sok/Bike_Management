package com.example.BicycleManagement.model

import jakarta.persistence.*
import java.sql.Date
import kotlin.jvm.Transient

@Entity
@Table(name = "imports")
data class Import(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?=null,
    @Column(name = "bicycle_name")
    var bikeName : String?=null,
    var quantity : Int ?=null,
    @Column(name = "manu_facture_date")
    var manuFactureDate : Date ?=null,
    @Column(name = "unit_price")
    var unitPrice : Double? = null,
    @Column(name = "create_at")
    var createAt : Date? = null, // Make this field nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id" , referencedColumnName = "id")
    var supplier: Supplier?=null,
    @OneToMany(mappedBy = "import" , fetch = FetchType.LAZY)
    var bicycles : List<Bicycle>?=null,
    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    var category : Category?=null,
    @Transient
    var sellPrice : Double?=null,
)
