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

/**
 * Provides read-only access to a [Rectangle].
 */
interface IRectangle : IRectangularShape {

    /** Returns a copy of this rectangle's upper-left corner.  */
    fun location(): Point

    /** Initializes the supplied point with this rectangle's upper-left corner.
     * @return the supplied point.
     */
    fun location(target: Point): Point

    /** Returns a copy of this rectangle's size.  */
    fun size(): Dimension

    /** Initializes the supplied dimension with this rectangle's size.
     * @return the supplied dimension.
     */
    fun size(target: Dimension): Dimension

    /** Returns the intersection of the specified rectangle and this rectangle (i.e. the largest
     * rectangle contained in both this and the specified rectangle).  */
    fun intersection(rx: Float, ry: Float, rw: Float, rh: Float): Rectangle

    /** Returns the intersection of the supplied rectangle and this rectangle (i.e. the largest
     * rectangle contained in both this and the supplied rectangle).  */
    fun intersection(r: IRectangle): Rectangle

    /** Returns the union of the supplied rectangle and this rectangle (i.e. the smallest rectangle
     * that contains both this and the supplied rectangle).  */
    fun union(r: IRectangle): Rectangle

    /** Returns true if the specified line segment intersects this rectangle.  */
    fun intersectsLine(x1: Float, y1: Float, x2: Float, y2: Float): Boolean

    /** Returns true if the supplied line segment intersects this rectangle.  */
    fun intersectsLine(l: ILine): Boolean

    /** Returns a set of flags indicating where the specified point lies in relation to the bounds
     * of this rectangle. See [.OUT_LEFT], etc.  */
    fun outcode(px: Float, py: Float): Int

    /** Returns a set of flags indicating where the supplied point lies in relation to the bounds of
     * this rectangle. See [.OUT_LEFT], etc.  */
    fun outcode(point: XY): Int

    /** Returns a mutable copy of this rectangle.  */
    fun copy(x: Float = this.x, y: Float = this.y, width: Float = this.width, height: Float = this.height): Rectangle

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
