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

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.sqrt

/**
 * Provides read-only access to a [Vector3].
 */
interface IVector3 {
    /** Returns the x-component of this vector.  */
    val x: Float

    /** Returns the y-component of this vector.  */
    val y: Float

    /** Returns the z-component of this vector.  */
    val z: Float

    /**
     * Computes and returns the dot product of this and the specified other vector.
     */
    fun dot(other: IVector3): Float = x * other.x + y * other.y + z * other.z

    /**
     * Computes the cross product of this and the specified other vector.
     * @return a new vector containing the result.
     */
    fun cross(other: IVector3): Vector3 = cross(other, Vector3())

    /**
     * Computes the cross product of this and the specified other vector, placing the result
     * in the object supplied.
     * @return a reference to the result, for chaining.
     */
    fun cross(other: IVector3, result: Vector3): Vector3 {
        val x = this.x
        val y = this.y
        val z = this.z
        val ox = other.x
        val oy = other.y
        val oz = other.z
        return result.set(y * oz - z * oy, z * ox - x * oz, x * oy - y * ox)
    }

    /**
     * Computes the triple product of this and the specified other vectors, which is equal to
     * `this.dot(b.cross(c))`.
     */
    fun triple(b: IVector3, c: IVector3): Float {
        val bx = b.x
        val by = b.y
        val bz = b.z
        val cx = c.x
        val cy = c.y
        val cz = c.z
        return x * (by * cz - bz * cy) + y * (bz * cx - bx * cz) + z * (bx * cy - by * cx)
    }

    /**
     * Negates this vector.
     * @return a new vector containing the result.
     */
    fun negate(): Vector3 = negate(Vector3())

    /**
     * Negates this vector, storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun negate(result: Vector3): Vector3 = result.set(-x, -y, -z)

    /**
     * Absolute-values this vector.
     * @return a new vector containing the result.
     */
    fun abs(): Vector3 = abs(Vector3())

    /**
     * Absolute-values this vector, storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun abs(result: Vector3): Vector3 = result.set(abs(x), abs(y), abs(z))

    /**
     * Normalizes this vector.
     * @return a new vector containing the result.
     */
    fun normalize(): Vector3 = normalize(Vector3())

    /**
     * Normalizes this vector, storing the result in the object supplied.
     * @return a reference to the result, for chaining.
     */
    fun normalize(result: Vector3): Vector3 = mult(1f / length(), result)

    /**
     * Returns the angle between this vector and the specified other vector.
     */
    fun angle(other: IVector3): Float = acos(dot(other) / (length() * other.length()))

    /**
     * Returns the length of this vector.
     */
    fun length(): Float = sqrt(lengthSquared())

    /**
     * Returns the squared length of this vector.
     */
    fun lengthSquared(): Float {
        val x = this.x
        val y = this.y
        val z = this.z
        return x * x + y * y + z * z
    }

    /**
     * Returns the distance from this vector to the specified other vector.
     */
    fun distance(other: IVector3): Float = sqrt(distanceSquared(other))

    /**
     * Returns the squared distance from this vector to the specified other.
     */
    fun distanceSquared(other: IVector3): Float {
        val dx = x - other.x
        val dy = y - other.y
        val dz = z - other.z
        return dx * dx + dy * dy + dz * dz
    }

    /**
     * Returns the Manhattan distance between this vector and the specified other.
     */
    fun manhattanDistance(other: IVector3): Float = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    /**
     * Multiplies this vector by a scalar.
     * @return a new vector containing the result.
     */
    fun mult(v: Float): Vector3 = mult(v, Vector3())

    /**
     * Multiplies this vector by a scalar and places the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun mult(v: Float, result: Vector3): Vector3 = result.set(x * v, y * v, z * v)

    /**
     * Multiplies this vector by another.
     * @return a new vector containing the result.
     */
    fun mult(other: IVector3): Vector3 = mult(other, Vector3())

    /**
     * Multiplies this vector by another, storing the result in the object provided.
     * @return a reference to the result vector, for chaining.
     */
    fun mult(other: IVector3, result: Vector3): Vector3 = result.set(x * other.x, y * other.y, z * other.z)

    /**
     * Adds a vector to this one.
     * @return a new vector containing the result.
     */
    fun add(other: IVector3): Vector3 = add(x, y, z, Vector3())

    /**
     * Adds a vector to this one, storing the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(other: IVector3, result: Vector3): Vector3 = add(other.x, other.y, other.z, result)

    /**
     * Adds a vector to this one.
     * @return a new vector containing the result.
     */
    fun add(x: Float, y: Float, z: Float): Vector3 = add(x, y, z, Vector3())

    /**
     * Adds a vector to this one and stores the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(x: Float, y: Float, z: Float, result: Vector3): Vector3 = result.set(this.x + x, this.y + y, this.z + z)

    /**
     * Adds a scaled vector to this one.
     * @return a new vector containing the result.
     */
    fun addScaled(other: IVector3, v: Float): Vector3 = addScaled(other, v, Vector3())

    /**
     * Adds a scaled vector to this one and stores the result in the supplied vector.
     * @return a reference to the result, for chaining.
     */
    fun addScaled(other: IVector3, v: Float, result: Vector3): Vector3 =
            result.set(x + other.x * v, y + other.y * v, z + other.z * v)

    /**
     * Subtracts a vector from this one.
     * @return a new vector containing the result.
     */
    fun subtract(other: IVector3): Vector3 = subtract(other, Vector3())

    /**
     * Subtracts a vector from this one and places the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun subtract(other: IVector3, result: Vector3): Vector3 = add(-other.x, -other.y, -other.z, result)

    /**
     * Linearly interpolates between this and the specified other vector by the supplied amount.
     * @return a new vector containing the result.
     */
    fun lerp(other: IVector3, t: Float): Vector3 = lerp(other, t, Vector3())

    /**
     * Linearly interpolates between this and the supplied other vector by the supplied amount,
     * storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun lerp(other: IVector3, t: Float, result: Vector3): Vector3 {
        val x = this.x
        val y = this.y
        val z = this.z
        return result.set(x + t * (other.x - x), y + t * (other.y - y), z + t * (other.z - z))
    }

    /**
     * Returns the element at the idx'th position of the vector.
     */
    operator fun get(idx: Int): Float {
        when (idx) {
            0 -> return x
            1 -> return y
            2 -> return z
        }
        throw IndexOutOfBoundsException(idx.toString())
    }

    /**
     * Populates the supplied array with the contents of this vector.
     */
    fun get(values: FloatArray) {
        values[0] = x
        values[1] = y
        values[2] = z
    }

    /** Returns a mutable copy of this vector.  */
    fun copy(x: Float = this.x, y: Float = this.y, z: Float = this.z): Vector3
}
