package cc.colbt.themonkey.util

import android.content.Context
import java.util.UUID


class DeviceUuidFactory {

    companion object {
        const val PREFS_FILE = "device_id.xml"
        const val PREFS_DEVICE_ID = "device_id"
        var deviceUuid: UUID? = null
        fun GetUUID(context: Context): UUID? {
            if (deviceUuid == null) {
                synchronized(DeviceUuidFactory::class.java) {
                    if (deviceUuid == null) {
                        val prefs =
                            context.getSharedPreferences(PREFS_FILE, 0)
                        val id =
                            prefs.getString(PREFS_DEVICE_ID, null)
                        if (id != null) {
                            // Use the ids previously computed and stored in the prefs file
                            deviceUuid = UUID.fromString(id)
                        } else {
                            deviceUuid =
                                UUID.randomUUID()
                            prefs.edit().putString(
                                PREFS_DEVICE_ID,
                                deviceUuid.toString()
                            ).commit()
                        }
                    }
                }
            }
            return deviceUuid
        }
    }
}