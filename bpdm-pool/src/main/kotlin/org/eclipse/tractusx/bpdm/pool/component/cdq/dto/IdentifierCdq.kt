package org.eclipse.tractusx.bpdm.pool.component.cdq.dto

data class IdentifierCdq(
    val type: TypeKeyNameUrlCdq? = null,
    val value: String,
    val issuingBody: TypeKeyNameUrlCdq? = null,
    val status: TypeKeyNameCdq? = null
)