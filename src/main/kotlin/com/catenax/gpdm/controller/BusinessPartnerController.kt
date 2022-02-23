package com.catenax.gpdm.controller

import com.catenax.gpdm.config.BpnConfigProperties
import com.catenax.gpdm.dto.request.BusinessPartnerRequest
import com.catenax.gpdm.dto.request.PaginationRequest
import com.catenax.gpdm.dto.response.BusinessPartnerResponse
import com.catenax.gpdm.dto.response.PageResponse
import com.catenax.gpdm.service.BusinessPartnerService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("catena/business-partner")
class BusinessPartnerController(
    val businessPartnerService: BusinessPartnerService,
    val bpnConfigProperties: BpnConfigProperties
) {
    @GetMapping("/")
    fun getBusinessPartners(@Valid paginationRequest: PaginationRequest): PageResponse<BusinessPartnerResponse> {
        return businessPartnerService.findPartners(PageRequest.of(paginationRequest.page, paginationRequest.size))
    }

    @GetMapping("/{idValue}")
    fun getBusinessPartner(@PathVariable idValue: String,
                           @RequestParam idType: String?
    ): BusinessPartnerResponse {
        val actualType = idType ?: bpnConfigProperties.id
        return if(actualType == bpnConfigProperties.id) businessPartnerService.findPartner(idValue)
        else businessPartnerService.findPartnerByIdentifier(actualType, idValue)
    }

    @PostMapping("/")
    fun createBusinessPartners(@RequestBody businessPartners: Collection<BusinessPartnerRequest>) : Collection<BusinessPartnerResponse>{
        return businessPartnerService.createPartners(businessPartners)
    }

}