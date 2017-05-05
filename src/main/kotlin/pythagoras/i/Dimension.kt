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
 * Represents a magnitude in two dimensions.
 */
class Dimension
/**
 * Creates a dimension with the specified width and height.
 */
constructor(
        /** The magnitude in the x-dimension.  */
        override var width: Int = 0,
        /** The magnitude in the y-dimension.  */
        override var height: Int = 0
) : AbstractDimension() {

    /**
     * Creates a dimension with width and height equal to the supplied dimension.
     */
    constructor(d: IDimension) : this(d.width, d.height) {}

    /**
     * Sets the magnitudes of this dimension to the specified width and height.
     */
    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    /**
     * Sets the magnitudes of this dimension to be equal to the supplied dimension.
     */
    fun setSize(d: IDimension) {
        setSize(d.width, d.height)
    }

    companion object {
        private const val serialVersionUID = 5773214044931265346L
    }
}
/**
 * Creates a dimension with magnitude (0, 0).
 */
