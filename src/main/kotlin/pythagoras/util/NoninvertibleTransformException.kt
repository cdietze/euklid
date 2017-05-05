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



package pythagoras.util

/**
 * An exception thrown by `Transform` when a request for an inverse transform cannot be
 * satisfied.
 */
class NoninvertibleTransformException(s: String) : RuntimeException(s) {
    companion object {
        private val serialVersionUID = 5208863644264280750L
    }
}
