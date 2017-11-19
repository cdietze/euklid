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

import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Rectangle-related utility methods.
 */
object Rectangles {
    /**
     * Intersects the supplied two rectangles, writing the result into `dst`.
     */
    fun intersect(src1: IRectangle, src2: IRectangle, dst: Rectangle) {
        val x1 = max(src1.minX, src2.minX)
        val y1 = max(src1.minY, src2.minY)
        val x2 = min(src1.maxX, src2.maxX)
        val y2 = min(src1.maxY, src2.maxY)
        dst.setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    /**
     * Unions the supplied two rectangles, writing the result into `dst`.
     */
    fun union(src1: IRectangle, src2: IRectangle, dst: Rectangle) {
        val x1 = min(src1.minX, src2.minX)
        val y1 = min(src1.minY, src2.minY)
        val x2 = max(src1.maxX, src2.maxX)
        val y2 = max(src1.maxY, src2.maxY)
        dst.setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    /**
     * Computes the point inside the bounds of the rectangle that's closest to the given point,
     * writing the result into `out`.
     * @return `out` for call chaining convenience.
     */
    fun closestInteriorPoint(r: IRectangle, p: IPoint, out: Point = Point()): Point {
        out.set(MathUtil.clamp(p.x, r.minX, r.maxX),
                MathUtil.clamp(p.y, r.minY, r.maxY))
        return out
    }

    /**
     * Returns the squared Euclidean distance between the given point and the nearest point inside
     * the bounds of the given rectangle. If the supplied point is inside the rectangle, the
     * distance will be zero.
     */
    fun pointRectDistanceSq(r: IRectangle, p: IPoint): Float {
        val p2 = closestInteriorPoint(r, p)
        return Points.distanceSq(p.x, p.y, p2.x, p2.y)
    }

    /**
     * Returns the Euclidean distance between the given point and the nearest point inside the
     * bounds of the given rectangle. If the supplied point is inside the rectangle, the distance
     * will be zero.
     */
    fun pointRectDistance(r: IRectangle, p: IPoint): Float {
        return sqrt(pointRectDistanceSq(r, p))
    }
}
/**
 * Computes and returns the point inside the bounds of the rectangle that's closest to the
 * given point.
 */
