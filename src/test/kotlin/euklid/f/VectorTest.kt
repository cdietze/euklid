/*
 * Copyright 2017 The Euklid Authors
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
package euklid.f

import org.junit.Test
import euklid.assertEqualsWithDelta

/**
 * Tests aspects of the [Vector] class.
 */
class VectorTest {
    @Test
    fun testFromPolar() {
        var length = 0.05f
        while (length < 5) {
            // stay away from -PI and PI because the signs might be flipped
            var theta = -MathUtil.PI + 0.05f
            while (theta < MathUtil.PI) {
                val v = Vectors.fromPolar(length, theta)
                assertEqualsWithDelta(length, v.length(), MathUtil.EPSILON)
                assertEqualsWithDelta(theta, v.angle(), MathUtil.EPSILON)
                theta += 0.05f
            }
            length += 0.05f
        }
    }

    @Test
    fun testSetLength() {
        var length = 0.05f
        while (length < 5) {
            // stay away from -PI and PI because the signs might be flipped
            var theta = -MathUtil.PI + 0.05f
            while (theta < MathUtil.PI) {
                val v = Vectors.fromPolar(length, theta)
                v.setLength(10f)
                // make sure setting length actually sets the length
                assertEqualsWithDelta(10f, v.length(), MathUtil.EPSILON)
                // make sure setting length doesn't bork angle
                assertEqualsWithDelta(theta, v.angle(), MathUtil.EPSILON)
                theta += 0.05f
            }
            length += 0.05f
        }
    }

    @Test
    fun testSetAngle() {
        var length = 0.05f
        while (length < 5) {
            // stay away from -PI and PI because the signs might be flipped
            var theta = 0.05f
            while (theta < MathUtil.PI) {
                val v = Vectors.fromPolar(length, theta)
                v.setAngle(MathUtil.PI - theta)
                // make sure setting angle actually sets the angle
                assertEqualsWithDelta(MathUtil.PI - theta, v.angle(), MathUtil.EPSILON)
                // make sure setting length doesn't bork length
                assertEqualsWithDelta(length, v.length(), MathUtil.EPSILON)
                theta += 0.05f
            }
            length += 0.05f
        }
    }
}
