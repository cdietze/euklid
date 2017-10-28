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

import pythagoras.util.Platform

/**
 * Provides most of the implementation of [ICircle], obtaining only the location and radius
 * from the derived class.
 */
abstract class AbstractCircle : ICircle {
    override // from ICircle
    fun intersects(c: ICircle): Boolean {
        val maxDist = radius + c.radius
        return Points.distanceSq(x, y, c.x, c.y) < maxDist * maxDist
    }

    override // from ICircle
    fun contains(p: XY): Boolean {
        val r = radius
        return Points.distanceSq(x, y, p.x, p.y) < r * r
    }

    override // from ICircle
    fun contains(x: Float, y: Float): Boolean {
        val r = radius
        return Points.distanceSq(x, y, x, y) < r * r
    }

    override // from ICircle
    fun offset(x: Float, y: Float): Circle {
        return Circle(x + x, y + y, radius)
    }

    override // from ICircle
    fun offset(x: Float, y: Float, result: Circle): Circle {
        result.set(x + x, y + y, radius)
        return result
    }

    override // from ICircle
    fun clone(): Circle {
        return Circle(this)
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other is AbstractCircle) {
            val c = other
            return x == c.x && y == c.y && radius == c.radius
        }
        return false
    }

    override fun hashCode(): Int {
        return Platform.hashCode(x) xor Platform.hashCode(y) xor Platform.hashCode(radius)
    }
}
