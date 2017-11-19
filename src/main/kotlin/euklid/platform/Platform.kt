package euklid.platform

/** Copies `length` elements from `src` array starting at index `srcPos` into `dest` array starting at index `destPos` */
expect fun arrayCopy(src: ByteArray, srcPos: Int, dest: ByteArray, destPos: Int, length: Int)

/** Copies `length` elements from `src` array starting at index `srcPos` into `dest` array starting at index `destPos` */
expect fun arrayCopy(src: IntArray, srcPos: Int, dest: IntArray, destPos: Int, length: Int)

/** Copies `length` elements from `src` array starting at index `srcPos` into `dest` array starting at index `destPos` */
expect fun arrayCopy(src: FloatArray, srcPos: Int, dest: FloatArray, destPos: Int, length: Int)

/** Copies `length` elements from `src` array starting at index `srcPos` into `dest` array starting at index `destPos` */
expect fun <T> arrayCopy(src: Array<T>, srcPos: Int, dest: Array<T>, destPos: Int, length: Int)
