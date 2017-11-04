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

package pythagoras.i

/**
 * Provides read-only access to a [Dimension].
 */
interface IDimension {
    /**
     * Returns the magnitude in the x-dimension.
     */
    val width: Int

    /**
     * Returns the magnitude in the y-dimension.
     */
    val height: Int

    /**
     * Returns a mutable copy of this dimension.
     */
    fun copy(width: Int = this.width, height: Int = this.height): Dimension
}
