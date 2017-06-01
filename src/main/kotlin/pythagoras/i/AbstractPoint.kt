/*
 * Copyright 2017 The Pythagoras.kt Authors
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



package pythagoras.i

/**
 * Provides most of the implementation of [IPoint], obtaining only the location from the
 * derived class.
 */
abstract class AbstractPoint : IPoint {
    override // from IPoint
    fun distanceSq(px: Int, py: Int): Int {
        return Points.distanceSq(x, y, px, py)
    }

    override // from IPoint
    fun distanceSq(p: IPoint): Int {
        return Points.distanceSq(x, y, p.x, p.y)
    }

    override // from IPoint
    fun distance(px: Int, py: Int): Int {
        return Points.distance(x, y, px, py)
    }

    override // from IPoint
    fun distance(p: IPoint): Int {
        return Points.distance(x, y, p.x, p.y)
    }

    override // from IPoint
    fun add(x: Int, y: Int): Point {
        return Point(x + x, y + y)
    }

    override // from IPoint
    fun add(x: Int, y: Int, result: Point): Point {
        return result.set(x + x, y + y)
    }

    override // from IPoint
    fun subtract(x: Int, y: Int): Point {
        return subtract(x, y, Point())
    }

    override fun subtract(x: Int, y: Int, result: Point): Point {
        return result.set(x - x, y - y)
    }

    override fun subtract(other: IPoint, result: Point): Point {
        return subtract(other.x, other.y, result)
    }

    override // from interface IPoint
    fun clone(): Point {
        return Point(this)
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other is AbstractPoint) {
            val p = other
            return x == p.x && y == p.y
        }
        return false
    }

    override fun hashCode(): Int {
        return x xor y
    }

    override fun toString(): String {
        return Points.pointToString(x, y)
    }
}
