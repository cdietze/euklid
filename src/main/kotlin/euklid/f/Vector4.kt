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
 * A four element vector.
 */
data class Vector4(
    override var x: Float = 0f,
    override var y: Float = 0f,
    override var z: Float = 0f,
    override var w: Float = 0f
) : IVector4 {

    /**
     * Creates a vector from four components.
     */
    constructor(values: FloatArray) : this(values[0], values[1], values[2], values[3])

    constructor(v: IVector4) : this(v.x, v.y, v.z, v.w)

    /**
     * Copies the elements of another vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(other: IVector4): Vector4 = set(other.x, other.y, other.z, other.w)

    /**
     * Sets all of the elements of the vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(values: FloatArray): Vector4 = set(values[0], values[1], values[2], values[3])

    /**
     * Sets all of the elements of the vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(x: Float, y: Float, z: Float, w: Float): Vector4 {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
        return this
    }

    /**
     * Negates this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun negateLocal(): Vector4 = negate(this)

    /**
     * Absolute-values this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun absLocal(): Vector4 = abs(this)

    /**
     * Multiplies this vector by a scalar and stores the result back in this vector.
     * @return a reference to this vector, for chaining.
     */
    fun multLocal(v: Float): Vector4 = mult(v, this)

    /**
     * Multiplies this vector by a matrix (V * M) and stores the result back in this vector.
     * @return a reference to this vector, for chaining.
     */
    fun multLocal(matrix: IMatrix4): Vector4 = mult(matrix, this)

    override fun toString(): String = "[$x, $y, $z, $w]"
}
