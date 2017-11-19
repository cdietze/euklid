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
 * Provides read-only access to a [Rectangle].
 */
interface IRectangle : IRectangularShape {

    /** Returns a copy of this rectangle's upper-left corner.  */
    fun location(): Point = location(Point())

    /** Initializes the supplied point with this rectangle's upper-left corner.
     * @return the supplied point.
     */
    fun location(target: Point): Point = target.set(x, y)

    /** Returns a copy of this rectangle's size.  */
    fun size(): Dimension = size(Dimension())

    /** Initializes the supplied dimension with this rectangle's size.
     * @return the supplied dimension.
     */
    fun size(target: Dimension): Dimension = target.setSize(width, height)

    /** Returns the intersection of the specified rectangle and this rectangle (i.e. the largest
     * rectangle contained in both this and the specified rectangle).  */
    fun intersection(rx: Float, ry: Float, rw: Float, rh: Float): Rectangle {
        val x1 = max(x, rx)
        val y1 = max(y, ry)
        val x2 = min(maxX, rx + rw)
        val y2 = min(maxY, ry + rh)
        return Rectangle(x1, y1, x2 - x1, y2 - y1)
    }

    /** Returns the intersection of the supplied rectangle and this rectangle (i.e. the largest
     * rectangle contained in both this and the supplied rectangle).  */
    fun intersection(r: IRectangle): Rectangle = intersection(r.x, r.y, r.width, r.height)

    /** Returns the union of the supplied rectangle and this rectangle (i.e. the smallest rectangle
     * that contains both this and the supplied rectangle).  */
    fun union(r: IRectangle): Rectangle {
        val rect = Rectangle(this)
        rect.add(r)
        return rect
    }

    /** Returns true if the specified line segment intersects this rectangle.  */
    fun intersectsLine(x1: Float, y1: Float, x2: Float, y2: Float): Boolean =
            Lines.lineIntersectsRect(x1, y1, x2, y2, x, y, width, height)

    /** Returns true if the supplied line segment intersects this rectangle.  */
    fun intersectsLine(l: ILine): Boolean = intersectsLine(l.x1, l.y1, l.x2, l.y2)

    /** Returns a set of flags indicating where the specified point lies in relation to the bounds
     * of this rectangle. See [.OUT_LEFT], etc.  */
    fun outcode(px: Float, py: Float): Int {
        var code = 0
        when {
            width <= 0 -> code = code or (IRectangle.OUT_LEFT or IRectangle.OUT_RIGHT)
            px < x -> code = code or IRectangle.OUT_LEFT
            px > maxX -> code = code or IRectangle.OUT_RIGHT
        }
        when {
            height <= 0 -> code = code or (IRectangle.OUT_TOP or IRectangle.OUT_BOTTOM)
            py < y -> code = code or IRectangle.OUT_TOP
            py > maxY -> code = code or IRectangle.OUT_BOTTOM
        }
        return code
    }

    /** Returns a set of flags indicating where the supplied point lies in relation to the bounds of
     * this rectangle. See [.OUT_LEFT], etc.  */
    fun outcode(point: XY): Int = outcode(point.x, point.y)

    /** Returns a mutable copy of this rectangle.  */
    fun copy(x: Float = this.x, y: Float = this.y, width: Float = this.width, height: Float = this.height): Rectangle

    override fun contains(x: Float, y: Float): Boolean {
        var px = x
        var py = y
        if (isEmpty) return false

        if (px < this.x || py < this.y) return false

        px -= this.x
        py -= this.y
        return px <= width && py <= height
    }

    override fun contains(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (isEmpty) return false

        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        return x1 <= x && x + width <= x2 && y1 <= y && y + height <= y2
    }

    override fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (isEmpty) return false

        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        return x + width > x1 && x < x2 && y + height > y1 && y < y2
    }

    override fun pathIterator(transform: Transform?): PathIterator = Iterator(this, transform)

    override fun pathIterator(transform: Transform?, flatness: Float): PathIterator = Iterator(this, transform)

    /** An iterator over an [IRectangle].  */
    private class Iterator internal constructor(r: IRectangle, private val t: Transform?) : PathIterator {
        private val x: Float = r.x
        private val y: Float = r.y
        private val width: Float = r.width
        private val height: Float = r.height

        /** The current segment index.  */
        private var index: Int = 0

        init {
            if (width < 0f || height < 0f) {
                index = 6
            }
        }

        override fun windingRule(): Int = PathIterator.WIND_NON_ZERO

        override val isDone: Boolean
            get() = index > 5

        override fun next() {
            index++
        }

        override fun currentSegment(coords: FloatArray): Int {
            if (isDone) {
                throw NoSuchElementException("Iterator out of bounds")
            }
            if (index == 5) {
                return PathIterator.SEG_CLOSE
            }
            val type: Int
            if (index == 0) {
                type = PathIterator.SEG_MOVETO
                coords[0] = x
                coords[1] = y
            } else {
                type = PathIterator.SEG_LINETO
                when (index) {
                    1 -> {
                        coords[0] = x + width
                        coords[1] = y
                    }
                    2 -> {
                        coords[0] = x + width
                        coords[1] = y + height
                    }
                    3 -> {
                        coords[0] = x
                        coords[1] = y + height
                    }
                    4 -> {
                        coords[0] = x
                        coords[1] = y
                    }
                }
            }
            t?.transform(coords, 0, coords, 0, 1)
            return type
        }
    }

    companion object {
        /** The bitmask that indicates that a point lies to the left of this rectangle. See
         * [.outcode].  */
        val OUT_LEFT = 1

        /** The bitmask that indicates that a point lies above this rectangle. See [.outcode].  */
        val OUT_TOP = 2

        /** The bitmask that indicates that a point lies to the right of this rectangle. See
         * [.outcode].  */
        val OUT_RIGHT = 4

        /** The bitmask that indicates that a point lies below this rectangle. See [.outcode].  */
        val OUT_BOTTOM = 8
    }
}
