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



package pythagoras.f

import kotlin.math.max
import kotlin.math.min

/**
 * Provides read-only access to a [CubicCurve].
 */
interface ICubicCurve : IShape {
    /** Returns the x-coordinate of the start of this curve.  */
    val x1: Float

    /** Returns the y-coordinate of the start of this curve.  */
    val y1: Float

    /** Returns the x-coordinate of the first control point.  */
    val ctrlX1: Float

    /** Returns the y-coordinate of the first control point.  */
    val ctrlY1: Float

    /** Returns the x-coordinate of the second control point.  */
    val ctrlX2: Float

    /** Returns the y-coordinate of the second control point.  */
    val ctrlY2: Float

    /** Returns the x-coordinate of the end of this curve.  */
    val x2: Float

    /** Returns the y-coordinate of the end of this curve.  */
    val y2: Float

    /** Returns a copy of the starting point of this curve.  */
    fun p1(): Point = Point(x1, y1)

    /** Returns a copy of the first control point of this curve.  */
    fun ctrlP1(): Point = Point(ctrlX1, ctrlY1)

    /** Returns a copy of the second control point of this curve.  */
    fun ctrlP2(): Point = Point(ctrlX2, ctrlY2)

    /** Returns a copy of the ending point of this curve.  */
    fun p2(): Point = Point(x2, y2)

    /** Returns the square of the flatness (maximum distance of a control point from the line
     * connecting the end points) of this curve.  */
    fun flatnessSq(): Float =
            CubicCurves.flatnessSq(x1, y1, ctrlX1, ctrlY1, ctrlX2, ctrlY2, x2, y2)

    /** Returns the flatness (maximum distance of a control point from the line connecting the end
     * points) of this curve.  */
    fun flatness(): Float =
            CubicCurves.flatness(x1, y1, ctrlX1, ctrlY1, ctrlX2, ctrlY2, x2, y2)

    /** Subdivides this curve and stores the results into `left` and `right`.  */
    fun subdivide(left: CubicCurve, right: CubicCurve) {
        CubicCurves.subdivide(this, left, right)
    }

    /** Returns a mutable copy of this curve.  */
    fun copy(): CubicCurve =
            CubicCurve(x1, y1, ctrlX1, ctrlY1, ctrlX2, ctrlY2, x2, y2)

    // Curves contain no space
    override val isEmpty: Boolean get() = true

    override fun contains(x: Float, y: Float): Boolean =
            Crossing.isInsideEvenOdd(Crossing.crossShape(this, x, y))

    override fun contains(x: Float, y: Float, width: Float, height: Float): Boolean {
        val cross = Crossing.intersectShape(this, x, y, width, height)
        return cross != Crossing.CROSSING && Crossing.isInsideEvenOdd(cross)
    }

    override fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean {
        val cross = Crossing.intersectShape(this, x, y, width, height)
        return cross == Crossing.CROSSING || Crossing.isInsideEvenOdd(cross)
    }

    override fun bounds(target: Rectangle): Rectangle {
        val rx1 = min(min(x1, x2), min(ctrlX1, ctrlX2))
        val ry1 = min(min(y1, y2), min(ctrlY1, ctrlY2))
        val rx2 = max(max(x1, x2), max(ctrlX1, ctrlX2))
        val ry2 = max(max(y1, y2), max(ctrlY1, ctrlY2))
        target.setBounds(rx1, ry1, rx2 - rx1, ry2 - ry1)
        return target
    }

    override fun pathIterator(transform: Transform?): PathIterator =
            Iterator(this, transform)

    override fun pathIterator(transform: Transform?, flatness: Float): PathIterator =
            FlatteningPathIterator(pathIterator(transform), flatness)

    /** An iterator over an [ICubicCurve].  */
    private class Iterator internal constructor(private val c: ICubicCurve, private val t: Transform?) : PathIterator {
        private var index: Int = 0

        override fun windingRule(): Int {
            return PathIterator.WIND_NON_ZERO
        }

        override val isDone: Boolean
            get() = index > 1

        override fun next() {
            index++
        }

        override fun currentSegment(coords: FloatArray): Int {
            if (isDone) {
                throw NoSuchElementException("Iterator out of bounds")
            }
            val type: Int
            val count: Int
            if (index == 0) {
                type = PathIterator.SEG_MOVETO
                coords[0] = c.x1
                coords[1] = c.y1
                count = 1
            } else {
                type = PathIterator.SEG_CUBICTO
                coords[0] = c.ctrlX1
                coords[1] = c.ctrlY1
                coords[2] = c.ctrlX2
                coords[3] = c.ctrlY2
                coords[4] = c.x2
                coords[5] = c.y2
                count = 3
            }
            t?.transform(coords, 0, coords, 0, count)
            return type
        }
    }

}
