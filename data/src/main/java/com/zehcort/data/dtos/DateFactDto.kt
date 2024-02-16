package com.zehcort.data.dtos

import com.zehcort.data.entities.remote.numbers.DateFact as DateFactEntity
import com.zehcort.domain.models.DateFact as DateFactDomain

fun DateFactEntity.toDomain(): DateFactDomain = DateFactDomain(
    text = text ?: "",
    year = year?.toString() ?: ""
)