package com.riavendanot.ituneapi.common

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `Get value from String not null`(){
        val testString = "test"
        assertEquals(testString.value(), "test")
    }

    @Test
    fun `Get quotation when String is null`() {
        val testString: String? = null
        assertEquals(testString.value(), "")
    }
}