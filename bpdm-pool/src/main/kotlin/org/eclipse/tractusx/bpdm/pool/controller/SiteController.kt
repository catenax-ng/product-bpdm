package org.eclipse.tractusx.bpdm.pool.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.eclipse.tractusx.bpdm.pool.dto.request.PaginationRequest
import org.eclipse.tractusx.bpdm.pool.dto.request.SiteSearchRequest
import org.eclipse.tractusx.bpdm.pool.dto.response.PageResponse
import org.eclipse.tractusx.bpdm.pool.dto.response.SiteWithReferenceResponse
import org.eclipse.tractusx.bpdm.pool.service.SiteService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/catena/sites")
class SiteController(
    val siteService: SiteService
) {

    @Operation(
        summary = "Get site by bpn",
        description = "Get site by bpn-s of the site."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Found site with specified bpn"),
            ApiResponse(responseCode = "400", description = "On malformed request parameters", content = [Content()]),
            ApiResponse(responseCode = "404", description = "No site found under specified bpn", content = [Content()])
        ]
    )
    @GetMapping("/{bpn}")
    fun getSite(
        @Parameter(description = "Bpn value") @PathVariable bpn: String
    ): SiteWithReferenceResponse {
        return siteService.findByBpn(bpn)
    }

    @Operation(
        summary = "Search sites by BPNLs",
        description = "Search sites by legal entity BPNs"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Found sites that belong to specified legal entites"),
            ApiResponse(responseCode = "400", description = "On malformed request parameters", content = [Content()])
        ]
    )
    @PostMapping("/search")
    fun searchSites(
        @RequestBody siteSearchRequest: SiteSearchRequest,
        @ParameterObject paginationRequest: PaginationRequest
    ): PageResponse<SiteWithReferenceResponse> {
        return siteService.findByPartnerBpns(siteSearchRequest, paginationRequest)
    }
}