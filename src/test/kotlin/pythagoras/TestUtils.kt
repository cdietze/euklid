package pythagoras

import kotlin.math.abs
import kotlin.test.assertTrue

fun assertEquals(expected: Float, actual: Float, delta: Float, message: String? = null) {
    assertTrue(abs(expected - actual) <= delta, message)
}
