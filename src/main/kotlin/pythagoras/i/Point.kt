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

/**
 * Represents a point on a plane.
 */
@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class Point(
        /** The x-coordinate of the point.  */
        override var x: Int = 0,
        /** The y-coordinate of the point.  */
        override var y: Int = 0
) : IPoint {

    /**
     * Constructs a point with coordinates equal to the supplied point.
     */
    constructor(p: IPoint) : this(p.x, p.y)

    /**
     * Sets the coordinates of this point to be equal to those of the supplied point.
     * @return a reference to this this, for chaining.
     */
    fun setLocation(p: IPoint): Point = setLocation(p.x, p.y)

    /**
     * Sets the coordinates of this point to the supplied values.
     * @return a reference to this this, for chaining.
     */
    fun setLocation(x: Int, y: Int): Point {
        this.x = x
        this.y = y
        return this
    }

    /**
     * A synonym for [setLocation].
     * @return a reference to this this, for chaining.
     */
    fun move(x: Int, y: Int): Point = setLocation(x, y)

    /**
     * Translates this point by the specified offset.
     * @return a reference to this this, for chaining.
     */
    fun translate(dx: Int, dy: Int): Point {
        x += dx
        y += dy
        return this
    }

    /** Sets the coordinates of this point to be equal to those of the supplied point.
     * @return a reference to this this, for chaining.
     */
    fun set(p: IPoint): Point = set(p.x, p.y)

    /** Sets the coordinates of this point to the supplied values.
     * @return a reference to this this, for chaining.
     */
    fun set(x: Int, y: Int): Point {
        this.x = x
        this.y = y
        return this
    }

    /** Translates this point by the specified offset.
     * @return a reference to this point, for chaining.
     */
    fun addLocal(dx: Int, dy: Int): Point = add(dx, dy, this)

    /** Subtracts the supplied x/y from this point.
     * @return a reference to this point, for chaining.
     */
    fun subtractLocal(x: Int, y: Int): Point = subtract(x, y, this)

    /** @return a string describing this point, of the form `+x+y`, `+x-y`, `-x-y`, etc. */
    override fun toString(): String = Points.pointToString(x, y)

    companion object {
        private const val serialVersionUID = -6346341779228562585L
    }
}
