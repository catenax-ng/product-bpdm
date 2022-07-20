package org.eclipse.tractusx.bpdm.pool.entity

import com.neovisionaries.i18n.CountryCode
import org.eclipse.tractusx.bpdm.common.model.AddressType
import javax.persistence.*

@Entity
@Table(
    name = "addresses",
    indexes = [
        Index(columnList = "partner_id")
    ]
)
class Address (
    @Column(name = "bpn", nullable = false, unique = true)
    var bpn: String,
    @Column(name = "care_of")
    var careOf: String?,
    @ElementCollection(targetClass = String::class)
    @JoinTable(
        name = "address_contexts",
        joinColumns = [JoinColumn(name = "address_id")],
        indexes = [Index(columnList = "address_id")]
    )
    @Column(name = "context", nullable = false)
    val contexts: MutableSet<String> = mutableSetOf(),
    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    var country: CountryCode,
    @ElementCollection(targetClass = AddressType::class)
    @Enumerated(EnumType.STRING)
    @JoinTable(
        name = "address_types",
        joinColumns = [JoinColumn(name = "address_id")],
        indexes = [Index(columnList = "address_id")]
    )
    @Column(name = "type", nullable = false)
    val types: MutableSet<AddressType> = mutableSetOf(),
    @Embedded
    var version: AddressVersion,
    @Embedded
    var geoCoordinates: GeographicCoordinate?,
    @ManyToOne
    @JoinColumn(name = "partner_id")
    var partner: BusinessPartner?,
    @ManyToOne
    @JoinColumn(name = "site_id")
    var site: Site?
) : BaseEntity() {
    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], orphanRemoval = true)
    val administrativeAreas: MutableSet<AdministrativeArea> = mutableSetOf()

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], orphanRemoval = true)
    val postCodes: MutableSet<PostCode> = mutableSetOf()

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], orphanRemoval = true)
    val thoroughfares: MutableSet<Thoroughfare> = mutableSetOf()

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], orphanRemoval = true)
   val premises: MutableSet<Premise> = mutableSetOf()

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], orphanRemoval = true)
    val postalDeliveryPoints: MutableSet<PostalDeliveryPoint> = mutableSetOf()

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], orphanRemoval = true)
   val localities: MutableSet<Locality> = mutableSetOf()
}



