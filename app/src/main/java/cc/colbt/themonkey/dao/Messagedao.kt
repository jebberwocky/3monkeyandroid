package cc.colbt.themonkey.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cc.colbt.themonkey.model.Message

@Dao
interface MessagedaoDao {
    @Query("SELECT * FROM messages")
    fun getAll(): List<Message>

    @Insert
    fun insertAll(vararg messages: Message)

    @Delete
    fun delete(message: Message)
}