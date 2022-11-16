package ni.edu.uca.investigacion2.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.investigacion2.R
import ni.edu.uca.investigacion2.UserApplication
import ni.edu.uca.investigacion2.entidades.Usuario

class UsuarioAdapter (private val userList: List<Usuario>, currentView: View): RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioHolder(layoutInflater.inflate(R.layout.rv_usuarios, parent, false))
    }

    override fun onBindViewHolder(holder: UsuarioHolder, position: Int) {
        var item = userList[position]
        holder.load(item)
    }

    override fun getItemCount(): Int = userList.size

    inner class UsuarioHolder(private var view: View): RecyclerView.ViewHolder(view){

        private var tvNombre = view.findViewById<TextView>(R.id.tvUserNameRV)
        private var tvEdad = view.findViewById<TextView>(R.id.tvUserAgeRV)
        private var tvSexo = view.findViewById<TextView>(R.id.tvUserSexRV)
        private var fragUsuario = view.findViewById<ConstraintLayout>(R.id.fragUsuario)

        fun load(user: Usuario) {
            tvNombre.text = "Nombre: " + user.name
            tvEdad.text = "Edad: " + user.age.toString()
            var sexo:String = "Sexo: Mujer"

            if(user.sex){
                sexo = "Sexo: Hombre"
            }

            tvSexo.text = sexo
            /* MODIFICAR */
            fragUsuario.setOnClickListener {
                UserApplication.prefs.saveTempKey(user.key)
                Navigation.findNavController(view).navigate(R.id.acHome2ModificarUsuario)
            }
        }
    }

}