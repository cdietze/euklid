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
 * Dimension-related utility methods.
 */
object Dimensions {
    /** A dimension width zero width and height.  */
    val ZERO: IDimension = Dimension(0f, 0f)

    /**
     * Returns a string describing the supplied dimension, of the form `widthxheight`.
     */
    fun dimenToString(width: Float, height: Float): String {
        return width.toString() + "x" + height
    }
}
