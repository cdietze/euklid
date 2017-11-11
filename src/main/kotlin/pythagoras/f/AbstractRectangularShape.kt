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
 * The base class for various [IShape] objects whose geometry is defined by a rectangular
 * frame.
 */
abstract class AbstractRectangularShape : IRectangularShape {
    /**
     * Sets the location and size of the framing rectangle of this shape to the specified values.
     */
    abstract fun setFrame(x: Float, y: Float, width: Float, height: Float)

    /**
     * Sets the location and size of the framing rectangle of this shape to the supplied values.
     */
    fun setFrame(loc: XY, size: IDimension) {
        setFrame(loc.x, loc.y, size.width, size.height)
    }

    /**
     * Sets the location and size of the framing rectangle of this shape to be equal to the
     * supplied rectangle.
     */
    fun setFrame(rect: IRectangle) {
        setFrame(rect.x, rect.y, rect.width, rect.height)
    }

    /**
     * Sets the location and size of the framing rectangle of this shape based on the specified
     * diagonal line.
     */
    fun setFrameFromDiagonal(x1: Float, y1: Float, x2: Float, y2: Float) {
        val rx: Float
        val ry: Float
        val rw: Float
        val rh: Float
        if (x1 < x2) {
            rx = x1
            rw = x2 - x1
        } else {
            rx = x2
            rw = x1 - x2
        }
        if (y1 < y2) {
            ry = y1
            rh = y2 - y1
        } else {
            ry = y2
            rh = y1 - y2
        }
        setFrame(rx, ry, rw, rh)
    }

    /**
     * Sets the location and size of the framing rectangle of this shape based on the supplied
     * diagonal line.
     */
    fun setFrameFromDiagonal(p1: XY, p2: XY) {
        setFrameFromDiagonal(p1.x, p1.y, p2.x, p2.y)
    }

    /**
     * Sets the location and size of the framing rectangle of this shape based on the specified
     * center and corner points.
     */
    fun setFrameFromCenter(centerX: Float, centerY: Float,
                           cornerX: Float, cornerY: Float) {
        val width = abs(cornerX - centerX)
        val height = abs(cornerY - centerY)
        setFrame(centerX - width, centerY - height, width * 2, height * 2)
    }

    /**
     * Sets the location and size of the framing rectangle of this shape based on the supplied
     * center and corner points.
     */
    fun setFrameFromCenter(center: XY, corner: XY) {
        setFrameFromCenter(center.x, center.y, corner.x, corner.y)
    }
}
