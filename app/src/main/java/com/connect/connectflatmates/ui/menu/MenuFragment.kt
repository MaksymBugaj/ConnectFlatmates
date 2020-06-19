package com.connect.connectflatmates.ui.menu

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.ui.menu.home.BottomNavHolderActivity
import kotlinx.android.synthetic.main.menu_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {

    private val viewModel by viewModel<MenuViewModel>()

    private lateinit var animationDrawableTopLeft: AnimationDrawable
    private lateinit var animationDrawableTopRight: AnimationDrawable
    private lateinit var animationDrawableBottomLeft: AnimationDrawable
    private lateinit var animationDrawableBottomRight: AnimationDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




        /*animationDrawableTopLeft = check1.background as AnimationDrawable
        animationDrawableTopLeft.setEnterFadeDuration(4000)
        animationDrawableTopLeft.setExitFadeDuration(4000)

        animationDrawableTopRight = check2.background as AnimationDrawable
        animationDrawableTopRight.setEnterFadeDuration(4000)
        animationDrawableTopRight.setExitFadeDuration(4000)

        animationDrawableBottomLeft = check3.background as AnimationDrawable
        animationDrawableBottomLeft.setEnterFadeDuration(4000)
        animationDrawableBottomLeft.setExitFadeDuration(4000)


        animationDrawableBottomRight = check4.background as AnimationDrawable
        animationDrawableBottomRight.setEnterFadeDuration(4000)
        animationDrawableBottomRight.setExitFadeDuration(4000)*/

    }

    override fun onResume() {
        super.onResume()
       /* if (!animationDrawableTopLeft.isRunning) {
            // start the animation
            animationDrawableTopLeft.start()
        }

        if (!animationDrawableTopRight.isRunning) {
            // start the animation
            animationDrawableTopRight.start()
        }

        if (!animationDrawableBottomLeft.isRunning) {
            // start the animation
            animationDrawableBottomLeft.start()
        }

        if (!animationDrawableBottomRight.isRunning) {
            // start the animation
            animationDrawableBottomRight.start()
        }*/
    }

    override fun onPause() {
        super.onPause()
       /* if (animationDrawableTopLeft.isRunning) {
            // stop the animation
            animationDrawableTopLeft.stop()
        }
        if (!animationDrawableTopRight.isRunning) {
            // start the animation
            animationDrawableTopRight.stop()
        }

        if (!animationDrawableBottomLeft.isRunning) {
            // start the animation
            animationDrawableBottomLeft.stop()
        }

        if (!animationDrawableBottomRight.isRunning) {
            // start the animation
            animationDrawableBottomRight.stop()
        }*/
    }
}
