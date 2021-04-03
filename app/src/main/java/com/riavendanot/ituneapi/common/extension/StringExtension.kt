package com.riavendanot.ituneapi.common.extension

import com.riavendanot.ituneapi.common.constant.StringConstant.EMPTY_STRING

fun String?.value(default: String = EMPTY_STRING) = this ?: default