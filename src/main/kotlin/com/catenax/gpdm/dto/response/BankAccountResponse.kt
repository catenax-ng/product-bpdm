package com.catenax.gpdm.dto.response

import com.catenax.gpdm.dto.response.type.TypeKeyNameDto
import com.neovisionaries.i18n.CurrencyCode

data class BankAccountResponse (
    val trustScores: Collection<Float>,
    val currency: TypeKeyNameDto<CurrencyCode>,
    val internationalBankAccountIdentifier: String,
    val internationalBankIdentifier: String,
    val nationalBankAccountIdentifier: String,
    val nationalBankIdentifier: String,
        )