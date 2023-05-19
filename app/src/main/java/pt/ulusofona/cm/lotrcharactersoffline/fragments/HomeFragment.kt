package pt.ulusofona.cm.lotrcharactersoffline.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import pt.ulusofona.cm.lotrcharactersoffline.NavigationManager
import pt.ulusofona.cm.lotrcharactersoffline.R
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTROkHttp
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.databinding.FragmentHomeBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class HomeFragment : Fragment() {

  private val model = LOTRRepository.getInstance()

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    binding = FragmentHomeBinding.bind(view)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    binding.getCharactersBtn.setOnClickListener {
      CoroutineScope(Dispatchers.IO).launch {
        model.getCharacters {
          if(it.isSuccess) {
            NavigationManager.goToCharactersListFragment(
              parentFragmentManager,
              //Envia uma lista vazia se não receber nada
            it.getOrDefault(mutableListOf())
            )
          } else {
            // Apresenta o erro num Toast
            Toast.makeText(requireContext(), it.exceptionOrNull()?.message, Toast.LENGTH_LONG)
              .show()
          }
        }
      }
    }
  }

}