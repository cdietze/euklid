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



package euklid.i

/**
 * Math utility methods.
 */
object MathUtil {
    /**
     * Clamps the supplied `value` to between `low` and `high` (both inclusive).
     */
    fun clamp(value: Int, low: Int, high: Int): Int {
        if (value < low) return low
        if (value > high) return high
        return value
    }

    /**
     * Computes the floored division `dividend/divisor` which is useful when dividing
     * potentially negative numbers into bins.
     *
     *  For example, the following numbers `floorDiv` 10 are:
     * <pre>
     * -15 -10 -8 -2 0 2 8 10 15
     * -2  -1 -1 -1 0 0 0  1  1
    </pre> *
     */
    fun floorDiv(dividend: Int, divisor: Int): Int {
        val numpos = dividend >= 0
        val denpos = divisor >= 0
        if (numpos == denpos) return dividend / divisor
        return if (denpos) (dividend - divisor + 1) / divisor else (dividend - divisor - 1) / divisor
    }
}
