package com.riavendanot.ituneapi.common.mapper

abstract class Transform<R, E> {

    abstract fun transform(value: R): E
    fun transformCollection(values: List<R>): List<E> = values.map { response -> transform(response) }
}