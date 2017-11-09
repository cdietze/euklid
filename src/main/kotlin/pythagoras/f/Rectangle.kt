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
 * Represents an area in two dimensions.
 */
data class Rectangle(
        /** The x-coordinate of the rectangle's upper left corner.  */
        override var x: Float = 0f,
        /** The y-coordinate of the rectangle's upper left corner.  */
        override var y: Float = 0f,
        /** The width of the rectangle.  */
        override var width: Float = 0f,
        /** The height of the rectangle.  */
        override var height: Float = 0f
) : AbstractRectangle() {

    /**
     * Constructs a rectangle at (0,0) and with dimensions (0,0).
     */
    constructor() : this(0f, 0f, 0f, 0f)

    /**
     * Constructs a rectangle with the supplied upper-left corner and dimensions (0,0).
     */
    constructor(p: XY) : this(p.x, p.y, 0f, 0f)

    /**
     * Constructs a rectangle with upper-left corner at (0,0) and the supplied dimensions.
     */
    constructor(d: IDimension) : this(0f, 0f, d.width, d.height)

    /**
     * Constructs a rectangle with upper-left corner at the supplied point and with the supplied
     * dimensions.
     */
    constructor(p: XY, d: IDimension) : this(p.x, p.y, d.width, d.height)

    /**
     * Constructs a rectangle with bounds equal to the supplied rectangle.
     */
    constructor(r: IRectangle) : this(r.x, r.y, r.width, r.height)

    /**
     * Sets the upper-left corner of this rectangle to the specified point.
     */
    fun setLocation(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    /**
     * Sets the upper-left corner of this rectangle to the supplied point.
     */
    fun setLocation(p: XY) {
        setLocation(p.x, p.y)
    }

    /**
     * Sets the size of this rectangle to the specified dimensions.
     */
    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    /**
     * Sets the size of this rectangle to the supplied dimensions.
     */
    fun setSize(d: IDimension) {
        setSize(d.width, d.height)
    }

    /**
     * Sets the bounds of this rectangle to the specified bounds.
     */
    fun setBounds(x: Float, y: Float, width: Float, height: Float) {
        this.x = x
        this.y = y
        this.height = height
        this.width = width
    }

    /**
     * Sets the bounds of this rectangle to those of the supplied rectangle.
     */
    fun setBounds(r: IRectangle) {
        setBounds(r.x, r.y, r.width, r.height)
    }

    /**
     * Grows the bounds of this rectangle by the specified amount (i.e. the upper-left corner moves
     * by the specified amount in the negative x and y direction and the width and height grow by
     * twice the specified amount).
     */
    fun grow(dx: Float, dy: Float) {
        x -= dx
        y -= dy
        width += dx + dx
        height += dy + dy
    }

    /**
     * Translates the upper-left corner of this rectangle by the specified amount.
     */
    fun translate(mx: Float, my: Float) {
        x += mx
        y += my
    }

    /**
     * Expands the bounds of this rectangle to contain the specified point.
     */
    fun add(px: Float, py: Float) {
        val x1 = min(x, px)
        val x2 = max(x + width, px)
        val y1 = min(y, py)
        val y2 = max(y + height, py)
        setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    /**
     * Expands the bounds of this rectangle to contain the supplied point.
     */
    fun add(p: XY) {
        add(p.x, p.y)
    }

    /**
     * Expands the bounds of this rectangle to contain the supplied rectangle.
     */
    fun add(r: IRectangle) {
        val x1 = min(x, r.x)
        val x2 = max(x + width, r.x + r.width)
        val y1 = min(y, r.y)
        val y2 = max(y + height, r.y + r.height)
        setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    override
    fun setFrame(x: Float, y: Float, width: Float, height: Float) {
        setBounds(x, y, width, height)
    }

    companion object {
        private const val serialVersionUID = -803067517133475390L
    }
}
