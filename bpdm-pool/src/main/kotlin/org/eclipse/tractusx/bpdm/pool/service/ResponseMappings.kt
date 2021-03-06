package org.eclipse.tractusx.bpdm.pool.service

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.CurrencyCode
import com.neovisionaries.i18n.LanguageCode
import org.eclipse.tractusx.bpdm.common.model.ClassificationType
import org.eclipse.tractusx.bpdm.common.model.NamedType
import org.eclipse.tractusx.bpdm.common.model.NamedUrlType
import org.eclipse.tractusx.bpdm.pool.dto.GeoCoordinateDto
import org.eclipse.tractusx.bpdm.pool.dto.response.*
import org.eclipse.tractusx.bpdm.pool.dto.response.type.TypeKeyNameDto
import org.eclipse.tractusx.bpdm.pool.dto.response.type.TypeKeyNameUrlDto
import org.eclipse.tractusx.bpdm.pool.dto.response.type.TypeNameUrlDto
import org.eclipse.tractusx.bpdm.pool.entity.*
import org.springframework.data.domain.Page


fun <S, T> Page<S>.toDto(dtoContent: Collection<T>) : PageResponse<T> {
    return PageResponse(this.totalElements, this.totalPages, this.number, this.numberOfElements, dtoContent)
}

fun <T: NamedUrlType> T.toDto(): TypeKeyNameUrlDto<T> {
    return TypeKeyNameUrlDto(this, getTypeName(), getUrl())
}

fun <T: NamedType> T.toDto(): TypeKeyNameDto<T> {
    return TypeKeyNameDto(this, getTypeName())
}

fun LanguageCode.toDto(): TypeKeyNameDto<LanguageCode> {
    return TypeKeyNameDto(this, getName())
}

fun CountryCode.toDto(): TypeKeyNameDto<CountryCode> {
    return TypeKeyNameDto(this, getName())
}

fun CurrencyCode.toDto(): TypeKeyNameDto<CurrencyCode> {
    return TypeKeyNameDto(this, getName())
}

fun BusinessPartner.toSearchDto(score: Float): BusinessPartnerSearchResponse {
    return BusinessPartnerSearchResponse(score, this.toDto())
}

fun BusinessPartner.toDto(): BusinessPartnerResponse {
    return BusinessPartnerResponse(
        bpn,
        identifiers.map { it.toDto() },
        names.map { it.toDto() },
        legalForm?.toDto(),
        stati.maxWithOrNull(compareBy { it.validFrom })?.toDto(),
        classification.map { it.toDto() },
        types.map { it.toDto() },
        bankAccounts.map { it.toDto() },
        roles.map { it.toDto() },
        startNodeRelations.map { it.toDto() }.plus(endNodeRelations.map { it.toDto() }),
        currentness
    )
}

fun Identifier.toDto(): IdentifierResponse {
    return IdentifierResponse(uuid, value, type.toDto(), issuingBody?.toDto(), status?.toDto())
}

fun IdentifierType.toDto(): TypeKeyNameUrlDto<String> {
    return TypeKeyNameUrlDto(technicalKey, name, url)
}

fun IdentifierStatus.toDto(): TypeKeyNameDto<String> {
    return TypeKeyNameDto(technicalKey, name)
}

fun IssuingBody.toDto(): TypeKeyNameUrlDto<String> {
    return TypeKeyNameUrlDto(technicalKey, name, url)
}

fun Name.toDto(): NameResponse {
    return NameResponse(uuid, value, shortName, type.toDto(), language.toDto())
}

fun LegalForm.toDto(): LegalFormResponse {
    return LegalFormResponse(technicalKey, name, url, mainAbbreviation, language.toDto(), categories.map { it.toDto() })
}

fun LegalFormCategory.toDto(): TypeNameUrlDto {
    return TypeNameUrlDto(name, url)
}

fun BusinessStatus.toDto(): BusinessStatusResponse {
    return BusinessStatusResponse(uuid, officialDenotation, validFrom, validUntil, type.toDto())
}

fun Role.toDto(): TypeKeyNameDto<String> {
    return TypeKeyNameDto(technicalKey, name)
}

fun Address.toDto(): AddressResponse {
    return AddressResponse(
        uuid,
        bpn,
        version.toDto(),
        careOf,
        contexts,
        country.toDto(),
        administrativeAreas.map { it.toDto() },
        postCodes.map { it.toDto() },
        localities.map { it.toDto() },
        thoroughfares.map { it.toDto() },
        premises.map { it.toDto() },
        postalDeliveryPoints.map { it.toDto() },
        geoCoordinates?.toDto(),
        types.map { it.toDto() }
    )
}

fun Address.toDtoWithReference(): AddressWithReferenceResponse {
    return AddressWithReferenceResponse(
        toDto(),
        partner?.bpn,
        site?.bpn
    )
}

fun Site.toDto(): SiteResponse {
    return SiteResponse(
        bpn,
        name,
        addresses.map { it.toDto() }
    )
}

fun Site.toDtoWithReference(): SiteWithReferenceResponse {
    return SiteWithReferenceResponse(
        toDto(),
        partner.bpn
    )
}

fun AdministrativeArea.toDto(): AdministrativeAreaResponse {
    return AdministrativeAreaResponse(uuid, value, shortName, fipsCode, type.toDto(), language.toDto())
}


fun PostCode.toDto(): PostCodeResponse {
    return PostCodeResponse(uuid, value, type.toDto())
}

fun Locality.toDto(): LocalityResponse {
    return LocalityResponse(uuid, value, shortName, localityType.toDto(), language.toDto())
}

fun Thoroughfare.toDto(): ThoroughfareResponse {
    return ThoroughfareResponse(uuid, value, name, shortName, number, direction, type.toDto(), language.toDto())
}

fun Premise.toDto(): PremiseResponse {
    return PremiseResponse(uuid, value, shortName, number, type.toDto(), language.toDto())
}

fun PostalDeliveryPoint.toDto(): PostalDeliveryPointResponse {
    return PostalDeliveryPointResponse(uuid, value, shortName, number, type.toDto(), language.toDto())
}

fun AddressVersion.toDto(): AddressVersionResponse {
    return AddressVersionResponse(characterSet.toDto(), language.toDto())
}

fun GeographicCoordinate.toDto(): GeoCoordinateDto {
    return GeoCoordinateDto(longitude, latitude, altitude)
}

fun Classification.toDto(): ClassificationResponse {
    return ClassificationResponse(uuid, value, code, type?.toDto())
}

fun ClassificationType.toDto(): TypeNameUrlDto {
    return TypeNameUrlDto(name, url)
}

fun Relation.toDto(): RelationResponse {
    return RelationResponse(uuid, relationClass.toDto(), type.toDto(), startNode.bpn, endNode.bpn, startedAt, endedAt)
}

fun BankAccount.toDto(): BankAccountResponse {
    return BankAccountResponse(
        uuid, trustScores, currency.toDto(), internationalBankAccountIdentifier, internationalBankIdentifier,
        nationalBankAccountIdentifier, nationalBankIdentifier
    )
}

fun SyncRecord.toDto(): SyncResponse {
    return SyncResponse(type, status, count, progress, errorDetails, startedAt, finishedAt)
}

fun PartnerChangelogEntry.toDto(): ChangelogEntryResponse {
    return ChangelogEntryResponse(bpn, changelogType, createdAt)
}
