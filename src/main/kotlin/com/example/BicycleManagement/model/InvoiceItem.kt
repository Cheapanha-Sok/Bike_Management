package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*


@Entity
@Table(name = "invoice_items")
@JsonIgnoreProperties("invoices")
data class InvoiceItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long ? = null,
    var quantity : Int?=null,
    @Column(name = "sub_total_price")
    var subTotalPrice : Double ?= null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bicycle_id" , referencedColumnName = "id")
    var bicycle : Bicycle?=null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id" , referencedColumnName = "id")
    var invoices : Invoice?=null
)
