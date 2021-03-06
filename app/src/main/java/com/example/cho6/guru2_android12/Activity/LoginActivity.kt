//s2.로그인 화면
package com.example.cho6.guru2_android12.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cho6.guru2_android12.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth // FirebaseAuth 객체의 공유 인스턴스 가져오기(1)

    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // 's2.로그인 화면'을 위한 xml 사용

        auth = FirebaseAuth.getInstance() // FirebaseAuth 객체의 공유 인스턴스 가져오기(2)

        login_button.setOnClickListener {

            val email = login_email.text.toString()
            val password = login_pwd.text.toString()

            // 사용자가 앱에 로그인 -> 이메일 주소, 비밀번호 전달
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success") // 로그인 성공여부 확인 로그


                        // 위드미 화면으로 이동 (withmefriendActivity)
                        val intent = Intent(this,
                            withmefriendActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // 뒤에 있는 Activity를 지워준다.
                        startActivity(intent)


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception) // 로그인 실패여부 확인 로그
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    // ...
                }

        }

        // 화면 뒤로 가기 버튼(s1.'회원 가입'으로 이동)
        back_to_register_textview.setOnClickListener {
            val intent = Intent(this,
                MainActivity::class.java)
            startActivity(intent)
        }
    }
}