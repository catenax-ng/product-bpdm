package org.eclipse.tractusx.bpdm.pool.entity

import org.eclipse.tractusx.bpdm.common.model.BusinessPartnerType
import java.time.Instant
import javax.persistence.*

@Entity
@Table(
    name = "business_partners",
    indexes = [Index(columnList = "legal_form_id")]
)
class BusinessPartner(
    @Column(name = "bpn", nullable = false, unique = true)
    var bpn: String,
    @ManyToOne
    @JoinColumn(name = "legal_form_id")
    var legalForm: LegalForm?,
    @ElementCollection(targetClass = BusinessPartnerType::class)
    @JoinTable(name = "business_partner_types", joinColumns = [JoinColumn(name = "partner_id")], indexes = [Index(columnList = "partner_id")])
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var types: Set<BusinessPartnerType>,
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "business_partners_roles",
        joinColumns = [JoinColumn(name = "partner_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")],
        indexes = [Index(columnList = "partner_id")]
    )
    val roles: Set<Role>,
    @Column(name = "currentness", nullable = false)
    var currentness: Instant
) : BaseEntity() {
    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val identifiers: MutableSet<Identifier> = mutableSetOf()

    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val names: MutableSet<Name> = mutableSetOf()

    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val stati: MutableSet<BusinessStatus> = mutableSetOf()

    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val addresses: MutableSet<Address> = mutableSetOf()

    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sites: MutableSet<Site> = mutableSetOf()

    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val classification: MutableSet<Classification> = mutableSetOf()

    @OneToMany(mappedBy = "partner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val bankAccounts: MutableSet<BankAccount> = mutableSetOf()

    @OneToMany(mappedBy = "startNode", cascade = [CascadeType.ALL], orphanRemoval = true)
    val startNodeRelations: MutableSet<Relation> = mutableSetOf()

    @OneToMany(mappedBy = "endNode", cascade = [CascadeType.ALL], orphanRemoval = true)
    val endNodeRelations: MutableSet<Relation> = mutableSetOf()
}

