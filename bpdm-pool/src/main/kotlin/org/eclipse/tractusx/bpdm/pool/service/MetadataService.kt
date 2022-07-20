package org.eclipse.tractusx.bpdm.pool.service

import mu.KotlinLogging
import org.eclipse.tractusx.bpdm.common.dto.response.LegalFormResponse
import org.eclipse.tractusx.bpdm.common.dto.response.type.TypeKeyNameDto
import org.eclipse.tractusx.bpdm.common.dto.response.type.TypeKeyNameUrlDto
import org.eclipse.tractusx.bpdm.pool.dto.request.LegalFormRequest
import org.eclipse.tractusx.bpdm.pool.dto.response.PageResponse
import org.eclipse.tractusx.bpdm.pool.entity.*
import org.eclipse.tractusx.bpdm.pool.exception.BpdmAlreadyExists
import org.eclipse.tractusx.bpdm.pool.repository.IdentifierStatusRepository
import org.eclipse.tractusx.bpdm.pool.repository.IdentifierTypeRepository
import org.eclipse.tractusx.bpdm.pool.repository.IssuingBodyRepository
import org.eclipse.tractusx.bpdm.pool.repository.LegalFormRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service for fetching and creating metadata entities
 */
@Service
class MetadataService(
    val identifierTypeRepository: IdentifierTypeRepository,
    val issuingBodyRepository: IssuingBodyRepository,
    val legalFormRepository: LegalFormRepository,
    val identifierStatusRepository: IdentifierStatusRepository
) {

    private val logger = KotlinLogging.logger { }

    @Transactional
    fun createIdentifierType(type: TypeKeyNameUrlDto<String>): TypeKeyNameUrlDto<String> {
        if (identifierTypeRepository.findByTechnicalKey(type.technicalKey) != null)
            throw BpdmAlreadyExists(IdentifierType::class.simpleName!!, type.technicalKey)

        logger.info { "Create new Identifier-Type with key ${type.technicalKey} and name ${type.name}" }
        return identifierTypeRepository.save(IdentifierType(type.name, type.url, type.technicalKey)).toDto()
    }

    fun getIdentifierTypes(pageRequest: Pageable): PageResponse<TypeKeyNameUrlDto<String>> {
        val page = identifierTypeRepository.findAll(pageRequest)
        return page.toDto( page.content.map { it.toDto() } )
    }

    @Transactional
    fun createIdentifierStatus(status: TypeKeyNameDto<String>): TypeKeyNameDto<String> {
        if (identifierStatusRepository.findByTechnicalKey(status.technicalKey) != null)
            throw BpdmAlreadyExists(IdentifierStatus::class.simpleName!!, status.technicalKey)

        logger.info { "Create new Identifier-Status with key ${status.technicalKey} and name ${status.name}" }
        return identifierStatusRepository.save(IdentifierStatus(status.name, status.technicalKey)).toDto()
    }

    fun getIdentifierStati(pageRequest: Pageable): PageResponse<TypeKeyNameDto<String>> {
        val page = identifierStatusRepository.findAll(pageRequest)
        return page.toDto( page.content.map { it.toDto() } )
    }

    @Transactional
    fun createIssuingBody(type: TypeKeyNameUrlDto<String>): TypeKeyNameUrlDto<String> {
        if (issuingBodyRepository.findByTechnicalKey(type.technicalKey) != null)
            throw BpdmAlreadyExists(IssuingBody::class.simpleName!!, type.technicalKey)

        logger.info { "Create new Issuing-Body with key ${type.technicalKey} and name ${type.name}" }
        return issuingBodyRepository.save(IssuingBody(type.name, type.url, type.technicalKey)).toDto()
    }

    fun getIssuingBodies(pageRequest: Pageable): PageResponse<TypeKeyNameUrlDto<String>> {
        val page = issuingBodyRepository.findAll(pageRequest)
        return page.toDto( page.content.map { it.toDto() } )
    }

    @Transactional
    fun createLegalForm(request: LegalFormRequest): LegalFormResponse {
        if (legalFormRepository.findByTechnicalKey(request.technicalKey) != null)
            throw BpdmAlreadyExists(LegalForm::class.simpleName!!, request.name)

        logger.info { "Create new Legal-Form with key ${request.technicalKey}, name ${request.name} and ${request.category.size} categories" }
        val categories = request.category.map { LegalFormCategory(it.name, it.url) }.toSet()
        val legalForm = LegalForm(request.name, request.url, request.language, request.mainAbbreviation, categories, request.technicalKey)

        return legalFormRepository.save(legalForm).toDto()
    }

    fun getLegalForms(pageRequest: Pageable): PageResponse<LegalFormResponse> {
        val page = legalFormRepository.findAll(pageRequest)
        return page.toDto( page.content.map { it.toDto() } )
    }
}