package com.catenax.gpdm.adapter.cdq.config

import com.catenax.gpdm.adapter.cdq.CdqRequestMappingService
import com.catenax.gpdm.adapter.cdq.PartnerImportPageService
import com.catenax.gpdm.adapter.cdq.dto.TypeKeyNameCdq
import com.catenax.gpdm.adapter.cdq.dto.TypeKeyNameUrlCdq
import com.catenax.gpdm.repository.ConfigurationEntryRepository
import com.catenax.gpdm.service.BusinessPartnerService
import com.catenax.gpdm.service.MetadataService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class CdqAdapterConfig(
    val adapterProperties: CdqAdapterConfigProperties,
    val cdqIdProperties: CdqIdentifierConfigProperties,
) {
    @Bean
    fun adapterClient(): WebClient{
        return WebClient.builder()
            .baseUrl("${adapterProperties.host}/${adapterProperties.api}/storages/${adapterProperties.storage}")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("x-api-key", adapterProperties.apiKey)
            .build()
    }

    @Bean
    fun importService(
        webClient: WebClient,
        configurationEntryRepository: ConfigurationEntryRepository,
        adapterProperties: CdqAdapterConfigProperties,
        cdqIdConfigProperties: CdqIdentifierConfigProperties,
        metadataService: MetadataService,
        mappingService: CdqRequestMappingService,
        businessPartnerService: BusinessPartnerService,
    ): PartnerImportPageService{
        return PartnerImportPageService(
            webClient,
            adapterProperties,
            cdqIdConfigProperties,
            metadataService,
            mappingService,
            businessPartnerService,
            createCdqIdentifierType(),
            createCdqImportedStatus(),
            createCdqIdentifierIssuer(),
        )
    }

    private fun createCdqIdentifierType(): TypeKeyNameUrlCdq {
        return TypeKeyNameUrlCdq(cdqIdProperties.typeKey, cdqIdProperties.typeName, "")
    }

    private fun createCdqIdentifierIssuer(): TypeKeyNameUrlCdq {
        return TypeKeyNameUrlCdq(cdqIdProperties.issuerKey, cdqIdProperties.issuerName, "")
    }

    private fun createCdqImportedStatus(): TypeKeyNameCdq {
        return TypeKeyNameCdq(cdqIdProperties.statusImportedKey, cdqIdProperties.statusImportedName)
    }
}