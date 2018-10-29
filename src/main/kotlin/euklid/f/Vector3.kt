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

/**
 * A three element vector.
 */
data class Vector3(
    override var x: Float = 0f,
    override var y: Float = 0f,
    override var z: Float = 0f
) : IVector3 {

    /**
     * Creates a vector from an array of values.
     */
    constructor(values: FloatArray) : this(values[0], values[1], values[2])

    constructor(v: IVector3) : this(v.x, v.y, v.z)

    /**
     * Computes the cross product of this and the specified other vector, storing the result
     * in this vector.
     * @return a reference to this vector, for chaining.
     */
    fun crossLocal(other: IVector3): Vector3 = cross(other, this)

    /**
     * Negates this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun negateLocal(): Vector3 = negate(this)

    /**
     * Absolute-values this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun absLocal(): Vector3 = abs(this)

    /**
     * Normalizes this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun normalizeLocal(): Vector3 = normalize(this)

    /**
     * Multiplies this vector in-place by a scalar.
     * @return a reference to this vector, for chaining.
     */
    fun multLocal(v: Float): Vector3 = mult(v, this)

    /**
     * Multiplies this vector in-place by another.
     * @return a reference to this vector, for chaining.
     */
    fun multLocal(other: IVector3): Vector3 = mult(other, this)

    /**
     * Adds a vector in-place to this one.
     * @return a reference to this vector, for chaining.
     */
    fun addLocal(other: IVector3): Vector3 = add(other, this)

    /**
     * Subtracts a vector in-place from this one.
     * @return a reference to this vector, for chaining.
     */
    fun subtractLocal(other: IVector3): Vector3 = subtract(other, this)

    /**
     * Adds a vector in-place to this one.
     * @return a reference to this vector, for chaining.
     */
    fun addLocal(x: Float, y: Float, z: Float): Vector3 = add(x, y, z, this)

    /**
     * Adds a scaled vector in-place to this one.
     * @return a reference to this vector, for chaining.
     */
    fun addScaledLocal(other: IVector3, v: Float): Vector3 = addScaled(other, v, this)

    /**
     * Linearly interpolates between this and the specified other vector in-place by the supplied
     * amount.
     * @return a reference to this vector, for chaining.
     */
    fun lerpLocal(other: IVector3, t: Float): Vector3 = lerp(other, t, this)

    /**
     * Copies the elements of another vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(other: IVector3): Vector3 = set(other.x, other.y, other.z)

    /**
     * Copies the elements of an array.
     * @return a reference to this vector, for chaining.
     */
    fun set(values: FloatArray): Vector3 = set(values[0], values[1], values[2])

    /**
     * Sets all of the elements of the vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(x: Float, y: Float, z: Float): Vector3 {
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    override fun toString(): String = "[$x, $y, $z]"

    companion object {
        /** A unit vector in the X+ direction.  */
        val UNIT_X: IVector3 = Vector3(1f, 0f, 0f)

        /** A unit vector in the Y+ direction.  */
        val UNIT_Y: IVector3 = Vector3(0f, 1f, 0f)

        /** A unit vector in the Z+ direction.  */
        val UNIT_Z: IVector3 = Vector3(0f, 0f, 1f)

        /** A vector containing unity for all components.  */
        val UNIT_XYZ: IVector3 = Vector3(1f, 1f, 1f)

        /** A normalized version of UNIT_XYZ.  */
        val NORMAL_XYZ: IVector3 = UNIT_XYZ.normalize()

        /** The zero vector.  */
        val ZERO: IVector3 = Vector3(0f, 0f, 0f)

        /** A vector containing the minimum floating point value for all components
         * (note: the components are -[Float.MAX_VALUE], not [Float.MIN_VALUE]).  */
        val MIN_VALUE: IVector3 = Vector3(-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE)

        /** A vector containing the maximum floating point value for all components.  */
        val MAX_VALUE: IVector3 = Vector3(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)
    }
}
