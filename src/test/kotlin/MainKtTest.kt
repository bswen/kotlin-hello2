import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun sum() {
        val expected = 42
        assertEquals(expected, sum(40, 2))
    }
}