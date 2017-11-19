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

import kotlin.math.max
import kotlin.math.min

/**
 * Provides read-only access to a [QuadCurve].
 */
interface IQuadCurve : IShape {
    /** Returns the x-coordinate of the start of this curve.  */
    val x1: Float

    /** Returns the y-coordinate of the start of this curve.  */
    val y1: Float

    /** Returns the x-coordinate of the control point.  */
    val ctrlX: Float

    /** Returns the y-coordinate of the control point.  */
    val ctrlY: Float

    /** Returns the x-coordinate of the end of this curve.  */
    val x2: Float

    /** Returns the y-coordinate of the end of this curve.  */
    val y2: Float

    /** Returns a copy of the starting point of this curve.  */
    fun p1(): Point = Point(x1, y1)

    /** Returns a copy of the control point of this curve.  */
    fun ctrlP(): Point = Point(ctrlX, ctrlY)

    /** Returns a copy of the ending point of this curve.  */
    fun p2(): Point = Point(x2, y2)

    /** Returns the square of the flatness (maximum distance of a control point from the line
     * connecting the end points) of this curve.  */
    fun flatnessSq(): Float = Lines.pointSegDistSq(ctrlX, ctrlY, x1, y1, x2, y2)

    /** Returns the flatness (maximum distance of a control point from the line connecting the end
     * points) of this curve.  */
    fun flatness(): Float = Lines.pointSegDist(ctrlX, ctrlY, x1, y1, x2, y2)

    /** Subdivides this curve and stores the results into `left` and `right`.  */
    fun subdivide(left: QuadCurve, right: QuadCurve) =
            QuadCurves.subdivide(this, left, right)

    /** Returns a mutable copy of this curve.  */
    fun copy(): QuadCurve =
            QuadCurve(x1, y1, ctrlX, ctrlY, x2, y2)

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
        val x1 = x1
        val y1 = y1
        val x2 = x2
        val y2 = y2
        val ctrlx = ctrlX
        val ctrly = ctrlY
        val rx0 = min(min(x1, x2), ctrlx)
        val ry0 = min(min(y1, y2), ctrly)
        val rx1 = max(max(x1, x2), ctrlx)
        val ry1 = max(max(y1, y2), ctrly)
        target.setBounds(rx0, ry0, rx1 - rx0, ry1 - ry0)
        return target
    }

    override fun pathIterator(transform: Transform?): PathIterator {
        return Iterator(this, transform)
    }

    override fun pathIterator(transform: Transform?, flatness: Float): PathIterator {
        return FlatteningPathIterator(pathIterator(transform), flatness)
    }

    /** An iterator over an [IQuadCurve].  */
    private class Iterator internal constructor(private val c: IQuadCurve, private val t: Transform?) : PathIterator {
        private var index: Int = 0

        override fun windingRule(): Int = PathIterator.WIND_NON_ZERO

        override val isDone: Boolean get() = index > 1

        override fun next() {
            index++
        }

        override fun currentSegment(coords: FloatArray): Int {
            require(!isDone, { -> "Iterator out of bounds" })
            val type: Int
            val count: Int
            if (index == 0) {
                type = PathIterator.SEG_MOVETO
                coords[0] = c.x1
                coords[1] = c.y1
                count = 1
            } else {
                type = PathIterator.SEG_QUADTO
                coords[0] = c.ctrlX
                coords[1] = c.ctrlY
                coords[2] = c.x2
                coords[3] = c.y2
                count = 2
            }
            t?.transform(coords, 0, coords, 0, count)
            return type
        }
    }
}
