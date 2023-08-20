package cc.colbt.themonkey.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val data: String? = null,
    val s: String? = null,
    val atag: ATag? = null
)

data class ATag(
    val mk: String? = null,
    val mh: String? = null,
    val pk: String? = null
)
