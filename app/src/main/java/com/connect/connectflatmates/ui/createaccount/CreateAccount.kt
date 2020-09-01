package com.connect.connectflatmates.ui.createaccount

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.databinding.CreateAccountFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.create_account_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class CreateAccount : Fragment() {

    private val viewModel by viewModel<CreateAccountViewModel>()

    private var firebaseAuth: FirebaseAuth? = null
    private var mFirebaseDatabase: DatabaseReference? = null
    private var mFirebaseInstance: FirebaseDatabase? = null
    private val subscriptions = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return CreateAccountFragmentBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        mFirebaseInstance = FirebaseDatabase.getInstance()

        mFirebaseDatabase = mFirebaseInstance!!.getReference("users")



        viewModel.observeCreateAccount.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> view!!.findNavController().popBackStack(R.id.loginFragment, true)
                false -> Unit
            }
        })

        viewModel.observeInput.observe(viewLifecycleOwner, Observer {
            when (it) {
                CreatingAccount.NoName -> {
                    createAccountLayout_name.error = resources.getString(R.string.field_not_empty)
                }

                CreatingAccount.NoSurname -> {
                    createAccountLayout_surname.error = resources.getString(R.string.field_not_empty)
                }

                CreatingAccount.NoLogin ->{
                    createAccountLayout_login.error = resources.getString(R.string.field_not_empty)
                }

                CreatingAccount.WrongEmail ->{
                    createAccountLayout_email.error = resources.getString(R.string.not_an_email)
                }

                CreatingAccount.NoEmail ->{
                    createAccountLayout_email.error = resources.getString(R.string.field_not_empty)
                }

                CreatingAccount.NoPassword ->{
                    createAccountLayout_password.error = resources.getString(R.string.field_not_empty)
                }

                CreatingAccount.NoRepeatedPassword ->{
                    createAccountLayout_passwordCorrect.error = resources.getString(R.string.field_not_empty)
                }

                CreatingAccount.PasswordDoesntMatch ->{
                    createAccountLayout_passwordCorrect.error = resources.getString(R.string.password_does_not_match)
                }
            }
        })

    }

    private fun create() {
        val user = UserProfile(
            name = createAccount_name.text.toString(),
            surname = createAccount_surname.text.toString(),
            login = createAccount_login.text.toString(),
            email = createAccount_email.text.toString(),
            password = createAccount_password.text.toString()
        )


        subscriptions.add(Observable.just(1).delay(3, TimeUnit.SECONDS).subscribe {
            view!!.findNavController().navigate(R.id.action_createAccount_to_loginFragment)
            Log.d("NOPE", "NOPE HELP MEEEE. to te login")
        }
        )
    }

    //todo move to viewModel
    //disable firebase for a while
    private fun createUserInDatabase() {
        val userId = mFirebaseDatabase!!.push().key!!.toInt()
        val user = UserProfile(
            id = userId,
            name = createAccount_name.text.toString(),
            surname = createAccount_surname.text.toString(),
            login = createAccount_login.text.toString(),
            email = createAccount_email.text.toString(),
            password = createAccount_password.text.toString()
        )


        firebaseAuth!!.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Creating account successful", Toast.LENGTH_SHORT)
                        .show()
                    viewModel.insert(user)
                    mFirebaseDatabase!!.child(userId.toString()).setValue(user)
                    Observable.just(1).delay(3, TimeUnit.SECONDS).subscribe {
                        view!!.findNavController()
                            .navigate(R.id.action_createAccount_to_loginFragment)
                        Log.d("NOPE", "NOPE HELP MEEEE. IM STUCKK")
                    }

                } else {
                    Log.d("NOPE", "NOPE HELP MEEEE. IM STUCKK ${task.exception}")
                    Toast.makeText(context, "Dupa koleś", Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}
