package com.example.BicycleManagement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "invoices")
@JsonIgnoreProperties("customer")
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long ? =null,
    @Column(name = "invoice_date")
    var invoiceDate : Date ? =null,
    @Column(name = "total_price")
    var totalPrice : Double? =null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    var customer: Customer?=null,
    @OneToMany(mappedBy = "invoices" , fetch = FetchType.LAZY)
    var invoiceItems : List<InvoiceItem>?=null,
)