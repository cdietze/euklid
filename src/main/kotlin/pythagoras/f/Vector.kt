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

import kotlin.math.cos
import kotlin.math.sin

/**
 * Represents a vector in a plane.
 */
@Suppress("DATA_CLASS_OVERRIDE_DEFAULT_VALUES_WARNING")
data class Vector(
        /** The x-component of the vector.  */
        override var x: Float = 0f,
        /** The y-component of the vector.  */
        override var y: Float = 0f
) : IVector {

    /** Creates a vector equal to `other`.  */
    constructor(other: XY) : this(other.x, other.y)

    /** Computes the cross product of this and the specified other vector, storing the result in
     * this vector.
     * @return a reference to this vector, for chaining.
     */
    fun crossLocal(other: IVector): Vector = cross(other, this)

    /** Negates this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun negateLocal(): Vector = negate(this)

    /** Normalizes this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun normalizeLocal(): Vector = normalize(this)

    /** Scales this vector in place, uniformly by the specified magnitude.
     * @return a reference to this vector, for chaining.
     */
    fun scaleLocal(v: Float): Vector = scale(v, this)

    /** Scales this vector's x and y components, in place, independently by the x and y components
     * of the supplied vector.
     * @return a reference to this vector, for chaining.
     */
    fun scaleLocal(other: IVector): Vector = scale(other, this)

    /** Adds a vector in-place to this one.
     * @return a reference to this vector, for chaining.
     */
    fun addLocal(other: IVector): Vector = add(other, this)

    /** Subtracts a vector in-place from this one.
     * @return a reference to this vector, for chaining.
     */
    fun subtractLocal(other: IVector): Vector = subtract(other, this)

    /** Adds a vector in-place to this one.
     * @return a reference to this vector, for chaining.
     */
    fun addLocal(x: Float, y: Float): Vector = add(x, y, this)

    /** Subtracts a vector in-place from this one.
     * @return a reference to this vector, for chaining.
     */
    fun subtractLocal(x: Float, y: Float): Vector = subtract(x, y, this)

    /** Adds a scaled vector in-place to this one.
     * @return a reference to this vector, for chaining.
     */
    fun addScaledLocal(other: IVector, v: Float): Vector = addScaled(other, v, this)

    /** Rotates this vector in-place by the specified angle.
     * @return a reference to this vector, for chaining.
     */
    fun rotateLocal(angle: Float): Vector = rotate(angle, this)

    /** Linearly interpolates between this and `other` in-place by the supplied amount.
     * @return a reference to this vector, for chaining.
     */
    fun lerpLocal(other: IVector, t: Float): Vector = lerp(other, t, this)

    /** Copies the elements of another vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(other: XY): Vector = set(other.x, other.y)

    /** Copies the elements of an array.
     * @return a reference to this vector, for chaining.
     */
    fun set(values: FloatArray): Vector = set(values[0], values[1])

    /** Sets all of the elements of the vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(x: Float, y: Float): Vector {
        this.x = x
        this.y = y
        return this
    }

    /**
     * Sets this vector's angle, preserving its magnitude.
     * @return a reference to this vector, for chaining.
     */
    fun setAngle(angle: Float): Vector {
        val l = length()
        return set(l * cos(angle), l * sin(angle))
    }

    /**
     * Sets this vector's magnitude, preserving its angle.
     */
    fun setLength(length: Float): Vector = normalizeLocal().scaleLocal(length)

    /**
     * @return a string describing this vector, of the form `+x+y`, `+x-y`, `-x-y`, etc.
     */
    override fun toString(): String = Vectors.vectorToString(x, y)
}
