package org.eclipse.tractusx.bpdm.pool.dto.response

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import org.eclipse.tractusx.bpdm.common.dto.response.AddressResponse

@Schema(name = "Site Response", description = "Site of a business partner")
data class SiteResponse(
    @Schema(description = "Business Partner Number, main identifier value for sites")
    val bpn: String,
    @Schema(description = "Site name")
    val name: String,
    @ArraySchema(arraySchema = Schema(description = "Addresses of the site"))
    val addresses: Collection<AddressResponse> = emptyList(),
)