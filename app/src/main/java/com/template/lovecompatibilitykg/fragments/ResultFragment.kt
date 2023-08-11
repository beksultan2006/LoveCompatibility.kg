package com.template.lovecompatibilitykg.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.template.lovecompatibilitykg.databinding.FragmentResultBinding
import com.template.lovecompatibilitykg.retrofit.LoveModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private var loveModel: LoveModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loveModel = arguments?.getSerializable(EnterFragment.KEY) as LoveModel
        initializeTextView()
        initializeClickers()
    }
    // В методе onViewCreated происходит инициализация фрагмента после создания его представления.
    // Здесь происходит получение данных LoveModel из аргументов, переданных из предыдущего
    // фрагмента EnterFragment, и вызов методов initializeTextView() и initializeClickers().

    private fun initializeClickers() {
        with(binding) {
            buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    // Метод initializeClickers() устанавливает обработчик нажатия на кнопку buttonBack,
// который осуществляет навигацию назад.
    @SuppressLint("SetTextI18n")
    private fun initializeTextView() {
        with(binding) {
            textView1.text = loveModel?.firstName.toString()
            textView2.text = loveModel?.secondName.toString()
            result.text = loveModel?.percentage.toString() + "%"
        }
    }
    // Метод initializeTextView() устанавливает текстовые значения в соответствующие TextView
    // на основе данных из LoveModel.

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
