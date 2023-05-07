package pt.ulusofona.cm.lotrcharactersoffline.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.cm.lotrcharactersoffline.CharacterListAdapter
import pt.ulusofona.cm.lotrcharactersoffline.R
import pt.ulusofona.cm.lotrcharactersoffline.databinding.FragmentCharactersListBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding
    private lateinit var characters: List<LOTRCharacter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getSerializable("characters")?.let {
            characters = (it as Array<LOTRCharacter>).asList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)
        binding = FragmentCharactersListBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.charactersListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersListRv.adapter = CharacterListAdapter(characters)
        binding.charactersListRv.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }

    companion object {

        fun newInstance(characters: List<LOTRCharacter>): CharactersListFragment {
            return CharactersListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("characters", characters.toTypedArray())
                }
            }
        }

    }

}