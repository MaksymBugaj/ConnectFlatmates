package com.connect.connectflatmates.ui.createaccount

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.UserEntity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import kotlinx.android.synthetic.main.create_account_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class CreateAccount : Fragment() {

    private val viewModel by viewModel<CreateAccountViewModel>()

    private var firebaseAuth: FirebaseAuth? = null
    private var mFirebaseDatabase: DatabaseReference? = null
    private var mFirebaseInstance: FirebaseDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        mFirebaseInstance = FirebaseDatabase.getInstance()

        mFirebaseDatabase = mFirebaseInstance!!.getReference("users")


        createAccount_createButton.setOnClickListener { view ->
            createUserInDatabase()
        }
    }


    private fun createUserInDatabase(){
        val userId = mFirebaseDatabase!!.push().key.toString()
        val user = UserEntity(
            id = userId,
            name = createAccount_name.text.toString(),
            surname = createAccount_surname.text.toString(),
            login = createAccount_login.text.toString(),
            email = createAccount_email.text.toString(),
            password = createAccount_password.text.toString()
        )


        firebaseAuth!!.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(context,"Creating account successful", Toast.LENGTH_SHORT).show()
                viewModel.insert(user)
                mFirebaseDatabase!!.child(userId).setValue(user)
                Observable.just(1).delay(3, TimeUnit.SECONDS).subscribe{
                    view!!.findNavController().navigate(R.id.action_createAccount_to_loginFragment)
                    Log.d("NOPE","NOPE HELP MEEEE. IM STUCKK")
                }

            } else {
                Log.d("NOPE","NOPE HELP MEEEE. IM STUCKK ${task.exception}")
                Toast.makeText(context,"Dupa koleś", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
