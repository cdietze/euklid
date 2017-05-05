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



package pythagoras.f

/**
 * Defines an x/y coordinate. This is implemented by both `Point` and `Vector` so that
 * APIs which require an x/y coordinate, but don't really want to mak the distinction between a
 * translation vector versus a point in 2D space, can simply accept both.
 */
interface XY {
    /** The x coordinate.  */
    val x: Float

    /** The y coordinate.  */
    val y: Float
}
