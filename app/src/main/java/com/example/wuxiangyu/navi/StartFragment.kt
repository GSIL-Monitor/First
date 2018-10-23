package com.example.wuxiangyu.navi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.base.BaseFragment


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
class StartFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_start
    }

    private lateinit var tvNextFragment: TextView
    private lateinit var btnDeeplink: Button
    private var param1: String? = null
    private var param2: String? = null
    override fun initView(view: View) {

        tvNextFragment = view.findViewById(R.id.tvNextFragment)
        btnDeeplink = view.findViewById(R.id.btnDeeplink)
        tvNextFragment.setOnClickListener {
            //todo
            navController?.navigate(R.id.action_startFragment_to_secondFragment, null);
        }
        btnDeeplink.setOnClickListener { view ->

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("anote://anote-app.com/playing?track_id=6553075482441553921")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.addCategory(Intent.CATEGORY_APP_BROWSER)
//            intent.addCategory(Intent.CATEGORY_DEFAULT)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}
