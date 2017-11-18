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

import kotlin.math.abs

/**
 * A four element vector.
 */
class Vector4 : IVector4 {

    /** The components of the vector.  */
    override var x: Float = 0f
    override var y: Float = 0f
    override var z: Float = 0f
    override var w: Float = 0f

    /**
     * Creates a vector from four components.
     */
    constructor(x: Float, y: Float, z: Float, w: Float) {
        set(x, y, z, w)
    }

    /**
     * Creates a vector from four components.
     */
    constructor(values: FloatArray) {
        set(values)
    }

    /**
     * Copy constructor.
     */
    constructor(other: IVector4) {
        set(other)
    }

    /**
     * Creates a zero vector.
     */
    constructor()

    /**
     * Copies the elements of another vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(other: IVector4): Vector4 {
        return set(other.x, other.y, other.z, other.w)
    }

    /**
     * Sets all of the elements of the vector.
     * @return a reference to this vector, for chaining.
     */
    fun set(values: FloatArray): Vector4 {
        return set(values[0], values[1], values[2], values[3])
    }

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
    fun negateLocal(): Vector4 {
        return negate(this)
    }

    /**
     * Absolute-values this vector in-place.
     * @return a reference to this vector, for chaining.
     */
    fun absLocal(): Vector4 {
        return abs(this)
    }

    /**
     * Multiplies this vector by a scalar and stores the result back in this vector.
     * @return a reference to this vector, for chaining.
     */
    fun multLocal(v: Float): Vector4 {
        return mult(v, this)
    }

    /**
     * Multiplies this vector by a matrix (V * M) and stores the result back in this vector.
     * @return a reference to this vector, for chaining.
     */
    fun multLocal(matrix: IMatrix4): Vector4 {
        return mult(matrix, this)
    }

    override fun epsilonEquals(other: IVector4, epsilon: Float): Boolean {
        return abs(x - other.x) < epsilon &&
                abs(y - other.y) < epsilon &&
                abs(z - other.z) < epsilon &&
                abs(w - other.w) < epsilon
    }

    override fun negate(): Vector4 {
        return negate(Vector4())
    }

    override fun negate(result: Vector4): Vector4 {
        return result.set(-x, -y, -z, -w)
    }

    override fun abs(): Vector4 {
        return abs(Vector4())
    }

    override fun abs(result: Vector4): Vector4 {
        return result.set(abs(x), abs(y), abs(z), abs(w))
    }

    override fun mult(v: Float): Vector4 {
        return mult(v, Vector4())
    }

    override fun mult(v: Float, result: Vector4): Vector4 {
        return result.set(x * v, y * v, z * v, w * v)
    }

    override fun mult(matrix: IMatrix4): Vector4 {
        return mult(matrix, Vector4())
    }

    override fun mult(matrix: IMatrix4, result: Vector4): Vector4 {
        val m00 = matrix.m00
        val m10 = matrix.m10
        val m20 = matrix.m20
        val m30 = matrix.m30
        val m01 = matrix.m01
        val m11 = matrix.m11
        val m21 = matrix.m21
        val m31 = matrix.m31
        val m02 = matrix.m02
        val m12 = matrix.m12
        val m22 = matrix.m22
        val m32 = matrix.m32
        val m03 = matrix.m03
        val m13 = matrix.m13
        val m23 = matrix.m23
        val m33 = matrix.m33
        val vx = x
        val vy = y
        val vz = z
        val vw = w
        return result.set(m00 * vx + m01 * vy + m02 * vz + m03 * vw,
                m10 * vx + m11 * vy + m12 * vz + m13 * vw,
                m20 * vx + m21 * vy + m22 * vz + m23 * vw,
                m30 * vx + m31 * vy + m32 * vz + m33 * vw)
    }

    override fun toString(): String {
        return "[$x, $y, $z, $w]"
    }

    override fun hashCode(): Int {
        return x.hashCode() xor y.hashCode() xor z.hashCode() xor
                w.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Vector4) {
            return false
        }
        val ovec = other
        return x == ovec.x && y == ovec.y && z == ovec.z && w == ovec.w
    }
}
