package com.hxqc.business.core.httprequest

/**
 * Author:  wh
 * Date:  2017/8/10
 * FIXME
 * Todo
 */


abstract class Fetch {
    abstract val desKey: IntArray
    abstract val MD5String: String
    abstract val IV: String
    abstract val Sys: String
}

object SSOFetch : Fetch() {
    override val Sys: String
        get() = "SSO"
    override val MD5String: String
        get() = "a\$%\$^#w12341@#@1-03@ffs3"
    override val IV: String
        get() = "01234567"
    override val desKey: IntArray
        get() = intArrayOf(457, 505, 205, 465, 481, 417, 437, 441, 461, 409, 205, 461, 425, 485, 209, 469, 425, 461, 461, 433, 465, 273, 161, 277)

}


object ERPFetch : Fetch() {
    override val Sys: String
        get() = "ERP"
    override val desKey: IntArray
        get() = intArrayOf(437, 501, 473, 417, 205, 417, 465, 457, 209, 465, 497, 449, 501, 205, 449, 425, 497, 161, 449, 469, 421, 437, 225, 221)
    override val MD5String: String
        get() = "a\$%\$^#w12341@#@1-03@ffs3"
    override val IV: String
        get() = "08183625"
}


object CRMFetch : Fetch() {
    override val Sys: String
        get() = "CRM"
    override val desKey: IntArray
        get() = intArrayOf(233, 221, 169, 309, 301, 249, 361, 189, 453, 345, 281, 293, 457, 441, 477, 153, 469, 161, 301, 369, 213, 309, 309, 433)
    override val MD5String: String
        get() = "kj@fr#bgxt41@#@1-03@ffs3"
    override val IV: String
        get() = "01234567"

}


object OAFetch : Fetch() {
    override val Sys: String
        get() = "OA"
    override val desKey: IntArray
        get() = intArrayOf(437, 501, 473, 417, 205, 417, 465, 457, 209, 465, 409, 421, 481, 417, 449, 425, 497, 161, 449, 469, 421, 437, 409, 409)
    override val MD5String: String
        get() = "cb%6^*w45@41@#@1-03@ffs3"
    override val IV: String
        get() = "76543210"

}


object NCFetch : Fetch() {
    override val Sys: String
        get() = "NC"
    override val desKey: IntArray
        get() = intArrayOf(465, 409, 453, 501, 277, 213, 233, 221, 493, 465, 481, 197, 501, 469, 425, 421, 417, 257, 501, 465, 217, 277, 165, 237)
    override val MD5String: String
        get() = "diap\$@ds%&^erwc@\$^*fsvs4"
    override val IV: String
        get() = "52681239"

}


object MallFetch : Fetch() {
    override val Sys: String
        get() = "MALL"
    override val desKey: IntArray
        get() = intArrayOf(397, 213, 429, 213, 497, 465, 277, 153, 457, 237, 481, 189, 245, 249, 397, 461, 213, 161, 173, 461, 437, 257, 421, 165)
    override val MD5String: String
        get() = ""
    override val IV: String
        get() = "01234567"

}


object ConfFetch : Fetch() {
    override val Sys: String
        get() = "Conf"
    override val desKey: IntArray
        get() = intArrayOf(237, 433, 361, 497, 233, 397, 509, 449, 361, 349, 469, 189, 225, 493, 485, 169, 313, 421, 221, 381, 321, 509, 225, 505)
    override val MD5String: String
        get() = ""
    override val IV: String
        get() = "01234567"
}


object NSCodeFetch : Fetch() {
    override val Sys: String
        get() = "NS"
    override val desKey: IntArray
        get() = intArrayOf(397, 213, 429, 213, 497, 465, 277, 153, 457, 237, 481, 189, 245, 249, 397, 461, 213, 161, 173, 461, 437, 257, 421, 165)
    override val MD5String: String
        get() = ""
    override val IV: String
        get() = "01234567"

}
