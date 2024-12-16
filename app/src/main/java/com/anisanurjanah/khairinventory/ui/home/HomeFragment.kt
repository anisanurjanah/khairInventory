package com.anisanurjanah.khairinventory.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anisanurjanah.khairinventory.data.Items
import com.anisanurjanah.khairinventory.databinding.FragmentHomeBinding
import com.anisanurjanah.khairinventory.ui.FirebaseItemAdapter
import com.anisanurjanah.khairinventory.utils.INVENTORY
import com.anisanurjanah.khairinventory.utils.ITEMS
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: FirebaseItemAdapter
    private lateinit var db: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.database
        val inventoryRef = db.reference.child(INVENTORY).child(ITEMS)

        val manager = LinearLayoutManager(requireContext())
        manager.stackFromEnd = true
        binding.rvItems.layoutManager = manager

        val options = FirebaseRecyclerOptions.Builder<Items>()
            .setQuery(inventoryRef, Items::class.java)
            .build()
        adapter = FirebaseItemAdapter(options)
        binding.rvItems.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    override fun onPause() {
        adapter.stopListening()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}