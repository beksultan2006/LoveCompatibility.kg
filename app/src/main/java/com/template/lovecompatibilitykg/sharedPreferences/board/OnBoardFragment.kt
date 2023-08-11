package com.template.lovecompatibilitykg.sharedPreferences.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.template.lovecompatibilitykg.R
import com.template.lovecompatibilitykg.databinding.FragmentOnBoardBinding
import com.template.lovecompatibilitykg.sharedPreferences.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardFragment : Fragment() {
    @Inject
    lateinit var pref: Preferences

    //Поле pref с аннотацией @Inject связывается с классом Preferences, который мы рассмотрели ранее,
    //и будет автоматически предоставлен Dagger'ом.
    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = BoardAdapter(this::click)

    private fun click() {
        pref.saveBoardState()
        findNavController().navigate(R.id.enterFragment)
        //Этот метод вызывается при нажатии на элемент "доски". Внутри него происходит сохранение состояния
        // "доски" в SharedPreferences, используя метод saveBoardState() из класса Preferences.
        // Затем фрагмент переходит на другой фрагмент с помощью findNavController().navigate().
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        //Назначение адаптера BoardAdapter для ViewPager.
        TabLayoutMediator(binding.wormDotsIndicator, binding.viewPager) { tab, _ ->
            tab.setIcon(R.drawable.tab_indicator)
        }.attach()
        //Привязка TabLayoutMediator для отображения точек, обозначающих текущую страницу в ViewPager.
        binding.skipButton.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            binding.viewPager.setCurrentItem(currentItem + 3, true)
        }
        binding.nextButton.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            binding.viewPager.setCurrentItem(currentItem + 1, true)
        }
        //Назначение обработчиков нажатия для кнопок "Пропустить" и "Далее".
    }
}
