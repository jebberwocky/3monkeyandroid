package cc.colbt.themonkey

import android.app.Activity
import android.webkit.JavascriptInterface
import android.widget.Toast


class MonkeyJavaScriptInterface(activity: Activity?) {
    var activity: Activity? = activity
    @JavascriptInterface
    fun showToast(toast: String?) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()
    }
    
    fun storeStat(state: String?){
        
    }
}