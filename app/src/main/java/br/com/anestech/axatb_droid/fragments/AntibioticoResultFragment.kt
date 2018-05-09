package br.com.anestech.axatb_droid.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.anestech.axatb_droid.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AntibioticoResultFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AntibioticoResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AntibioticoResultFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater!!.inflate(R.layout.fragment_antibiotico_result, container, false)
    }



}// Required empty public constructor
