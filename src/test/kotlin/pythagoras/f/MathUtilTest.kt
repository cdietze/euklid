/*
 * Copyright 2017 The Pythagoras.kt Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pythagoras.f

import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Tests parts of the [MathUtil] class.
 */
class MathUtilTest {

    @Test
    fun testLerpa() {
        assertEquals(MathUtil.lerpa(PI4, -PI4, 0.25f), PI8, MathUtil.EPSILON)
        assertEquals(MathUtil.lerpa(PI4, -PI4, 0.75f), -PI8, MathUtil.EPSILON)
        assertEquals(MathUtil.lerpa(-PI4, PI4, 0.25f), -PI8, MathUtil.EPSILON)
        assertEquals(MathUtil.lerpa(-PI4, PI4, 0.75f), PI8, MathUtil.EPSILON)
        // make sure we lerp the shortest route around the circle
        assertEquals(MathUtil.lerpa(3 * PI4, PI4, 0.5f), PI2, MathUtil.EPSILON)
        assertEquals(MathUtil.lerpa(PI4, 3 * PI4, 0.5f), PI2, MathUtil.EPSILON)
        assertEquals(MathUtil.lerpa(-3 * PI4, -PI4, 0.5f), -PI2, MathUtil.EPSILON)
        assertEquals(MathUtil.lerpa(-PI4, -3 * PI4, 0.5f), -PI2, MathUtil.EPSILON)

        assertEquals(MathUtil.lerpa(3 * PI4, -3 * PI4, 0.5f), -PI, MathUtil.EPSILON)
    }

    @Test
    fun testToString() {
        Assert.assertEquals("+1.0", MathUtil.toString(1f))
        Assert.assertEquals("-1.0", MathUtil.toString(-1f))
        Assert.assertEquals("+1.1", MathUtil.toString(1.1f))
        Assert.assertEquals("-1.1", MathUtil.toString(-1.1f))
        Assert.assertEquals("+3.141", MathUtil.toString(MathUtil.PI))
        Assert.assertEquals("-3.141", MathUtil.toString(-MathUtil.PI))

        MathUtil.setToStringDecimalPlaces(5)
        Assert.assertEquals("+1.0", MathUtil.toString(1f))
        Assert.assertEquals("-1.0", MathUtil.toString(-1f))
        Assert.assertEquals("+1.1", MathUtil.toString(1.1f))
        Assert.assertEquals("-1.1", MathUtil.toString(-1.1f))
        Assert.assertEquals("+3.14159", MathUtil.toString(MathUtil.PI))
        Assert.assertEquals("-3.14159", MathUtil.toString(-MathUtil.PI))
    }

    companion object {
        val PI = MathUtil.PI
        val PI2 = MathUtil.PI / 2
        val PI4 = MathUtil.PI / 4
        val PI8 = MathUtil.PI / 8
    }
}
