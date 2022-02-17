package com.catenax.gpdm.adapter.cdq.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "bpdm.adapter.cdq")
@ConstructorBinding
class CdqAdapterConfigProperties (
    val host: String = "https://api.cdq.com",
    val api: String = "data-exchange/rest/v4",
    val storage: String = "8888865cc59a3b4aa079b8e00313cf53",
    val datasource: String = "61c096613b4b824755a62641",
    val apiKey: String = "",
    val timestampKey: String = "last-import",
    val importLimit: Int = 100
        )