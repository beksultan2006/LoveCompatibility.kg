package com.template.lovecompatibilitykg.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.template.lovecompatibilitykg.R
import com.template.lovecompatibilitykg.databinding.FragmentEnterBinding
import com.template.lovecompatibilitykg.mvvm.LoveViewModel
import com.template.lovecompatibilitykg.room.LoveDao
import com.template.lovecompatibilitykg.sharedPreferences.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EnterFragment : Fragment() {
    @Inject
    lateinit var preferences: Preferences

    @Inject
    lateinit var loveDao: LoveDao
    private var _binding: FragmentEnterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeClicker()
        if (!preferences.isBoardShow()) {
            findNavController().navigate(R.id.onBoardFragment)
        }
    }

    private fun initializeClicker() {
        with(binding) {
            history.setOnClickListener {
                findNavController().navigate(R.id.historyActivity)
            }
            button.setOnClickListener {
                viewModel.getLiveData(editTextFname.text.toString(), editTextSname.text.toString())
                    .observe(viewLifecycleOwner) { loveModel ->
                        findNavController().navigate(
                            R.id.resultFragment, bundleOf(KEY to loveModel)
                        )
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY = "LoveModel"
    }
}
