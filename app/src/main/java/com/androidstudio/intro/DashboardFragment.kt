package com.androidstudio.intro

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        // Menambahkan setHasOptionsMenu(true) agar fragment dapat menampilkan menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val btnProfile: ImageButton = view.findViewById(R.id.btnProfile)
        btnProfile.setOnClickListener {
            // Ganti fragment ke EditProfileFragment saat tombol btnProfile ditekan
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flContent, EditProfileFragment())
            fragmentTransaction.addToBackStack(null)  // Menambahkan transaksi ke tumpukan kembali
            fragmentTransaction.commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.header_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.menuNotification -> Toast.makeText(requireContext(), "Kamu menekan notification", Toast.LENGTH_LONG).show()
//            R.id.menuProfile -> Toast.makeText(requireContext(), "Kamu menekan profile", Toast.LENGTH_LONG).show()
//        }
//        return true
//    }
}
