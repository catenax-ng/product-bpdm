package com.catenax.gpdm.repository

import com.catenax.gpdm.entity.LegalFormCategory
import org.springframework.data.repository.PagingAndSortingRepository

interface LegalFormCategoryRepository : PagingAndSortingRepository<LegalFormCategory, Long> {
    fun findByName(name: String): LegalFormCategory?
}