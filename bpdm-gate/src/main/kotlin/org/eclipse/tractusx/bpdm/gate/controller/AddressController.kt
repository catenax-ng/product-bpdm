package org.eclipse.tractusx.bpdm.gate.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.eclipse.tractusx.bpdm.common.dto.LegalEntityDto
import org.eclipse.tractusx.bpdm.gate.config.ApiConfigProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/catena/addresses")
class AddressController(
    val apiConfigProperties: ApiConfigProperties
) {

    @Operation(
        summary = "Create or update legal entities.",
        description = "Create or update legal entities. " +
                "Updates instead of creating a new legal entity if an already existing external id is used. " +
                "The same external id may not occur more than once in a single request. " +
                "For a single request, the maximum number of legal entities in the request is limited to \${bpdm.api.upsert-limit} entries."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Legal entities were successfully updated or created"),
            ApiResponse(responseCode = "400", description = "On malformed legal entity request", content = [Content()]),
        ]
    )
    @PutMapping
    fun upsertAddresses(@RequestBody legalEntities: Collection<LegalEntityDto>): ResponseEntity<Any> {
        TODO()
    }

//    @Operation(
//        summary = "Get legal entity by external identifier",
//        description = "Get legal entity by external identifier."
//    )
//    @ApiResponses(
//        value = [
//            ApiResponse(responseCode = "200", description = "Found legal entity with external identifier"),
//            ApiResponse(responseCode = "404", description = "No legal entity found under specified external identifier", content = [Content()])
//        ]
//    )
//    @GetMapping("/{externalId}")
//    fun getLegalEntityByExternalId(@Parameter(description = "External identifier") @PathVariable externalId: String): LegalEntityDto {
//
//    }
//
//    @Operation(
//        summary = "Get page of legal entities",
//        description = "Get page of legal entities."
//    )
//    @ApiResponses(
//        value = [
//            ApiResponse(responseCode = "200", description = "The sites for the specified bpn"),
//            ApiResponse(responseCode = "400", description = "On malformed pagination request", content = [Content()]),
//        ]
//    )
//    @GetMapping
//    fun getLegalEntities(@ParameterObject @Valid paginationRequest: PaginationStartAfterRequest): PageStartAfterResponse<LegalEntityDto> {
//    }
}