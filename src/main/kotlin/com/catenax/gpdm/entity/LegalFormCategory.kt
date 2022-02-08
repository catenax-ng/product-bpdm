package com.catenax.gpdm.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "legal_form_categories")
class LegalFormCategory (
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "url")
    val url: String?
): BaseEntity()