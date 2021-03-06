package org.eclipse.tractusx.bpdm.common.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.eclipse.tractusx.bpdm.common.model.BusinessStatusType
import java.time.LocalDateTime

@Schema(name = "Business Status", description = "Status record for a business partner")
data class BusinessStatusDto(
    @Schema(description = "Exact, official denotation of the status")
    val officialDenotation: String,
    @Schema(description = "Since when the status is/was valid")
    val validFrom: LocalDateTime,
    @Schema(description = "Until the status was valid, if applicable")
    val validUntil: LocalDateTime?,
    @Schema(description = "The type of this specified status")
    val type: BusinessStatusType
)