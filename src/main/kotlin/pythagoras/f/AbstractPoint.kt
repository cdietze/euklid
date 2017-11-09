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

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/**
 * Provides most of the implementation of [IPoint], obtaining only the location from the
 * derived class.
 */
abstract class AbstractPoint : IPoint {
    override
    fun distanceSq(px: Float, py: Float): Float {
        return Points.distanceSq(x, y, px, py)
    }

    override
    fun distanceSq(p: XY): Float {
        return Points.distanceSq(x, y, p.x, p.y)
    }

    override
    fun distance(px: Float, py: Float): Float {
        return Points.distance(x, y, px, py)
    }

    override
    fun distance(p: XY): Float {
        return Points.distance(x, y, p.x, p.y)
    }

    override
    fun direction(other: XY): Float {
        return atan2(other.y - y, other.x - x)
    }

    override
    fun mult(s: Float): Point {
        return mult(s, Point())
    }

    override
    fun mult(s: Float, result: Point): Point {
        return result.set(x * s, y * s)
    }

    override
    fun add(x: Float, y: Float): Point {
        return Point(this.x + x, this.y + y)
    }

    override
    fun add(x: Float, y: Float, result: Point): Point {
        return result.set(this.x + x, this.y + y)
    }

    override
    fun add(other: XY, result: Point): Point {
        return add(other.x, other.y, result)
    }

    override
    fun subtract(x: Float, y: Float): Point {
        return subtract(x, y, Point())
    }

    override
    fun subtract(x: Float, y: Float, result: Point): Point {
        return result.set(this.x - x, this.y - y)
    }

    override
    fun subtract(other: XY, result: Point): Point {
        return subtract(other.x, other.y, result)
    }

    override
    fun rotate(angle: Float): Point {
        return rotate(angle, Point())
    }

    override
    fun rotate(angle: Float, result: Point): Point {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set(x * cosa - y * sina, x * sina + y * cosa)
    }

    override fun toString(): String {
        return Points.pointToString(x, y)
    }
}
