package by.densolo.demousers.screens.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.densolo.demousers.R
import by.densolo.demousers.databinding.FragmentHomeBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlin.random.Random

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            Toast.makeText(context, greeting(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun greeting(): CharSequence {
        return when (Random.nextInt(1,4)) {
            1 -> "You're breathtaking!"
            2 -> "You look amazing!"
            3 -> "You're awesome!"
            else -> "You're adorable!"
        }
    }
}

