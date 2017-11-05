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
 * Provides most of the implementation of [IQuadCurve], obtaining only the start, end and
 * control point from the derived class.
 */
abstract class AbstractQuadCurve : IQuadCurve {
    override // from interface IQuadCurve
    fun p1(): Point {
        return Point(x1, y1)
    }

    override // from interface IQuadCurve
    fun ctrlP(): Point {
        return Point(ctrlX, ctrlY)
    }

    override // from interface IQuadCurve
    fun p2(): Point {
        return Point(x2, y2)
    }

    override // from interface IQuadCurve
    fun flatnessSq(): Float {
        return Lines.pointSegDistSq(ctrlX, ctrlY, x1, y1, x2, y2)
    }

    override // from interface IQuadCurve
    fun flatness(): Float {
        return Lines.pointSegDist(ctrlX, ctrlY, x1, y1, x2, y2)
    }

    override // from interface IQuadCurve
    fun subdivide(left: QuadCurve, right: QuadCurve) {
        QuadCurves.subdivide(this, left, right)
    }

    override // from interface IQuadCurve
    fun clone(): QuadCurve {
        return QuadCurve(x1, y1, ctrlX, ctrlY, x2, y2)
    }

    override // from interface IShape
            // curves contain no space
    val isEmpty: Boolean
        get() = true

    override // from interface IShape
    fun contains(x: Float, y: Float): Boolean {
        return Crossing.isInsideEvenOdd(Crossing.crossShape(this, x, y))
    }

    override // from interface IShape
    fun contains(x: Float, y: Float, width: Float, height: Float): Boolean {
        val cross = Crossing.intersectShape(this, x, y, width, height)
        return cross != Crossing.CROSSING && Crossing.isInsideEvenOdd(cross)
    }

    override // from interface IShape
    fun contains(point: XY): Boolean {
        return contains(point.x, point.y)
    }

    override // from interface IShape
    fun contains(rect: IRectangle): Boolean {
        return contains(rect.x, rect.y, rect.width, rect.height)
    }

    override // from interface IShape
    fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean {
        val cross = Crossing.intersectShape(this, x, y, width, height)
        return cross == Crossing.CROSSING || Crossing.isInsideEvenOdd(cross)
    }

    override // from interface IShape
    fun intersects(rect: IRectangle): Boolean {
        return intersects(rect.x, rect.y, rect.width, rect.height)
    }

    override // from interface IShape
    fun bounds(): Rectangle {
        return bounds(Rectangle())
    }

    override // from interface IShape
    fun bounds(target: Rectangle): Rectangle {
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

    override // from interface IShape
    fun pathIterator(transform: Transform?): PathIterator {
        return Iterator(this, transform)
    }

    override // from interface IShape
    fun pathIterator(transform: Transform?, flatness: Float): PathIterator {
        return FlatteningPathIterator(pathIterator(transform), flatness)
    }

    /** An iterator over an [IQuadCurve].  */
    protected class Iterator internal constructor(private val c: IQuadCurve, private val t: Transform?) : PathIterator {
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
