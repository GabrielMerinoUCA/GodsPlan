package ni.edu.uca.investigacion2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.investigacion2.databinding.FragmentModificarUsuarioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ModificarUsuarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModificarUsuarioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var fbinding: FragmentModificarUsuarioBinding

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
        fbinding = FragmentModificarUsuarioBinding.inflate(layoutInflater)
        iniciar()
        return fbinding.root
    }

    private fun iniciar() {

        val tempKey: String = UserApplication.prefs.readTempKey()
        fbinding.btnModificar.setOnClickListener{
            val name = fbinding.etUserNameMod.text.toString()
            val age = fbinding.etUserAgeMod.text.toString().toInt()
            var sex: Boolean = true
            if(fbinding.rbHombreAdd.isChecked){
                sex = true
            }else if(fbinding.rbMujerAdd.isChecked){
                sex = false
            }
            UserApplication.prefs.saveName(tempKey, name)
            UserApplication.prefs.saveAge(tempKey, age)
            UserApplication.prefs.saveSex(tempKey, sex)
        }
        fbinding.btnEliminar.setOnClickListener {
            var arreglo = UserApplication.prefs.getArray()
            arreglo[tempKey]!!.eliminado  = true
            UserApplication.prefs.wipe()
            var ktemp = "A"
            var y:String = ""
            // deleted validation
            for(x in arreglo.keys){
                if(!y.equals("")){
                    if(!arreglo[x]!!.eliminado) {
                        UserApplication.prefs.saveKey(y, x)
                        UserApplication.prefs.saveName(x, arreglo[x]!!.name)
                        UserApplication.prefs.saveAge(x, arreglo[x]!!.age)
                        UserApplication.prefs.saveSex(x, arreglo[x]!!.sex)
                    }
                }
                y = x
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ModificarUsuarioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ModificarUsuarioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}