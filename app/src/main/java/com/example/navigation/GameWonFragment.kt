package com.example.navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view:View->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
//        Toast.makeText(context,
//        "NumCorrect: ${args.numCorrect} , NumQuestions: ${args.numQuestions}", Toast.LENGTH_LONG).show()  for showing correct answers

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun getShareIntent(): Intent {
        var args = GameWonFragmentArgs.fromBundle(requireArguments())
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.setType("Text/Plain").putExtra(Intent.EXTRA_TEXT,
//            getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
//
//        return shareIntent
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.winner_menu,menu)
        if(null==getShareIntent().resolveActivity(requireActivity().packageManager)){
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onContextItemSelected(item)
    }
}