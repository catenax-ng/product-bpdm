package com.catenax.gpdm.repository.entity

import com.catenax.gpdm.entity.BusinessPartner
import com.catenax.gpdm.entity.IdentifierStatus
import com.catenax.gpdm.entity.IdentifierType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface BusinessPartnerRepository : PagingAndSortingRepository<BusinessPartner, Long>{
    fun findByBpn(bpn: String) : BusinessPartner?

    fun findDistinctByBpnIn(bpns: Collection<String>): Set<BusinessPartner>

    fun findByUpdatedAtAfter(updatedAt: Date, pageable: Pageable): Page<BusinessPartner>

    @Query("SELECT DISTINCT i.partner FROM Identifier i WHERE i.type = ?1 AND i.value = ?2")
    fun findByIdentifierTypeAndValue(type: IdentifierType, idValue: String) : BusinessPartner?

    @Query("SELECT DISTINCT i.partner FROM Identifier i WHERE i.type.technicalKey = ?1 AND i.value in ?2")
    fun findByIdentifierTypeAndValues(type: String, values: Collection<String>) : Set<BusinessPartner>

    @Query("SELECT DISTINCT i.partner FROM Identifier i WHERE i.type = ?1 AND i.status = ?2")
    fun findByIdentifierTypeAndStatus(type: IdentifierType, status: IdentifierStatus) : Set<BusinessPartner>


}