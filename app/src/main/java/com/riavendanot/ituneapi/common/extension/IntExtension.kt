package com.riavendanot.ituneapi.common.extension

fun Int?.value(defaultValues: Int = 0) = this ?: defaultValues