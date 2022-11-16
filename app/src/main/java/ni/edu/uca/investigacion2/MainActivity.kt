package ni.edu.uca.investigacion2

import android.annotation.SuppressLint
import android.app.blob.BlobHandle
import android.app.blob.BlobStoreManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ni.edu.uca.investigacion2.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var fbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fbinding = ActivityMainBinding.inflate(layoutInflater)
        iniciar()
        setContentView(fbinding.root)
    }

    /* BLOB WTF */
    /*
    @RequiresApi(Build.VERSION_CODES.R)
    private fun iniciar() {
        // write
        fbinding.btnEnviar.setOnClickListener {
            write()
        }

        //read
        fbinding.btnMostrar.setOnClickListener {
            read()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("WrongConstant")
    private fun write(){

        var content = "someContentToWrite"
        val contentByte = content.toByteArray(charset("utf-8"))
        val md = MessageDigest.getInstance("sha256")
        val contentHash: ByteArray = md.digest(contentByte)

        var data = content

        try {
            val blobHandle = BlobHandle.createWithSha256(
                contentHash,
                "Sample",
                System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1),
                "SAMPLE_TAG"
            )
            val blobStoreManager = getSystemService(Context.BLOB_STORE_SERVICE) as BlobStoreManager

            val sessionId = blobStoreManager.createSession(blobHandle)
            try {
                val session = blobStoreManager.openSession(sessionId)
                try {
                    val output = ParcelFileDescriptor.AutoCloseOutputStream(
                        session.openWrite(
                            0,
                            1024 * 1024 * 200
                        )
                    )
                    Log.wtf("TAG", "writefile: >>>>>>>>>>text = " + "somthing")

                    output.write("somthing".toByteArray())
                    output.flush()

                    session.allowPublicAccess()
                    session.commit(applicationContext.mainExecutor, { Log.wtf("TAG", "xd") })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("WrongConstant")
    private fun read(): String{
        val data = ""
        val blobStoreManager = getSystemService(Context.BLOB_STORE_SERVICE) as BlobStoreManager
        val blobHandle = BlobHandle.createWithSha256("something".toByteArray(),
            "Sample",
            System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1),
            "SAMPLE_TAG")
        try{
            val output = ParcelFileDescriptor.AutoCloseInputStream(blobStoreManager.openBlob(blobHandle))
            val buffer = ByteArray(output.available())
            output.read(buffer)
            var txt = String(buffer)
            Log.wtf("TAG", ">>>>>>>>>THE VALUE OF THE BLOB IS :" + txt)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return data
    }

     */

    /* SHARED PREFERENCES */
    private fun iniciar() {

    }

    /* INTERNAL STORAGE */
    /*private fun iniciar() {
        // gets a list of all files
        var files: Array<String> = applicationContext.fileList()

        val fileName = "myfile"
        //WRITE
        fbinding.btnEnviar.setOnClickListener {
            // gets the user input
            val fileContents = fbinding.etMessage.text.toString()

            // open a file to overwrite it or create and write if does not exist
            // first arg is the file you want to use, second arg is to open it with encryption
            applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(fileContents.toByteArray())
            }
        }

        // files is all the files that exist in the app file directory
        /*
        var filename: String = ""
        for(file in files) {
            filename += file + "\n"
        }
         */
         // READ
        fbinding.btnMostrar.setOnClickListener {
            applicationContext.openFileInput(fileName).use {
                val b = ByteArray(it.available())
                it.read(b)
                it.close()
                val msg = String(b)
                fbinding.tvMain.text = msg
            }
        }
    }*/

}
