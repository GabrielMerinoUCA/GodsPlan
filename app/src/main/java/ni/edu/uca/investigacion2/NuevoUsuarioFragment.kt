package ni.edu.uca.investigacion2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.investigacion2.databinding.FragmentNuevoUsuarioBinding
import ni.edu.uca.investigacion2.entidades.Usuario

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevoUsuarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevoUsuarioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var fbinding: FragmentNuevoUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fbinding = FragmentNuevoUsuarioBinding.inflate(layoutInflater)
        iniciar()
        return fbinding.root
    }

    private fun iniciar() {
        fbinding.btnAgregar.setOnClickListener {
            var nombre = fbinding.etUserNameAdd.text.toString()
            var edad = fbinding.etUserAgeAdd.text.toString().toInt()
            var sexo: Boolean = true
            if(fbinding.rbHombreAdd.isChecked){
                sexo = true
            }else if(fbinding.rbMujerAdd.isChecked){
                sexo = false
            }
            var arreglo = HashMap<String, Usuario>()
            // maybe the array is not nesesary
            arreglo = UserApplication.prefs.getArray() //A

            // instance for array params
            var user:Usuario = Usuario(nombre, edad, sexo)

            // new key
            var newKey = UserApplication.prefs.createKey() //AA
            var oldKey: String = ""
                for(llave in arreglo.keys){
                        oldKey = llave
                }
            //save newly createdkey to the pile (cola)
            UserApplication.prefs.saveKey(oldKey, newKey)
            //save data
            UserApplication.prefs.saveName(newKey, nombre) //AA
            UserApplication.prefs.saveAge(newKey, edad)
            UserApplication.prefs.saveSex(newKey, sexo)

            }
        }
    }
