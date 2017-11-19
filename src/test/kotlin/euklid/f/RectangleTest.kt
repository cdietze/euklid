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

class RectangleTest {
    @Test
    fun testPointRectDistance() {
        testPointRectDistance(0f, Rectangle(0f, 0f, 10f, 10f), Point(0f, 0f)) // edge
        testPointRectDistance(0f, Rectangle(0f, 0f, 10f, 10f), Point(5f, 5f)) // interior
        testPointRectDistance(5f, Rectangle(0f, 0f, 10f, 10f), Point(5f, 15f)) // exterior
    }

    protected fun testPointRectDistance(expected: Float, r: IRectangle, p: Point) {
        assertEqualsWithDelta(expected, Rectangles.pointRectDistance(r, p), MathUtil.EPSILON)
    }
}
