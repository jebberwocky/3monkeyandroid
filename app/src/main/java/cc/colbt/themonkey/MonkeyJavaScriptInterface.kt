package cc.colbt.themonkey

import android.app.Activity
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import cc.colbt.themonkey.model.Message
import cc.colbt.themonkey.util.Const
import com.google.gson.Gson


class MonkeyJavaScriptInterface(activity: Activity?) {
    var activity: Activity? = activity
    @JavascriptInterface
    fun showToast(toast: String?) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun storeStat(state: String?){
        if (state != null) {
            Log.d(Const.LogTag, state)
        }
    }

    @JavascriptInterface
    fun setMessage(message: String?){
        if(message != null){
            Log.d(Const.LogTag, message)
            //convert json string to Message object
            val m = Gson().fromJson(message, Message::class.java)
            Log.d(Const.LogTag,m.toString())
        }
    }
}