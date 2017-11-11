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

/**
 * Represents an ellipse that is described by a framing rectangle.
 */
data class Ellipse(
        /** The x-coordinate of the framing rectangle.  */
        override var x: Float = 0f,
        /** The y-coordinate of the framing rectangle.  */
        override var y: Float = 0f,
        /** The width of the framing rectangle.  */
        override var width: Float = 0f,
        /** The height of the framing rectangle.  */
        override var height: Float = 0f
) : RectangularShape(), IEllipse {

    override fun setFrame(x: Float, y: Float, width: Float, height: Float) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    companion object {
        private const val serialVersionUID = -1205529661373764424L
    }
}
