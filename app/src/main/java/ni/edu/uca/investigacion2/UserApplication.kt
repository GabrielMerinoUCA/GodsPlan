package ni.edu.uca.investigacion2

import android.app.Application
import android.util.Log

/* INITIALIZES THE SHARE PREFERENCES WHEN THE APPLICATION STARTS (como un data conection o clase de conexio)
* algo asi*/
class UserApplication: Application(){
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }


}