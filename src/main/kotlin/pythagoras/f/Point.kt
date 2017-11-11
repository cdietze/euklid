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
 * Represents a point on a plane.
 */
@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class Point(
        /** The x-coordinate of the point.  */
        override var x: Float = 0f,
        /** The y-coordinate of the point.  */
        override var y: Float = 0f
) : IPoint {

    /**
     * Constructs a point with coordinates equal to the supplied point.
     */
    constructor(p: XY) : this(p.x, p.y)

    /** Sets the coordinates of this point to be equal to those of the supplied point.
     * @return a reference to this this, for chaining.
     */
    fun set(p: XY): Point = set(p.x, p.y)

    /** Sets the coordinates of this point to the supplied values.
     * @return a reference to this this, for chaining.
     */
    fun set(x: Float, y: Float): Point {
        this.x = x
        this.y = y
        return this
    }

    /** Multiplies this point by a scale factor.
     * @return a a reference to this point, for chaining.
     */
    fun multLocal(s: Float): Point = mult(s, this)

    /** Translates this point by the specified offset.
     * @return a reference to this point, for chaining.
     */
    fun addLocal(dx: Float, dy: Float): Point = add(dx, dy, this)

    /** Rotates this point in-place by the specified angle.
     * @return a reference to this point, for chaining.
     */
    fun rotateLocal(angle: Float): Point = rotate(angle, this)

    /** Subtracts the supplied x/y from this point.
     * @return a reference to this point, for chaining.
     */
    fun subtractLocal(x: Float, y: Float): Point = subtract(x, y, this)

    /** @return a string of the form `+x+y`, `+x-y`, `-x-y`, etc. */
    override fun toString(): String = Points.pointToString(x, y)

    companion object {
        private const val serialVersionUID = -2666598890366249427L
    }
}
