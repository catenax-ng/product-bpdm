package org.eclipse.tractusx.bpdm.common.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "Geo Coordinates", description = "Geo coordinates record for an address")
data class GeoCoordinateDto (
    @Schema(description = "Longitude coordinate")
    val longitude: Float,
    @Schema(description = "Latitude coordinate")
    val latitude: Float,
    @Schema(description = "Altitude, if applicable")
    val altitude: Float? = null
        )