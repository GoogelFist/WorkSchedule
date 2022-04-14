package com.github.googelfist.workshedule.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.DefaultScheduleFragmentBinding
import javax.inject.Inject

class DefaultScheduleFragment : Fragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private var _binding: DefaultScheduleFragmentBinding? = null
    private val binding: DefaultScheduleFragmentBinding
        get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel> {
        mainViewModelFactory
    }

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DefaultScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.month.observe(viewLifecycleOwner) {
            binding.textView.text = it.getFormattedDate()
        }

        setupButtons()
    }

    private fun setupButtons() {
        binding.button1.setOnClickListener {
            mainViewModel.onGeneratePreviousMonth()
        }
        binding.button2.setOnClickListener {
            mainViewModel.onGenerateCurrentMonth()
        }
        binding.button3.setOnClickListener {
            mainViewModel.onGenerateNextMonth()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun getNewInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment()
        }
    }
}