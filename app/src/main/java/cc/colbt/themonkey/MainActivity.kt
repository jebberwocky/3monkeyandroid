package cc.colbt.themonkey

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cc.colbt.themonkey.util.Const
import com.mixpanel.android.mpmetrics.MixpanelAPI
import io.branch.referral.Branch
import org.json.JSONException

private lateinit var webFragment: WebViewBlankFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webFragment = WebViewBlankFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.thelayout, webFragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        var mp = MixpanelAPI.getInstance(this, Const.MixPanel.Key, false)
        mp.track(Const.MixPanel.EventActivityStarted)
        Branch.sessionBuilder(this).withCallback { referringParams, error ->
            if (error == null) {
                if (referringParams != null) {
                    Log.i("BRANCH SDK INIT", referringParams.toString())
                    try {
                        //Log.i("BRANCH params", referringParams.get("params").toString());
                        Log.i(
                            "BRANCH \$canonical_url",
                            referringParams!!["\$canonical_url"].toString()
                        )
                    } catch (exception: JSONException) {
                        Log.e("Casting error", exception.toString())
                    }
                }
            } else {
                Log.e("BRANCH SDK ERROR", error.message)
            }
        }.withData(this.intent.data).init()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.intent = intent;
        if (intent != null &&
            intent.hasExtra("branch_force_new_session") &&
            intent.getBooleanExtra("branch_force_new_session",false)) {
            Branch.sessionBuilder(this).withCallback { referringParams, error ->
                if (error != null) {
                    Log.e("BranchSDK_Tester", error.message)
                } else if (referringParams != null) {
                    Log.i("BranchSDK_Tester", referringParams.toString())
                }
            }.reInit()
        }
    }
}