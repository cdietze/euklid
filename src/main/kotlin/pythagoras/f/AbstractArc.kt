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

import pythagoras.f.IArc.ArcType
import kotlin.math.*

/**
 * Provides most of the implementation of [IArc], obtaining only the frame and other metrics
 * from the derived class.
 */
abstract class AbstractArc : AbstractRectangularShape(), IArc {
    override val startPoint: Point get() = startPoint(Point())

    override fun startPoint(target: Point): Point {
        val a = MathUtil.toRadians(angleStart)
        return target.set(x + (1f + cos(a)) * width / 2f,
                y + (1f - sin(a)) * height / 2f)
    }

    override val endPoint: Point get() = endPoint(Point())

    override fun endPoint(target: Point): Point {
        val a = MathUtil.toRadians(angleStart + angleExtent)
        return target.set(x + (1f + cos(a)) * width / 2f,
                y + (1f - sin(a)) * height / 2f)
    }

    override fun containsAngle(angle: Float): Boolean {
        val extent = angleExtent
        if (extent >= 360f) {
            return true
        }
        val normAngle = normAngle(angle)
        val a1 = normAngle(angleStart)
        val a2 = a1 + extent
        if (a2 > 360f) {
            return normAngle >= a1 || normAngle <= a2 - 360f
        }
        if (a2 < 0f) {
            return normAngle >= a2 + 360f || normAngle <= a1
        }
        return if (extent > 0f) normAngle in a1..a2 else normAngle in a2..a1
    }

    override val isEmpty: Boolean
        get() = arcType == ArcType.OPEN || super<AbstractRectangularShape>.isEmpty

    override fun contains(x: Float, y: Float): Boolean {
        // normalize point
        val nx = (x - this.x) / width - 0.5f
        val ny = (y - this.y) / height - 0.5f
        if (nx * nx + ny * ny > 0.25) {
            return false
        }

        val extent = angleExtent
        val absExtent = abs(extent)
        if (absExtent >= 360f) {
            return true
        }

        val containsAngle = containsAngle(MathUtil.toDegrees(-atan2(ny, nx)))
        if (arcType == ArcType.PIE) {
            return containsAngle
        }
        if (absExtent <= 180f && !containsAngle) {
            return false
        }

        val l = Line(startPoint, endPoint)
        val ccw1 = l.relativeCCW(x, y)
        val ccw2 = l.relativeCCW(centerX, centerY)
        return ccw1 == 0 || ccw2 == 0 || (ccw1 + ccw2 == 0) xor (absExtent > 180f)
    }

    override fun contains(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (!(contains(x, y) && contains(x + width, y) &&
                contains(x + width, y + height) && contains(x, y + height))) {
            return false
        }

        val absExtent = abs(angleExtent)
        if (arcType != ArcType.PIE || absExtent <= 180f || absExtent >= 360f) {
            return true
        }

        val r = Rectangle(x, y, width, height)
        val cx = centerX
        val cy = centerY
        if (r.contains(cx, cy)) {
            return false
        }

        val p1 = startPoint
        val p2 = endPoint
        return !r.intersectsLine(cx, cy, p1.x, p1.y) && !r.intersectsLine(cx, cy, p2.x, p2.y)
    }

    override fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (isEmpty || width <= 0f || height <= 0f) {
            return false
        }

        // check: does arc contain rectangle's points
        if (contains(x, y) || contains(x + width, y) ||
                contains(x, y + height) || contains(x + width, y + height)) {
            return true
        }

        val cx = centerX
        val cy = centerY
        val p1 = startPoint
        val p2 = endPoint

        // check: does rectangle contain arc's points
        val r = Rectangle(x, y, width, height)
        if (r.contains(p1) || r.contains(p2) || arcType == ArcType.PIE && r.contains(cx, cy)) {
            return true
        }

        if (arcType == ArcType.PIE) {
            if (r.intersectsLine(p1.x, p1.y, cx, cy) || r.intersectsLine(p2.x, p2.y, cx, cy)) {
                return true
            }
        } else {
            if (r.intersectsLine(p1.x, p1.y, p2.x, p2.y)) {
                return true
            }
        }

        // nearest rectangle point
        val nx = if (cx < x) x else if (cx > x + width) x + width else cx
        val ny = if (cy < y) y else if (cy > y + height) y + height else cy
        return contains(nx, ny)
    }

    override fun bounds(target: Rectangle): Rectangle {
        if (isEmpty) {
            target.setBounds(x, y, width, height)
            return target
        }

        val rx1 = x
        val ry1 = y
        val rx2 = rx1 + width
        val ry2 = ry1 + height

        val p1 = startPoint
        val p2 = endPoint

        var bx1 = if (containsAngle(180f)) rx1 else min(p1.x, p2.x)
        var by1 = if (containsAngle(90f)) ry1 else min(p1.y, p2.y)
        var bx2 = if (containsAngle(0f)) rx2 else max(p1.x, p2.x)
        var by2 = if (containsAngle(270f)) ry2 else max(p1.y, p2.y)

        if (arcType == ArcType.PIE) {
            val cx = centerX
            val cy = centerY
            bx1 = min(bx1, cx)
            by1 = min(by1, cy)
            bx2 = max(bx2, cx)
            by2 = max(by2, cy)
        }
        target.setBounds(bx1, by1, bx2 - bx1, by2 - by1)
        return target
    }

    override fun pathIterator(transform: Transform?): PathIterator = Iterator(this, transform)

    /** Returns a normalized angle (bound between 0 and 360 degrees).  */
    protected fun normAngle(angle: Float): Float = angle - floor(angle / 360f) * 360f

    /** An iterator over an [IArc].  */
    protected class Iterator internal constructor(a: IArc,
                                                  /** The path iterator transformation  */
                                                  private val t: Transform?) : PathIterator {
        /** The x coordinate of left-upper corner of the arc rectangle bounds  */
        private val x: Float

        /** The y coordinate of left-upper corner of the arc rectangle bounds  */
        private val y: Float

        /** The width of the arc rectangle bounds  */
        private val width: Float = a.width / 2f

        /** The height of the arc rectangle bounds  */
        private val height: Float = a.height / 2f

        /** The start angle of the arc in degrees  */
        private var angle: Float = 0f

        /** The angle extent in degrees  */
        private val extent: Float

        /** The closure type of the arc  */
        private val type: ArcType

        /** The current segment index  */
        private var index: Int = 0

        /** The number of arc segments the source arc subdivided to be approximated by Bezier
         * curves. Depends on extent value.  */
        private var arcCount: Int = 0

        /** The number of line segments. Depends on closure type.  */
        private var lineCount: Int = 0

        /** The step to calculate next arc subdivision point  */
        private var step: Float = 0f

        /** The temporary value of cosinus of the current angle  */
        private var cos: Float = 0f

        /** The temporary value of sinus of the current angle  */
        private var sin: Float = 0f

        /** The coefficient to calculate control points of Bezier curves  */
        private var k: Float = 0f

        /** The temporary value of x coordinate of the Bezier curve control vector  */
        private var kx: Float = 0f

        /** The temporary value of y coordinate of the Bezier curve control vector  */
        private var ky: Float = 0f

        /** The x coordinate of the first path point (MOVE_TO)  */
        private var mx: Float = 0f

        /** The y coordinate of the first path point (MOVE_TO)  */
        private var my: Float = 0f

        init {
            this.x = a.x + width
            this.y = a.y + height
            this.angle = -MathUtil.toRadians(a.angleStart)
            this.extent = -a.angleExtent
            this.type = a.arcType

            if (width < 0 || height < 0) {
                arcCount = 0
                lineCount = 0
                index = 1
            } else {

                if (abs(extent) >= 360f) {
                    arcCount = 4
                    k = 4f / 3f * (sqrt(2f) - 1f)
                    step = MathUtil.PI / 2f
                    if (extent < 0f) {
                        step = -step
                        k = -k
                    }
                } else {
                    arcCount = MathUtil.iceil(abs(extent) / 90f)
                    step = MathUtil.toRadians(extent / arcCount)
                    k = 4f / 3f * (1f - cos(step / 2f)) / sin(step / 2f)
                }

                lineCount = 0
                if (type == ArcType.CHORD) {
                    lineCount++
                } else if (type == ArcType.PIE) {
                    lineCount += 2
                }
            }
        }

        override fun windingRule(): Int = PathIterator.WIND_NON_ZERO

        override val isDone: Boolean
            get() = index > arcCount + lineCount

        override fun next() {
            index++
        }

        override fun currentSegment(coords: FloatArray): Int {
            if (isDone) {
                throw NoSuchElementException("Iterator out of bounds")
            }
            val type: Int
            val count: Int
            when {
                index == 0 -> {
                    type = PathIterator.SEG_MOVETO
                    count = 1
                    cos = cos(angle)
                    sin = sin(angle)
                    kx = k * width * sin
                    ky = k * height * cos
                    mx = x + cos * width
                    coords[0] = mx
                    my = y + sin * height
                    coords[1] = my
                }
                index <= arcCount -> {
                    type = PathIterator.SEG_CUBICTO
                    count = 3
                    coords[0] = mx - kx
                    coords[1] = my + ky
                    angle += step
                    cos = cos(angle)
                    sin = sin(angle)
                    kx = k * width * sin
                    ky = k * height * cos
                    mx = x + cos * width
                    coords[4] = mx
                    my = y + sin * height
                    coords[5] = my
                    coords[2] = mx + kx
                    coords[3] = my - ky
                }
                index == arcCount + lineCount -> {
                    type = PathIterator.SEG_CLOSE
                    count = 0
                }
                else -> {
                    type = PathIterator.SEG_LINETO
                    count = 1
                    coords[0] = x
                    coords[1] = y
                }
            }
            t?.transform(coords, 0, coords, 0, count)
            return type
        }
    }
}
