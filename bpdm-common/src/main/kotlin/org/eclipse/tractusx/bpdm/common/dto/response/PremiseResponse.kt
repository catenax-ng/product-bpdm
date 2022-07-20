package org.eclipse.tractusx.bpdm.common.dto.response

import com.neovisionaries.i18n.LanguageCode
import io.swagger.v3.oas.annotations.media.Schema
import org.eclipse.tractusx.bpdm.common.dto.response.type.TypeKeyNameDto
import org.eclipse.tractusx.bpdm.common.dto.response.type.TypeKeyNameUrlDto
import org.eclipse.tractusx.bpdm.common.model.PremiseType

@Schema(name = "Premise Response", description = "Premise record of an address such as building, room or floor")
data class PremiseResponse (
    @Schema(description = "Full denotation of the premise")
    val value: String,
    @Schema(description = "Abbreviation or shorthand")
    val shortName: String? = null,
    @Schema(description = "Premise number")
    val number: String? = null,
    @Schema(description = "Type of premise")
    val type: TypeKeyNameUrlDto<PremiseType>,
    @Schema(description = "Language the premise is specified in")
    val language: TypeKeyNameDto<LanguageCode>
        )