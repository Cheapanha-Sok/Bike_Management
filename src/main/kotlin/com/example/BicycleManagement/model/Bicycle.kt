package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "bicycles")
data class Bicycle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long ? = null,
    var name :String ? = null,
    @Column(name = "manufacture_date")
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    var manufactureDate : Date ? = null,
    @Column(name = "price")
    var sellPrice : Double ? = null,
    var quantity :Int ? = null,
    var status : Boolean ? = true,

    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    var category : Category ?=null,

    @OneToMany(mappedBy = "bicycle" , fetch = FetchType.LAZY)
    var invoiceItem: List<InvoiceItem> ?=null,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_id" , referencedColumnName = "id")
    var import : Import ?=null
)
