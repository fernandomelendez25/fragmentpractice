package com.melendez.fragmentpractice

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SUM1 = "sum1"
private const val ARG_SUM2 = "sum2"

class SumFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var sum1: Int? = null
    private var sum2: Int? = null
    private var listener : OnActionListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnActionListener

        if(listener == null){
            throw ClassCastException("$context must implement onActionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sum1 = it.getInt(ARG_SUM1, 0)
            sum2 = it.getInt(ARG_SUM2, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sumando1TextView : TextView = view.findViewById(R.id.sumando_uno_text_view)
        val sumando2TextView : TextView = view.findViewById(R.id.sumando_dos_text_view)
        val resultTextView : TextView = view.findViewById(R.id.result_text_view)

        val result : Int = (sum1?.plus(sum2!!)) ?: 0
        val actionButton : Button = view.findViewById<Button>(R.id.action_button)

        actionButton.setOnClickListener {
            listener?.onActionClick(result)
        }

        sumando1TextView.text = sum1?.toString()
        sumando2TextView.text = sum2?.toString()
        resultTextView.text = result.toString()
    }


    interface OnActionListener {
        fun onActionClick(result:Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param sum1 Parameter 1.
         * @param sum2 Parameter 2.
         * @return A new instance of fragment SumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sum1: Int, sum2: Int) =
            SumFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SUM1, sum1)
                    putInt(ARG_SUM2, sum2)
                }
            }
    }
}