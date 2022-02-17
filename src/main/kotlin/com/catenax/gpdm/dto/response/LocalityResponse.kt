package com.catenax.gpdm.dto.response

import com.catenax.gpdm.dto.response.type.TypeKeyNameDto
import com.catenax.gpdm.dto.response.type.TypeKeyNameUrlDto
import com.catenax.gpdm.entity.LocalityType
import com.neovisionaries.i18n.LanguageCode
import java.util.*

data class LocalityResponse (
    val uuid: UUID,
    val value: String,
    val shortName: String?,
    val type: TypeKeyNameUrlDto<LocalityType>,
    val language: TypeKeyNameDto<LanguageCode>
        )