package ni.edu.uca.investigacion2

import android.content.Context
import ni.edu.uca.investigacion2.entidades.Usuario

class Prefs(val context: Context){
    //this is lik the name of the files
    val SHARED_NAME = "Mydtb"

    // these are keys as if it was an associative array
    val SHARED_USER_KEY = "A" // pepe, 18, true/false
    val SHARED_USER_NAME = "username"
    val SHARED_USER_AGE = "age"
    val SHARED_USER_SEX = "sex"
    val SHARED_TEMP_KEY = "R"

    val storage = context.getSharedPreferences(SHARED_NAME,0)

    /* WRITE */
    fun saveName(key:String ,name: String){
        storage.edit().putString(key + SHARED_USER_NAME, name).apply() //AAusername, AAAusername
    }
    fun saveAge(key:String ,age: Int){
        storage.edit().putInt(key + SHARED_USER_AGE, age).apply()
    }
    fun saveSex(key:String ,sex: Boolean){
        storage.edit().putBoolean(key + SHARED_USER_SEX, sex).apply()
    }
    fun saveKey(oldKey:String, newKey: String){
        storage.edit().putString(oldKey, newKey).apply()
    }
    fun saveTempKey(key: String){ // Saves the key of the user to modify temporally
        storage.edit().putString(SHARED_TEMP_KEY, key).apply()
    }

    /* READ FUNCTIONS */
    fun readTempKey(): String{
        return storage.getString(SHARED_TEMP_KEY, "")!!
    }
    fun readKey(key:String): String{
        return storage.getString(key, "")!!
    }
    fun readName(key:String): String{
        //second arg is default value in case there is nothing
        return storage.getString(key + SHARED_USER_NAME, "")!!
    }
    fun readAge(key:String): Int{
        return storage.getInt(key + SHARED_USER_AGE, -1)
    }
    fun readSex(key:String): Boolean{
        return storage.getBoolean(key+SHARED_USER_SEX, true)
    }

    /* MISELANEOUS */
    fun getArray(): HashMap<String, Usuario>{
        var tempKey = SHARED_USER_KEY
        var arreglo = HashMap<String, Usuario>()
        while(tempKey.isNotEmpty()){
            /*Get values from the current key iterations*/
            val name:String = readName(tempKey)
            val age:Int = readAge(tempKey)
            val sex:Boolean = readSex(tempKey)

            val user = Usuario(name,age,sex)

            arreglo.put(tempKey, user)

            /* change to the next key*/
            tempKey = readKey(tempKey)
        }
        return arreglo
    }

    fun createKey(): String{
        var keyTemp = SHARED_USER_KEY
        while(true){
            if(!readKey(keyTemp).equals("") || readKey(keyTemp).isNotEmpty()){
                keyTemp = readKey(keyTemp)
            }else{break}
        }
        return keyTemp + "A"
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}