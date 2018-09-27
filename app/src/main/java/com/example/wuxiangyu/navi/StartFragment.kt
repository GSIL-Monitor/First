package com.example.wuxiangyu.navi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.wuxiangyu.first.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StartFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class StartFragment : Fragment() {
    private lateinit var tvNextFragment: TextView
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        tvNextFragment = view.findViewById(R.id.tvNextFragment)
                tvNextFragment.setOnClickListener{
                    //todo
                    Navigation.findNavController(view).navigate(R.id.action_startFragment_to_secondFragment, null);
                }
        return view
    }
}
