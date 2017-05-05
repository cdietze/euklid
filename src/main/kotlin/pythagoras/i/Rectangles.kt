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

import java.lang.Math

/**
 * Rectangle-related utility methods.
 */
object Rectangles {
    /**
     * Intersects the supplied two rectangles, writing the result into `dst`.
     */
    fun intersect(src1: IRectangle, src2: IRectangle, dst: Rectangle) {
        val x1 = Math.max(src1.minX(), src2.minX())
        val y1 = Math.max(src1.minY(), src2.minY())
        val x2 = Math.min(src1.maxX(), src2.maxX())
        val y2 = Math.min(src1.maxY(), src2.maxY())
        dst.setBounds(x1, y1, x2 - x1, y2 - y1)
    }

    /**
     * Unions the supplied two rectangles, writing the result into `dst`.
     */
    fun union(src1: IRectangle, src2: IRectangle, dst: Rectangle) {
        val x1 = Math.min(src1.minX(), src2.minX())
        val y1 = Math.min(src1.minY(), src2.minY())
        val x2 = Math.max(src1.maxX(), src2.maxX())
        val y2 = Math.max(src1.maxY(), src2.maxY())
        dst.setBounds(x1, y1, x2 - x1, y2 - y1)
    }
}
