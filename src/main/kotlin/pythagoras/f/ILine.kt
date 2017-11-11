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
 * Provides read-only access to a [Line].
 */
interface ILine : IShape {
    /** Returns the x-coordinate of the start of this line.  */
    val x1: Float

    /** Returns the y-coordinate of the start of this line.  */
    val y1: Float

    /** Returns the x-coordinate of the end of this line.  */
    val x2: Float

    /** Returns the y-coordinate of the end of this line.  */
    val y2: Float

    /** Returns a copy of the starting point of this line.  */
    fun p1(): Point = p1(Point())

    /** Initializes the supplied point with this line's starting point.
     * @return the supplied point.
     */
    fun p1(target: Point): Point = target.set(x1, y1)

    /** Returns a copy of the ending point of this line.  */
    fun p2(): Point = p2(Point())

    /** Initializes the supplied point with this line's ending point.
     * @return the supplied point.
     */
    fun p2(target: Point): Point = target.set(x2, y2)

    /** Returns the square of the distance from the specified point to the line defined by this
     * line segment.  */
    fun pointLineDistSq(px: Float, py: Float): Float = Lines.pointLineDistSq(px, py, x1, y1, x2, y2)

    /** Returns the square of the distance from the supplied point to the line defined by this line
     * segment.  */
    fun pointLineDistSq(p: XY): Float = Lines.pointLineDistSq(p.x, p.y, x1, y1, x2, y2)

    /** Returns the distance from the specified point to the line defined by this line segment.  */
    fun pointLineDist(px: Float, py: Float): Float = Lines.pointLineDist(px, py, x1, y1, x2, y2)

    /** Returns the distance from the supplied point to the line defined by this line segment.  */
    fun pointLineDist(p: XY): Float = Lines.pointLineDist(p.x, p.y, x1, y1, x2, y2)

    /** Returns the square of the distance from the specified point this line segment.  */
    fun pointSegDistSq(px: Float, py: Float): Float = Lines.pointSegDistSq(px, py, x1, y1, x2, y2)

    /** Returns the square of the distance from the supplied point this line segment.  */
    fun pointSegDistSq(p: XY): Float = Lines.pointSegDistSq(p.x, p.y, x1, y1, x2, y2)

    /** Returns the distance from the specified point this line segment.  */
    fun pointSegDist(px: Float, py: Float): Float = Lines.pointSegDist(px, py, x1, y1, x2, y2)

    /** Returns the distance from the supplied point this line segment.  */
    fun pointSegDist(p: XY): Float = Lines.pointSegDist(p.x, p.y, x1, y1, x2, y2)

    /** Returns an indicator of where the specified point (px,py) lies with respect to this line
     * segment.  */
    fun relativeCCW(px: Float, py: Float): Int = Lines.relativeCCW(px, py, x1, y1, x2, y2)

    /** Returns an indicator of where the specified point lies with respect to this line segment.  */
    fun relativeCCW(p: XY): Int = Lines.relativeCCW(p.x, p.y, x1, y1, x2, y2)

    /** Returns a mutable copy of this line.  */
    fun clone(): Line = Line(x1, y1, x2, y2)

    override val isEmpty: Boolean
        get() = true

    override fun contains(x: Float, y: Float): Boolean = false

    override fun contains(x: Float, y: Float, width: Float, height: Float): Boolean = false

    override fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean =
            Lines.lineIntersectsRect(x1, y1, x2, y2, x, y, width, height)

    override fun bounds(target: Rectangle): Rectangle {
        val x1 = x1
        val x2 = x2
        val y1 = y1
        val y2 = y2
        val rx: Float
        val ry: Float
        val rw: Float
        val rh: Float
        if (x1 < x2) {
            rx = x1
            rw = x2 - x1
        } else {
            rx = x2
            rw = x1 - x2
        }
        if (y1 < y2) {
            ry = y1
            rh = y2 - y1
        } else {
            ry = y2
            rh = y1 - y2
        }
        target.setBounds(rx, ry, rw, rh)
        return target
    }

    override fun pathIterator(transform: Transform?): PathIterator = Iterator(this, transform)

    override fun pathIterator(transform: Transform?, flatness: Float): PathIterator = Iterator(this, transform)

    /** An iterator over an [ILine].  */
    private class Iterator internal constructor(l: ILine, private val t: Transform?) : PathIterator {
        private val x1: Float = l.x1
        private val y1: Float = l.y1
        private val x2: Float = l.x2
        private val y2: Float = l.y2
        private var index: Int = 0

        override fun windingRule(): Int = PathIterator.WIND_NON_ZERO

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
            if (index == 0) {
                type = PathIterator.SEG_MOVETO
                coords[0] = x1
                coords[1] = y1
            } else {
                type = PathIterator.SEG_LINETO
                coords[0] = x2
                coords[1] = y2
            }
            t?.transform(coords, 0, coords, 0, 1)
            return type
        }
    }

}
