package com.catenax.gpdm.dto.response

import com.catenax.gpdm.dto.GeoCoordinateDto
import com.catenax.gpdm.dto.response.type.TypeKeyNameDto
import com.catenax.gpdm.dto.response.type.TypeKeyNameUrlDto
import com.catenax.gpdm.entity.AddressType
import com.neovisionaries.i18n.CountryCode

data class AddressResponse (
    val versions: AddressVersionResponse,
    val careOf: String?,
    val contexts: Collection<String>,
    val country: TypeKeyNameDto<CountryCode>,
    val administrativeAreas: Collection<AdministrativeAreaResponse>,
    val postCodes: Collection<PostCodeResponse>,
    val localities: Collection<LocalityResponse>,
    val thoroughfares: Collection<ThoroughfareResponse>,
    val premises: Collection<PremiseResponse>,
    val postalDeliveryPoints: Collection<PostalDeliveryPointResponse>,
    val geographicCoordinates: GeoCoordinateDto?,
    val types: Collection<TypeKeyNameUrlDto<AddressType>>
    )