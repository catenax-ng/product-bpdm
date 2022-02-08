package com.catenax.gpdm.dto.response

import com.catenax.gpdm.dto.response.type.TypeKeyNameUrlDto
import com.catenax.gpdm.entity.BusinessStatusType
import java.time.LocalDateTime

data class BusinessStatusResponse (
        val officialDenotation: String?,
        val validFrom: LocalDateTime,
        val validUntil: LocalDateTime?,
        val type: TypeKeyNameUrlDto<BusinessStatusType>
        )