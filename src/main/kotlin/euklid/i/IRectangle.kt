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

import kotlin.math.max
import kotlin.math.min

/**
 * Provides read-only access to a [Rectangle].
 */
interface IRectangle : IShape {

    /** Returns the x-coordinate of the upper-left corner of the framing rectangle.  */
    val x: Int

    /** Returns the y-coordinate of the upper-left corner of the framing rectangle.  */
    val y: Int

    /** Returns the width of the framing rectangle.  */
    val width: Int

    /** Returns the height of the framing rectangle.  */
    val height: Int

    /** Returns the minimum x-coordinate of the framing rectangle.  */
    fun minX(): Int = x

    /** Returns the minimum y-coordinate of the framing rectangle.  */
    fun minY(): Int = y

    /** Returns the maximum x-coordinate of the framing rectangle. *Note:* this method
     * differs from its floating-point counterparts in that it considers `(x + width - 1)` to
     * be a rectangle's maximum x-coordinate.  */
    fun maxX(): Int = x + width - 1

    /** Returns the maximum y-coordinate of the framing rectangle. *Note:* this method
     * differs from its floating-point counterparts in that it considers `(y + height - 1)`
     * to be a rectangle's maximum x-coordinate.  */
    fun maxY(): Int = y + height - 1

    /** Returns a copy of this rectangle's upper-left corner.  */
    fun location(): Point = location(Point())

    /** Initializes the supplied point with this rectangle's upper-left corner.
     * @return the supplied point.
     */
    fun location(target: Point): Point = target.setLocation(x, y)

    /** Returns a copy of this rectangle's size.  */
    fun size(): Dimension = size(Dimension())

    /** Initializes the supplied dimension with this rectangle's size.
     * @return the supplied dimension.
     */
    fun size(target: Dimension): Dimension = target.setSize(width, height)

    /** Returns the intersection of the specified rectangle and this rectangle (i.e. the largest
     * rectangle contained in both this and the specified rectangle).  */
    fun intersection(rx: Int, ry: Int, rw: Int, rh: Int): Rectangle {
        val x1 = max(x, rx)
        val y1 = max(y, ry)
        val x2 = min(maxX(), rx + rw - 1)
        val y2 = min(maxY(), ry + rh - 1)
        return Rectangle(x1, y1, x2 - x1, y2 - y1)
    }

    /** Returns the intersection of the supplied rectangle and this rectangle (i.e. the largest
     * rectangle contained in both this and the supplied rectangle).  */
    fun intersection(r: IRectangle): Rectangle = intersection(r.x, r.y, r.width, r.height)

    /** Returns the union of the supplied rectangle and this rectangle (i.e. the smallest rectangle
     * that contains both this and the supplied rectangle).  */
    fun union(r: IRectangle): Rectangle {
        val rect = Rectangle(this)
        return rect.add(r)
    }

    /** Returns a set of flags indicating where the specified point lies in relation to the bounds
     * of this rectangle. See [.OUT_LEFT], etc.  */
    fun outcode(px: Int, py: Int): Int {
        var code = 0
        when {
            width <= 0 -> code = code or (IRectangle.OUT_LEFT or IRectangle.OUT_RIGHT)
            px < x -> code = code or IRectangle.OUT_LEFT
            px > maxX() -> code = code or IRectangle.OUT_RIGHT
        }
        when {
            height <= 0 -> code = code or (IRectangle.OUT_TOP or IRectangle.OUT_BOTTOM)
            py < y -> code = code or IRectangle.OUT_TOP
            py > maxY() -> code = code or IRectangle.OUT_BOTTOM
        }
        return code
    }

    /** Returns a set of flags indicating where the supplied point lies in relation to the bounds of
     * this rectangle. See [.OUT_LEFT], etc.  */
    fun outcode(point: IPoint): Int = outcode(point.x, point.y)

    /** Returns a mutable copy of this rectangle.  */
    fun copy(): Rectangle = Rectangle(this)

    override val isEmpty: Boolean
        get() = width <= 0 || height <= 0

    override fun contains(x: Int, y: Int): Boolean {
        var px = x
        var py = y
        if (isEmpty) return false

        if (px < this.x || py < this.y) return false

        px -= this.x
        py -= this.y
        return px < width && py < height
    }

    override fun contains(x: Int, y: Int, width: Int, height: Int): Boolean {
        if (isEmpty) return false
        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        return x1 <= x && x + width <= x2 && y1 <= y && y + height <= y2
    }

    override fun intersects(x: Int, y: Int, width: Int, height: Int): Boolean {
        if (isEmpty) return false
        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        return x + width > x1 && x < x2 && y + height > y1 && y < y2
    }

    override fun bounds(target: Rectangle): Rectangle {
        target.setBounds(x, y, width, height)
        return target
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
