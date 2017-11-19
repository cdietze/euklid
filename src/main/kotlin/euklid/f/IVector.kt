/*
 * Copyright 2017 The Euklid Authors
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



package euklid.f

import kotlin.math.*

/**
 * Provides read-only access to a [Vector].
 */
interface IVector : XY {
    /** Computes and returns the dot product of this and the specified other vector.  */
    fun dot(other: IVector): Float = x * other.x + y * other.y

    /** Computes the cross product of this and the specified other vector.
     * @return a new vector containing the result.
     */
    fun cross(other: IVector): Vector = cross(other, Vector())

    /** Computes the cross product of this and the specified other vector, placing the result in
     * the object supplied.
     * @return a reference to the result, for chaining.
     */
    fun cross(other: IVector, result: Vector): Vector {
        val x = x
        val y = y
        val ox = other.x
        val oy = other.y
        return result.set(y * ox - x * oy, x * oy - y * ox)
    }

    /** Negates this vector.
     * @return a new vector containing the result.
     */
    fun negate(): Vector = negate(Vector())

    /** Negates this vector, storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun negate(result: Vector): Vector = result.set(-x, -y)

    /** Normalizes this vector.
     * @return a new vector containing the result.
     */
    fun normalize(): Vector = normalize(Vector())

    /** Normalizes this vector, storing the result in the object supplied.
     * @return a reference to the result, for chaining.
     */
    fun normalize(result: Vector): Vector = scale(1f / length(), result)

    /** Returns the length (magnitude) of this vector.  */
    fun length(): Float = sqrt(lengthSq())

    /** Returns the squared length of this vector.  */
    fun lengthSq(): Float = x * x + y * y

    /** Returns true if this vector has zero magnitude.  */
    val isZero: Boolean
        get() = Vectors.isZero(x, y)

    /** Returns the distance from this vector to the specified other vector.  */
    fun distance(other: IVector): Float = sqrt(distanceSq(other))

    /** Returns the squared distance from this vector to the specified other.  */
    fun distanceSq(other: IVector): Float {
        val dx = x - other.x
        val dy = y - other.y
        return dx * dx + dy * dy
    }

    /** Returns the angle of this vector.  */
    fun angle(): Float = atan2(y, x)

    /** Returns the angle between this vector and the specified other vector.  */
    fun angleBetween(other: IVector): Float {
        val cos = dot(other) / (length() * other.length())
        return if (cos >= 1f) 0f else acos(cos)
    }

    /** Scales this vector uniformly by the specified magnitude.
     * @return a new vector containing the result.
     */
    fun scale(v: Float): Vector = scale(v, Vector())

    /** Scales this vector uniformly by the specified magnitude, and places the result in the
     * supplied object.
     * @return a reference to the result, for chaining.
     */
    fun scale(v: Float, result: Vector): Vector = result.set(x * v, y * v)

    /** Scales this vector's x and y components independently by the x and y components of the
     * supplied vector.
     * @return a new vector containing the result.
     */
    fun scale(other: IVector): Vector = scale(other, Vector())

    /** Scales this vector's x and y components independently by the x and y components of the
     * supplied vector, and stores the result in the object provided.
     * @return a reference to the result vector, for chaining.
     */
    fun scale(other: IVector, result: Vector): Vector = result.set(x * other.x, y * other.y)

    /** Adds a vector to this one.
     * @return a new vector containing the result.
     */
    fun add(other: IVector): Vector = add(other, Vector())

    /** Adds a vector to this one, storing the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(other: IVector, result: Vector): Vector = add(other.x, other.y, result)

    /** Adds a vector to this one.
     * @return a new vector containing the result.
     */
    fun add(x: Float, y: Float): Vector = add(x, y, Vector())

    /** Adds a vector to this one and stores the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(x: Float, y: Float, result: Vector): Vector = result.set(this.x + x, this.y + y)

    /** Adds a scaled vector to this one.
     * @return a new vector containing the result.
     */
    fun addScaled(other: IVector, v: Float): Vector = addScaled(other, v, Vector())

    /** Adds a scaled vector to this one and stores the result in the supplied vector.
     * @return a reference to the result, for chaining.
     */
    fun addScaled(other: IVector, v: Float, result: Vector): Vector = result.set(x + other.x * v, y + other.y * v)

    /** Subtracts a vector from this one.
     * @return a new vector containing the result.
     */
    fun subtract(other: IVector): Vector = subtract(x, y, Vector())

    /** Subtracts a vector from this one and places the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun subtract(other: IVector, result: Vector): Vector = add(-other.x, -other.y, result)

    /** Subtracts a vector from this one.
     * @return a new vector containing the result.
     */
    fun subtract(x: Float, y: Float): Vector = subtract(x, y, Vector())

    /** Subtracts a vector from this one and places the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun subtract(x: Float, y: Float, result: Vector): Vector = result.set(this.x - x, this.y - y)

    /** Rotates this vector by the specified angle.
     * @return a new vector containing the result.
     */
    fun rotate(angle: Float): Vector = rotate(angle, Vector())

    /** Rotates this vector by the specified angle, storing the result in the vector provided.
     * @return a reference to the result vector, for chaining.
     */
    fun rotate(angle: Float, result: Vector): Vector {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set(x * cosa - y * sina, x * sina + y * cosa)
    }

    /** Rotates this vector by the specified angle and adds another vector to it, placing the
     * result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun rotateAndAdd(angle: Float, add: IVector, result: Vector): Vector {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set(x * cosa - y * sina + add.x, x * sina + y * cosa + add.y)
    }

    /** Rotates this vector by the specified angle, applies a uniform scale, and adds another
     * vector to it, placing the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun rotateScaleAndAdd(angle: Float, scale: Float, add: IVector, result: Vector): Vector {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set((x * cosa - y * sina) * scale + add.x,
                (x * sina + y * cosa) * scale + add.y)
    }

    /** Linearly interpolates between this and the specified other vector by the supplied amount.
     * @return a new vector containing the result.
     */
    fun lerp(other: IVector, t: Float): Vector = lerp(other, t, Vector())

    /** Linearly interpolates between this and the supplied other vector by the supplied amount,
     * storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun lerp(other: IVector, t: Float, result: Vector): Vector {
        val x = x
        val y = y
        val dx = other.x - x
        val dy = other.y - y
        return result.set(x + t * dx, y + t * dy)
    }

    /** Returns a mutable copy of this vector.  */
    fun copy(x: Float = this.x, y: Float = this.y): Vector
}
