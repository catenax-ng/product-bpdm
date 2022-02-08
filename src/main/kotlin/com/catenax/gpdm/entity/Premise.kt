package com.catenax.gpdm.entity

import com.neovisionaries.i18n.LanguageCode
import javax.persistence.*

@Entity
@Table(name = "premises")
class Premise(
    @Column(name = "`value`", nullable = false)
    val value: String,
    @Column(name = "short_name")
    val shortName: String?,
    @Column(name = "number")
    val number: String?,
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: PremiseType,
    @Column(name = "language", nullable = false)
    @Enumerated(EnumType.STRING)
    val language: LanguageCode,
    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    val address: Address
) : BaseEntity()

enum class PremiseType(private val typeName: String, private val url: String): NamedUrlType{
    BUILDING("Building", ""),
    OTHER("Other type", ""),
    LEVEL("Level", ""),
    HARBOUR("Harbour", ""),
    ROOM("Room", ""),
    SUITE("Suite", ""),
    UNIT("Unit", ""),
    WAREHOUSE("Warehouse", "");

    override fun getTypeName(): String {
        return typeName
    }

    override fun getUrl(): String {
        return url
    }
}