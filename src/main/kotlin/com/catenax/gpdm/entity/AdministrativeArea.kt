package com.catenax.gpdm.entity

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.LanguageCode
import javax.persistence.*

@Entity
@Table(name = "administrative_areas")
class AdministrativeArea(
    @Column(name = "`value`", nullable = false)
    val value: String,
    @Column(name = "short_name")
    val shortName: String?,
    @Column(name = "fips_code")
    val fipsCode: String?,
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: AdministrativeAreaType,
    @Column(name = "language", nullable = false)
    @Enumerated(EnumType.STRING)
    val language: LanguageCode,
    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    val countryCode: CountryCode,
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    val address: Address
): BaseEntity()

enum class AdministrativeAreaType(private val areaName: String, private val url: String): NamedUrlType{
    COUNTY("County", ""),
    REGION("Region", ""),
    OTHER("Other", "");

    override fun getTypeName(): String {
        return areaName
    }

    override fun getUrl(): String {
        return url
    }
}