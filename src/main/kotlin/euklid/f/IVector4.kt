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
 * Provides read-only access to a [Vector4].
 */
interface IVector4 {
    /** Returns the x-component of this vector.  */
    val x: Float

    /** Returns the y-component of this vector.  */
    val y: Float

    /** Returns the z-component of this vector.  */
    val z: Float

    /** Returns the w-component of this vector.  */
    val w: Float

    /**
     * Compares this vector to another with the provided epsilon.
     */
    fun epsilonEquals(other: IVector4, epsilon: Float): Boolean {
        return kotlin.math.abs(x - other.x) < epsilon &&
                kotlin.math.abs(y - other.y) < epsilon &&
                kotlin.math.abs(z - other.z) < epsilon &&
                kotlin.math.abs(w - other.w) < epsilon
    }

    /**
     * Negates this vector.
     * @return a new vector containing the result.
     */
    fun negate(): Vector4 = negate(Vector4())

    /**
     * Negates this vector, storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun negate(result: Vector4): Vector4 = result.set(-x, -y, -z, -w)

    /**
     * Absolute-values this vector.
     * @return a new vector containing the result.
     */
    fun abs(): Vector4 = abs(Vector4())

    /**
     * Absolute-values this vector, storing the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun abs(result: Vector4): Vector4 =
            result.set(kotlin.math.abs(x), kotlin.math.abs(y), kotlin.math.abs(z), kotlin.math.abs(w))

    /**
     * Multiplies this vector by a scalar.
     * @return a new vector containing the result.
     */
    fun mult(v: Float): Vector4 = mult(v, Vector4())

    /**
     * Multiplies this vector by a scalar and places the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun mult(v: Float, result: Vector4): Vector4 = result.set(x * v, y * v, z * v, w * v)

    /**
     * Multiplies this vector by a matrix (V * M).
     * @return a new vector containing the result.
     */
    fun mult(matrix: IMatrix4): Vector4 = mult(matrix, Vector4())

    /**
     * Multiplies this vector by a matrix (V * M) and stores the result in the object provided.
     * @return a reference to the result vector, for chaining.
     */
    fun mult(matrix: IMatrix4, result: Vector4): Vector4 {
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

    /** Returns a mutable copy of this vector.  */
    fun copy(x: Float = this.x, y: Float = this.y, z: Float = this.z, w: Float = this.w): Vector4
}
