/*
 * Copyright 2017 The Pythagoras-kt Authors
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

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Point-related utility methods.
 */
object Points {
    /**
     * Returns the squared Euclidian distance between the specified two points.
     */
    fun distanceSq(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        var x2 = x2
        var y2 = y2
        x2 -= x1
        y2 -= y1
        return x2 * x2 + y2 * y2
    }

    /**
     * Returns the Euclidian distance between the specified two points, truncated to the nearest
     * integer.
     */
    fun distance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return sqrt(distanceSq(x1, y1, x2, y2).toDouble()).toInt()
    }

    /**
     * Returns the Manhattan distance between the specified two points.
     */
    fun manhattanDistance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return abs(x2 - x1) + abs(y2 - y1)
    }

    /**
     * Returns a string describing the supplied point, of the form `+x+y`, `+x-y`,
     * `-x-y`, etc.
     */
    fun pointToString(x: Int, y: Int): String {
        val buf = StringBuilder()
        if (x >= 0) buf.append("+")
        buf.append(x)
        if (y >= 0) buf.append("+")
        buf.append(y)
        return buf.toString()
    }
}
