package com.catenax.gpdm.service

import com.catenax.gpdm.dto.response.BpnIdentifierMappingResponse
import com.catenax.gpdm.dto.response.BusinessPartnerResponse
import com.catenax.gpdm.entity.Address
import com.catenax.gpdm.entity.BusinessPartner
import com.catenax.gpdm.entity.Identifier
import com.catenax.gpdm.entity.IdentifierType
import com.catenax.gpdm.exception.BpdmNotFoundException
import com.catenax.gpdm.repository.BusinessPartnerRepository
import com.catenax.gpdm.repository.IdentifierRepository
import com.catenax.gpdm.repository.IdentifierTypeRepository
import com.catenax.gpdm.repository.LegalFormRepository
import com.catenax.gpdm.repository.entity.AddressRepository
import com.catenax.gpdm.repository.entity.BankAccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service for fetching business partner records from the database
 */
@Service
class BusinessPartnerFetchService(
    private val businessPartnerRepository: BusinessPartnerRepository,
    private val identifierTypeRepository: IdentifierTypeRepository,
    private val addressRepository: AddressRepository,
    private val identifierRepository: IdentifierRepository,
    private val legalFormRepository: LegalFormRepository,
    private val bankAccountRepository: BankAccountRepository
) {

    /**
     * Fetch a business partner by [bpn] and return as [BusinessPartnerResponse]
     */
    @Transactional
    fun findPartner(bpn: String): BusinessPartnerResponse {
        val bp = businessPartnerRepository.findByBpn(bpn) ?: throw BpdmNotFoundException("Business Partner", bpn)
        return bp.toDto()
    }

    /**
     * Fetch business partners by BPN in [bpns]
     */
    @Transactional
    fun fetchByBpns(bpns: Collection<String>): Set<BusinessPartner> {
        return fetchBusinessPartnerDependencies(businessPartnerRepository.findDistinctByBpnIn(bpns))
    }

    /**
     * Fetch a business partner by [identifierValue] of [identifierType] and return as [BusinessPartnerResponse]
     */
    @Transactional
    fun findPartnerByIdentifier(identifierType: String, identifierValue: String): BusinessPartnerResponse {
        val type = identifierTypeRepository.findByTechnicalKey(identifierType) ?: throw BpdmNotFoundException(IdentifierType::class, identifierType)
        return businessPartnerRepository.findByIdentifierTypeAndValue(type, identifierValue)?.toDto()
            ?: throw BpdmNotFoundException("Identifier Value", identifierValue)
    }

    /**
     * Fetch business partners by [values] of [identifierType]
     */
    @Transactional
    fun fetchByIdentifierValues(identifierType: String, values: Collection<String>): Set<BusinessPartner> {
        return fetchBusinessPartnerDependencies(businessPartnerRepository.findByIdentifierTypeAndValues(identifierType, values))
    }

    /**
     * Find bpn to identifier value mappings by [idValues] of [identifierType]
     */
    @Transactional
    fun findBpnsByIdentifiers(identifierType: String, idValues: Collection<String>): Set<BpnIdentifierMappingResponse> {
        val type = identifierTypeRepository.findByTechnicalKey(identifierType) ?: throw BpdmNotFoundException(IdentifierType::class, identifierType)
        return identifierRepository.findBpnsByIdentifierTypeAndValues(type, idValues)
    }

    private fun fetchBusinessPartnerDependencies(partners: Set<BusinessPartner>): Set<BusinessPartner> {

        businessPartnerRepository.joinIdentifiers(partners)
        businessPartnerRepository.joinNames(partners)
        businessPartnerRepository.joinAddresses(partners)
        businessPartnerRepository.joinStatuses(partners)
        businessPartnerRepository.joinClassifications(partners)
        businessPartnerRepository.joinBankAccounts(partners)
        businessPartnerRepository.joinRelations(partners)
        businessPartnerRepository.joinTypes(partners)
        businessPartnerRepository.joinRoles(partners)
        businessPartnerRepository.joinLegalForm(partners)

        val identifiers = partners.flatMap { it.identifiers }.toSet()
        fetchIdentifierDependencies(identifiers)

        val legalForms = partners.mapNotNull { it.legalForm }.toSet()
        legalFormRepository.joinCategories(legalForms)

        val addresses = partners.flatMap { it.addresses }.toSet()
        fetchAddressDependencies(addresses)

        val bankAccounts = partners.flatMap { it.bankAccounts }.toSet()
        bankAccountRepository.joinTrustScores(bankAccounts)

        return partners
    }

    private fun fetchAddressDependencies(addresses: Set<Address>): Set<Address> {
        addressRepository.joinContexts(addresses)
        addressRepository.joinTypes(addresses)
        addressRepository.joinVersions(addresses)
        addressRepository.joinAdminAreas(addresses)
        addressRepository.joinPostCodes(addresses)
        addressRepository.joinLocalities(addresses)
        addressRepository.joinPremises(addresses)
        addressRepository.joinPostalDeliveryPoints(addresses)
        addressRepository.joinThoroughfares(addresses)

        return addresses
    }

    private fun fetchIdentifierDependencies(identifiers: Set<Identifier>): Set<Identifier>{
        identifierRepository.joinType(identifiers)
        identifierRepository.joinStatus(identifiers)
        identifierRepository.joinIssuingBody(identifiers)

        return identifiers
    }


}