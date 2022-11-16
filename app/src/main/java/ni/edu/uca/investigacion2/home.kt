package ni.edu.uca.investigacion2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.investigacion2.databinding.FragmentHomeBinding
import ni.edu.uca.investigacion2.entidades.Usuario
import ni.edu.uca.investigacion2.rv_adapters.UsuarioAdapter
import java.security.Key

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var fbinding: FragmentHomeBinding

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
        fbinding = FragmentHomeBinding.inflate(layoutInflater)
        iniciar()
        return fbinding.root
    }

    // FIND A WAY TO SEND KEYS
    private fun iniciar() {

        fbinding.btnAgregar.setOnClickListener {
            Navigation.findNavController(fbinding.root).navigate(R.id.acHome2NuevoUsuarioFragment)
        }

        /* GET STORED DATA */
        var arreglo = UserApplication.prefs.getArray()
        var list = arrayListOf<Usuario>()

        /* TRANSFORM HASH TO LIST*/
        for(keys in arreglo.keys){
            if (!keys.equals("A")){
            var user: Usuario = arreglo[keys]!!
            user.key = keys
            list.add(user)
            }
        }

        /* LOAD RECYCLER VIEWS*/
        fbinding.rcvLista.layoutManager = LinearLayoutManager(context)
        fbinding.rcvLista.setHasFixedSize(true)
        fbinding.rcvLista.adapter = UsuarioAdapter(list, fbinding.root)

    }

}