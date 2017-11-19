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
import kotlin.test.assertEquals
import kotlin.test.fail

class AreaTest {
    @Test
    fun areaWithPath() {
        val path = Path()
        path.moveTo(0f, 0f)
        for (i in 1..8) {
            path.lineTo(2f * i, 3f * i)
        }
        val areaWithNinePoints = Area(path)
        path.closePath()
        val areaWithNinePointsAndClose = Area(path)
        assertEquals(areaWithNinePoints, areaWithNinePointsAndClose)

        path.reset()
        path.moveTo(0f, 0f)
        for (i in 1..10) {
            path.lineTo(2f * i, 3f * i)
        }
        val areaWithElevenPoints = Area(path)
        path.closePath()
        val areaWithElevenPointsAndClose = Area(path)
        assertEquals(areaWithElevenPoints, areaWithElevenPointsAndClose)

        path.reset()
        path.moveTo(0f, 0f)
        for (i in 1..9) {
            path.lineTo(2f * i, 3f * i)
        }
        // this used to ArrayIndexOutOfBoundsException
        val areaWithTenPoints = Area(path)
        path.closePath()
        val areaWithTenPointsAndClose = Area(path)
        assertEquals(areaWithTenPoints, areaWithTenPointsAndClose)
    }

    protected fun assertEquals(one: Area, two: Area) {
        val iter1 = one.pathIterator(IdentityTransform)
        val iter2 = two.pathIterator(IdentityTransform)
        val coords1 = FloatArray(2)
        val coords2 = FloatArray(2)
        while (!iter1.isDone) {
            if (iter2.isDone) fail("$two path shorter than $one")
            val seg1 = iter1.currentSegment(coords1)
            val seg2 = iter2.currentSegment(coords2)
            assertEquals(seg1.toLong(), seg2.toLong(), "Same path segment")
            assertEqualsWithDelta(coords1[0], coords2[0], MathUtil.EPSILON, "Same x coord")
            assertEqualsWithDelta(coords1[1], coords2[1], MathUtil.EPSILON, "Same y coord")
            iter1.next()
            iter2.next()
        }
        if (!iter2.isDone) fail("$one path shorter than $two")
    }
}
