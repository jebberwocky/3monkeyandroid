package cc.colbt.themonkey.model

data class Message (
    val data: String? = null,
    val s:String? = null,
    val atag:ATag? = null
)

data class ATag(
    val mk: String? = null,
    val mh: String? = null,
    val pk: String? = null
)
