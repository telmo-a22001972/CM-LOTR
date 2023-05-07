package pt.ulusofona.cm.lotrcharactersoffline.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pt.ulusofona.cm.lotrcharactersoffline.NavigationManager
import pt.ulusofona.cm.lotrcharactersoffline.R
import pt.ulusofona.cm.lotrcharactersoffline.databinding.FragmentHomeBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    binding = FragmentHomeBinding.bind(view)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    binding.getCharactersBtn.setOnClickListener {
      // TODO em vez de passarmos uma lista vazia, temos de passar a lista de characters
      NavigationManager.goToCharactersListFragment(parentFragmentManager, mutableListOf())
    }
  }

}