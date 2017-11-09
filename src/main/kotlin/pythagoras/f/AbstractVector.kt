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

import kotlin.math.*

/**
 * Provides most of the implementation of [IVector], obtaining only x and y from the derived
 * class.
 */
abstract class AbstractVector : IVector {
    override
    fun dot(other: IVector): Float {
        return x * other.x + y * other.y
    }

    override
    fun cross(other: IVector): Vector {
        return cross(other, Vector())
    }

    override
    fun cross(other: IVector, result: Vector): Vector {
        val x = x
        val y = y
        val ox = other.x
        val oy = other.y
        return result.set(y * ox - x * oy, x * oy - y * ox)
    }

    override
    fun negate(): Vector {
        return negate(Vector())
    }

    override
    fun negate(result: Vector): Vector {
        return result.set(-x, -y)
    }

    override
    fun normalize(): Vector {
        return normalize(Vector())
    }

    override
    fun normalize(result: Vector): Vector {
        return scale(1f / length(), result)
    }

    override
    fun length(): Float {
        return sqrt(lengthSq())
    }

    override
    fun lengthSq(): Float {
        val x = x
        val y = y
        return x * x + y * y
    }

    override
    val isZero: Boolean
        get() = Vectors.isZero(x, y)

    override
    fun distance(other: IVector): Float {
        return sqrt(distanceSq(other))
    }

    override
    fun distanceSq(other: IVector): Float {
        val dx = x - other.x
        val dy = y - other.y
        return dx * dx + dy * dy
    }

    override
    fun angle(): Float {
        return atan2(y, x)
    }

    override
    fun angleBetween(other: IVector): Float {
        val cos = dot(other) / (length() * other.length())
        return if (cos >= 1f) 0f else acos(cos)
    }

    override
    fun scale(v: Float): Vector {
        return scale(v, Vector())
    }

    override
    fun scale(v: Float, result: Vector): Vector {
        return result.set(x * v, y * v)
    }

    override
    fun scale(other: IVector): Vector {
        return scale(other, Vector())
    }

    override
    fun scale(other: IVector, result: Vector): Vector {
        return result.set(x * other.x, y * other.y)
    }

    override
    fun add(other: IVector): Vector {
        return add(other, Vector())
    }

    override
    fun add(other: IVector, result: Vector): Vector {
        return add(other.x, other.y, result)
    }

    override
    fun subtract(other: IVector): Vector {
        return subtract(other, Vector())
    }

    override
    fun subtract(other: IVector, result: Vector): Vector {
        return add(-other.x, -other.y, result)
    }

    override
    fun add(x: Float, y: Float): Vector {
        return add(x, y, Vector())
    }

    override
    fun add(x: Float, y: Float, result: Vector): Vector {
        return result.set(this.x + x, this.y + y)
    }

    override
    fun subtract(x: Float, y: Float): Vector {
        return subtract(x, y, Vector())
    }

    override
    fun subtract(x: Float, y: Float, result: Vector): Vector {
        return result.set(this.x - x, this.y - y)
    }

    override
    fun addScaled(other: IVector, v: Float): Vector {
        return addScaled(other, v, Vector())
    }

    override
    fun addScaled(other: IVector, v: Float, result: Vector): Vector {
        return result.set(x + other.x * v, y + other.y * v)
    }

    override
    fun rotate(angle: Float): Vector {
        return rotate(angle, Vector())
    }

    override
    fun rotate(angle: Float, result: Vector): Vector {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set(x * cosa - y * sina, x * sina + y * cosa)
    }

    override
    fun rotateAndAdd(angle: Float, add: IVector, result: Vector): Vector {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set(x * cosa - y * sina + add.x, x * sina + y * cosa + add.y)
    }

    override
    fun rotateScaleAndAdd(angle: Float, scale: Float, add: IVector, result: Vector): Vector {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set((x * cosa - y * sina) * scale + add.x,
                (x * sina + y * cosa) * scale + add.y)
    }

    override
    fun lerp(other: IVector, t: Float): Vector {
        return lerp(other, t, Vector())
    }

    override
    fun lerp(other: IVector, t: Float, result: Vector): Vector {
        val x = x
        val y = y
        val dx = other.x - x
        val dy = other.y - y
        return result.set(x + t * dx, y + t * dy)
    }

    override fun toString(): String {
        return Vectors.vectorToString(x, y)
    }
}
