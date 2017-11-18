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
@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class Rectangle(
        /** The x-coordinate of the rectangle's upper left corner.  */
        override var x: Float = 0f,
        /** The y-coordinate of the rectangle's upper left corner.  */
        override var y: Float = 0f,
        /** The width of the rectangle.  */
        override var width: Float = 0f,
        /** The height of the rectangle.  */
        override var height: Float = 0f
) : AbstractRectangularShape(), IRectangle {

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
     * @return a reference to this this, for chaining.
     */
    fun setLocation(x: Float, y: Float): Rectangle {
        this.x = x
        this.y = y
        return this
    }

    /**
     * Sets the upper-left corner of this rectangle to the supplied point.
     * @return a reference to this this, for chaining.
     */
    fun setLocation(p: XY): Rectangle = setLocation(p.x, p.y)

    /**
     * Sets the size of this rectangle to the specified dimensions.
     * @return a reference to this this, for chaining.
     */
    fun setSize(width: Float, height: Float): Rectangle {
        this.width = width
        this.height = height
        return this
    }

    /**
     * Sets the size of this rectangle to the supplied dimensions.
     * @return a reference to this this, for chaining.
     */
    fun setSize(d: IDimension): Rectangle = setSize(d.width, d.height)

    /**
     * Sets the bounds of this rectangle to the specified bounds.
     * @return a reference to this this, for chaining.
     */
    fun setBounds(x: Float, y: Float, width: Float, height: Float): Rectangle {
        this.x = x
        this.y = y
        this.height = height
        this.width = width
        return this
    }

    /**
     * Sets the bounds of this rectangle to those of the supplied rectangle.
     * @return a reference to this this, for chaining.
     */
    fun setBounds(r: IRectangle): Rectangle = setBounds(r.x, r.y, r.width, r.height)

    /**
     * Grows the bounds of this rectangle by the specified amount (i.e. the upper-left corner moves
     * by the specified amount in the negative x and y direction and the width and height grow by
     * twice the specified amount).
     * @return a reference to this this, for chaining.
     */
    fun grow(dx: Float, dy: Float): Rectangle {
        x -= dx
        y -= dy
        width += dx + dx
        height += dy + dy
        return this
    }

    /**
     * Translates the upper-left corner of this rectangle by the specified amount.
     * @return a reference to this this, for chaining.
     */
    fun translate(mx: Float, my: Float): Rectangle {
        x += mx
        y += my
        return this
    }

    /**
     * Expands the bounds of this rectangle to contain the specified point.
     * @return a reference to this this, for chaining.
     */
    fun add(px: Float, py: Float): Rectangle {
        val x1 = min(x, px)
        val x2 = max(x + width, px)
        val y1 = min(y, py)
        val y2 = max(y + height, py)
        return setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    /**
     * Expands the bounds of this rectangle to contain the supplied point.
     * @return a reference to this this, for chaining.
     */
    fun add(p: XY): Rectangle = add(p.x, p.y)

    /**
     * Expands the bounds of this rectangle to contain the supplied rectangle.
     * @return a reference to this this, for chaining.
     */
    fun add(r: IRectangle): Rectangle {
        val x1 = min(x, r.x)
        val x2 = max(x + width, r.x + r.width)
        val y1 = min(y, r.y)
        val y2 = max(y + height, r.y + r.height)
        return setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    override fun setFrame(x: Float, y: Float, width: Float, height: Float) = setBounds(x, y, width, height)

    /** @return a string describing this rectangle, of the form `width`x`height`+`x`+y` */
    override fun toString(): String = Dimensions.dimenToString(width, height) + Points.pointToString(x, y)
}
