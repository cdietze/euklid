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

import kotlin.math.min
import kotlin.math.sqrt

/**
 * Provides most of the implementation of [IRoundRectangle], obtaining the framing rectangle
 * from the derived class.
 */
abstract class AbstractRoundRectangle : RectangularShape(), IRoundRectangle {
    override // from interface IRoundRectangle
    fun clone(): RoundRectangle {
        return RoundRectangle(x, y, width, height,
                arcWidth, arcHeight)
    }

    override // from interface IShape
    fun contains(x: Float, y: Float): Boolean {
        var px = x
        var py = y
        if (isEmpty) return false

        val rx1 = this.x
        val ry1 = this.y
        val rx2 = rx1 + width
        val ry2 = ry1 + height
        if (px < rx1 || px >= rx2 || py < ry1 || py >= ry2) {
            return false
        }

        val aw = arcWidth / 2f
        val ah = arcHeight / 2f
        val cx: Float
        val cy: Float
        if (px < rx1 + aw) {
            cx = rx1 + aw
        } else if (px > rx2 - aw) {
            cx = rx2 - aw
        } else {
            return true
        }

        if (py < ry1 + ah) {
            cy = ry1 + ah
        } else if (py > ry2 - ah) {
            cy = ry2 - ah
        } else {
            return true
        }

        px = (px - cx) / aw
        py = (py - cy) / ah
        return px * px + py * py <= 1f
    }

    override // from interface IShape
    fun contains(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (isEmpty || width <= 0f || height <= 0f) return false
        val rx1 = x
        val ry1 = y
        val rx2 = x + width
        val ry2 = y + height
        return contains(rx1, ry1) && contains(rx2, ry1) && contains(rx2, ry2) && contains(rx1, ry2)
    }

    override // from interface IShape
    fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (isEmpty || width <= 0f || height <= 0f) return false

        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        val rx1 = x
        val ry1 = y
        val rx2 = x + width
        val ry2 = y + height
        if (rx2 < x1 || x2 < rx1 || ry2 < y1 || y2 < ry1) {
            return false
        }

        val cx = (x1 + x2) / 2f
        val cy = (y1 + y2) / 2f
        val nx = if (cx < rx1) rx1 else if (cx > rx2) rx2 else cx
        val ny = if (cy < ry1) ry1 else if (cy > ry2) ry2 else cy
        return contains(nx, ny)
    }

    override // from interface IShape
    fun pathIterator(transform: Transform?): PathIterator {
        return Iterator(this, transform)
    }

    /** Provides an iterator over an [IRoundRectangle].  */
    protected class Iterator internal constructor(rr: IRoundRectangle, private val t: Transform?) : PathIterator {
        private val x: Float = rr.x
        private val y: Float = rr.y
        private val width: Float = rr.width
        private val height: Float = rr.height
        private val aw: Float
        private val ah: Float
        private var index: Int = 0

        init {
            this.aw = min(width, rr.arcWidth)
            this.ah = min(height, rr.arcHeight)
            if (width < 0f || height < 0f || aw < 0f || ah < 0f) {
                index = POINTS.size
            }
        }

        override fun windingRule(): Int {
            return PathIterator.WIND_NON_ZERO
        }

        override val isDone: Boolean
            get() = index > POINTS.size

        override fun next() {
            index++
        }

        override fun currentSegment(coords: FloatArray): Int {
            if (isDone) {
                throw NoSuchElementException("Iterator out of bounds")
            }
            if (index == POINTS.size) {
                return PathIterator.SEG_CLOSE
            }
            var j = 0
            val p = POINTS[index]
            var i = 0
            while (i < p.size) {
                coords[j++] = x + p[i + 0] * width + p[i + 1] * aw
                coords[j++] = y + p[i + 2] * height + p[i + 3] * ah
                i += 4
            }
            t?.transform(coords, 0, coords, 0, j / 2)
            return TYPES[index]
        }
    }

    companion object {

        // the path for round corners is generated the same way as for Ellipse

        /** The segment types correspond to points array.  */
        protected val TYPES = intArrayOf(PathIterator.SEG_MOVETO, PathIterator.SEG_LINETO, PathIterator.SEG_CUBICTO, PathIterator.SEG_LINETO, PathIterator.SEG_CUBICTO, PathIterator.SEG_LINETO, PathIterator.SEG_CUBICTO, PathIterator.SEG_LINETO, PathIterator.SEG_CUBICTO)

        /** The coefficient to calculate control points of Bezier curves.  */
        protected val U = 0.5f - 2f / 3f * (sqrt(2f) - 1f)

        /** The points coordinates calculation table.  */
        protected val POINTS = arrayOf(floatArrayOf(0f, 0.5f, 0f, 0f), // MOVETO
                floatArrayOf(1f, -0.5f, 0f, 0f), // LINETO
                floatArrayOf(1f, -U, 0f, 0f, 1f, 0f, 0f, U, 1f, 0f, 0f, 0.5f), // CUBICTO
                floatArrayOf(1f, 0f, 1f, -0.5f), // LINETO
                floatArrayOf(1f, 0f, 1f, -U, 1f, -U, 1f, 0f, 1f, -0.5f, 1f, 0f), // CUBICTO
                floatArrayOf(0f, 0.5f, 1f, 0f), // LINETO
                floatArrayOf(0f, U, 1f, 0f, 0f, 0f, 1f, -U, 0f, 0f, 1f, -0.5f), // CUBICTO
                floatArrayOf(0f, 0f, 0f, 0.5f), // LINETO
                floatArrayOf(0f, 0f, 0f, U, 0f, U, 0f, 0f, 0f, 0.5f, 0f, 0f))// CUBICTO
    }
}
