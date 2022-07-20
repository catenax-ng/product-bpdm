package org.eclipse.tractusx.bpdm.gate.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.eclipse.tractusx.bpdm.common.dto.SiteWithReferencesDto
import org.eclipse.tractusx.bpdm.gate.dto.request.PaginationStartAfterRequest
import org.eclipse.tractusx.bpdm.gate.dto.response.PageStartAfterResponse
import org.springdoc.api.annotations.ParameterObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/catena/sites")
class SiteController {
    @Operation(
        summary = "Create or update sites.",
        description = "Create or update sites. " +
                "Updates instead of creating a new site if an already existing external id is used. " +
                "The same external id may not occur more than once in a single request. " +
                "For a single request, the maximum number of sites in the request is limited to \${bpdm.api.upsert-limit} entries."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Sites were successfully updated or created"),
            ApiResponse(responseCode = "400", description = "On malformed site request", content = [Content()]),
        ]
    )
    @PutMapping
    fun upsertSites(@RequestBody sites: Collection<SiteWithReferencesDto>): ResponseEntity<Any> {
        TODO()
    }

    @Operation(
        summary = "Get site by external identifier",
        description = "Get site by external identifier."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Found site with external identifier"),
            ApiResponse(responseCode = "404", description = "No site found under specified external identifier", content = [Content()])
        ]
    )
    @GetMapping("/{externalId}")
    fun getSiteByExternalId(@Parameter(description = "External identifier") @PathVariable externalId: String): SiteWithReferencesDto {
        TODO()
    }

    @Operation(
        summary = "Get page of sites",
        description = "Get page of sites."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The requested page of sites"),
            ApiResponse(responseCode = "400", description = "On malformed pagination request", content = [Content()]),
        ]
    )
    @GetMapping
    fun getSites(@ParameterObject @Valid paginationRequest: PaginationStartAfterRequest): PageStartAfterResponse<SiteWithReferencesDto> {
        TODO()
    }
}