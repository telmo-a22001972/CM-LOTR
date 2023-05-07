package pt.ulusofona.cm.lotrcharactersoffline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.cm.lotrcharactersoffline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NavigationManager.goToHomeFragment(supportFragmentManager)
    }

}