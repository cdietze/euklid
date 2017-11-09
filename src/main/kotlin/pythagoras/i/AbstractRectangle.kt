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

import kotlin.math.max
import kotlin.math.min

/**
 * Provides most of the implementation of [IRectangle], obtaining only the location and
 * dimensions from the derived class.
 */
abstract class AbstractRectangle : IRectangle {
    override
    fun minX(): Int {
        return x
    }

    override
    fun minY(): Int {
        return y
    }

    override
    fun maxX(): Int {
        return x + width - 1
    }

    override
    fun maxY(): Int {
        return y + height - 1
    }

    override
    fun location(): Point {
        return location(Point())
    }

    override
    fun location(target: Point): Point {
        target.setLocation(x, y)
        return target
    }

    override
    fun size(): Dimension {
        return size(Dimension())
    }

    override
    fun size(target: Dimension): Dimension {
        target.setSize(width, height)
        return target
    }

    override
    fun intersection(rx: Int, ry: Int, rw: Int, rh: Int): Rectangle {
        val x1 = max(x, rx)
        val y1 = max(y, ry)
        val x2 = min(maxX(), rx + rw - 1)
        val y2 = min(maxY(), ry + rh - 1)
        return Rectangle(x1, y1, x2 - x1, y2 - y1)
    }

    override
    fun intersection(r: IRectangle): Rectangle {
        return intersection(r.x, r.y, r.width, r.height)
    }

    override
    fun union(r: IRectangle): Rectangle {
        val rect = Rectangle(this)
        rect.add(r)
        return rect
    }

    override
    fun outcode(px: Int, py: Int): Int {
        var code = 0

        if (width <= 0) {
            code = code or (IRectangle.OUT_LEFT or IRectangle.OUT_RIGHT)
        } else if (px < x) {
            code = code or IRectangle.OUT_LEFT
        } else if (px > maxX()) {
            code = code or IRectangle.OUT_RIGHT
        }

        if (height <= 0) {
            code = code or (IRectangle.OUT_TOP or IRectangle.OUT_BOTTOM)
        } else if (py < y) {
            code = code or IRectangle.OUT_TOP
        } else if (py > maxY()) {
            code = code or IRectangle.OUT_BOTTOM
        }

        return code
    }

    override
    fun outcode(point: IPoint): Int {
        return outcode(point.x, point.y)
    }

    override
    val isEmpty: Boolean
        get() = width <= 0 || height <= 0

    override
    fun contains(x: Int, y: Int): Boolean {
        var px = x
        var py = y
        if (isEmpty) return false

        if (px < this.x || py < this.y) return false

        px -= this.x
        py -= this.y
        return px < width && py < height
    }

    override
    fun contains(x: Int, y: Int, width: Int, height: Int): Boolean {
        if (isEmpty) return false
        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        return x1 <= x && x + width <= x2 && y1 <= y && y + height <= y2
    }

    override
    fun intersects(x: Int, y: Int, width: Int, height: Int): Boolean {
        if (isEmpty) return false
        val x1 = this.x
        val y1 = this.y
        val x2 = x1 + this.width
        val y2 = y1 + this.height
        return x + width > x1 && x < x2 && y + height > y1 && y < y2
    }

    override
    fun bounds(target: Rectangle): Rectangle {
        target.setBounds(x, y, width, height)
        return target
    }

    override fun toString(): String {
        return Dimensions.dimenToString(width, height) + Points.pointToString(x, y)
    }
}
