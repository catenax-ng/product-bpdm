package org.eclipse.tractusx.bpdm.pool.entity

import org.eclipse.tractusx.bpdm.common.model.ClassificationType
import javax.persistence.*

@Entity
@Table(name = "classifications",
    indexes = [
        Index(columnList = "partner_id")
    ])
class Classification (
    @Column(name = "`value`", nullable = false)
    val value: String,
    @Column(name = "code")
    val code: String?,
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: ClassificationType?,
    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    var partner: BusinessPartner
        ): BaseEntity()

