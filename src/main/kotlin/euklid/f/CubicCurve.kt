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
 * Represents a cubic curve.
 */
data class CubicCurve(
        /** The x-coordinate of the start of this curve.  */
        override var x1: Float = 0f,
        /** The y-coordinate of the start of this curve.  */
        override var y1: Float = 0f,
        /** The x-coordinate of the first control point.  */
        override var ctrlX1: Float = 0f,
        /** The y-coordinate of the first control point.  */
        override var ctrlY1: Float = 0f,
        /** The x-coordinate of the second control point.  */
        override var ctrlX2: Float = 0f,
        /** The x-coordinate of the second control point.  */
        override var ctrlY2: Float = 0f,
        /** The x-coordinate of the end of this curve.  */
        override var x2: Float = 0f,
        /** The y-coordinate of the end of this curve.  */
        override var y2: Float = 0f
) : ICubicCurve {

    /**
     * Configures the start, control and end points for this curve.
     */
    fun setCurve(x1: Float, y1: Float, ctrlx1: Float, ctrly1: Float, ctrlx2: Float,
                 ctrly2: Float, x2: Float, y2: Float) {
        this.x1 = x1
        this.y1 = y1
        this.ctrlX1 = ctrlx1
        this.ctrlY1 = ctrly1
        this.ctrlX2 = ctrlx2
        this.ctrlY2 = ctrly2
        this.x2 = x2
        this.y2 = y2
    }

    /**
     * Configures the start, control and end points for this curve.
     */
    fun setCurve(p1: XY, cp1: XY, cp2: XY, p2: XY) {
        setCurve(p1.x, p1.y, cp1.x, cp1.y,
                cp2.x, cp2.y, p2.x, p2.y)
    }

    /**
     * Configures the start, control and end points for this curve, using the values at the
     * specified offset in the `coords` array.
     */
    fun setCurve(coords: FloatArray, offset: Int) {
        setCurve(coords[offset + 0], coords[offset + 1], coords[offset + 2], coords[offset + 3],
                coords[offset + 4], coords[offset + 5], coords[offset + 6], coords[offset + 7])
    }

    /**
     * Configures the start, control and end points for this curve, using the values at the
     * specified offset in the `points` array.
     */
    fun setCurve(points: Array<XY>, offset: Int) {
        setCurve(points[offset + 0].x, points[offset + 0].y,
                points[offset + 1].x, points[offset + 1].y,
                points[offset + 2].x, points[offset + 2].y,
                points[offset + 3].x, points[offset + 3].y)
    }

    /**
     * Configures the start, control and end points for this curve to be the same as the supplied
     * curve.
     */
    fun setCurve(curve: ICubicCurve) {
        setCurve(curve.x1, curve.y1, curve.ctrlX1, curve.ctrlY1,
                curve.ctrlX2, curve.ctrlY2, curve.x2, curve.y2)
    }
}
