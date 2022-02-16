package com.catenax.gpdm.adapter.cdq.dto

data class BusinessPartnerDocumentStatusCdq(
    val status: BusinessPartnerDocumentStatusType,
    val explanations: Collection<String> = emptyList()
)

enum class BusinessPartnerDocumentStatusType(){
    ACCEPTED,
    REJECTED
}
