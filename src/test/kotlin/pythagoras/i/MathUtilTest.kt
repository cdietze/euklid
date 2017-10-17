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
package pythagoras.i

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests parts of the [MathUtil] class.
 */
class MathUtilTest {
    @Test
    fun testFloorDiv() {
        val nums = intArrayOf(-15, -10, -8, -2, 0, 2, 8, 10, 15)
        val vals = intArrayOf(-2, -1, -1, -1, 0, 0, 0, 1, 1)
        for (ii in nums.indices) {
            assertEquals(vals[ii], MathUtil.floorDiv(nums[ii], 10))
        }
    }
}
