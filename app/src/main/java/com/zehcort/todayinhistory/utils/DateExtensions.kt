package com.zehcort.todayinhistory.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Long.toDate(): LocalDate = Instant.ofEpochMilli(this).atZone(ZoneId.of("UTC")).toLocalDate()