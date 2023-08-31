package com.faroh.airplaneandroid.core.data.source.remote

import android.util.Log
import com.faroh.airplaneandroid.core.data.source.remote.response.ApiResponse
import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseUser
import com.faroh.airplaneandroid.core.domain.model.SignInBody
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.core.domain.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) {
    fun signUp(signUpBody: SignUpBody): Flowable<ApiResponse<UserModel>> {
        val result = PublishSubject.create<ApiResponse<UserModel>>()

        firebaseAuth.createUserWithEmailAndPassword(signUpBody.email, signUpBody.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.onNext(
                        if (it.result != null) {
                            ApiResponse.Success(
                                UserModel(
                                    id = it.result.user?.uid,
                                    email = signUpBody.email,
                                    name = signUpBody.name,
                                    hobby = signUpBody.hobby,
                                    balance = 280000000
                                )
                            )
                        } else ApiResponse.Empty
                    )
                } else {
                    result.onNext(ApiResponse.Error(it.exception?.message.toString()))
                    Log.e("REMOTE SIGN UP", it.exception.toString())
                }
            }.addOnFailureListener {
                result.onNext(ApiResponse.Error(it.message.toString()))
                Log.e("REMOTE SIGN UP", it.toString())
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun signIn(signInBody: SignInBody): Flowable<ApiResponse<String>> {
        val result = PublishSubject.create<ApiResponse<String>>()

        firebaseAuth.signInWithEmailAndPassword(signInBody.email, signInBody.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.onNext(
                        if (it.result != null) ApiResponse.Success(it.result.user?.uid.toString()) else ApiResponse.Empty
                    )
                } else {
                    result.onNext(ApiResponse.Error(it.exception?.message.toString()))
                    Log.e("REMOTE SIGN IN", it.exception.toString())
                }
            }
            .addOnFailureListener {
                result.onNext(ApiResponse.Error(it.message.toString()))
                Log.e("REMOTE SIGN IN", it.toString())
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun signOut(): Completable {
        firebaseAuth.signOut()
        return Completable.complete()
    }

    fun setUser(userModel: UserModel): Completable {
        val user = HashMap<String, Any>()
        user["balance"] = userModel.balance
        user["email"] = userModel.email!!
        user["hobby"] = userModel.hobby
        user["name"] = userModel.name!!

        firebaseFirestore.collection("users").document(userModel.id!!)
            .set(user).addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.e("REMOTE SET USER", it.exception.toString())
                }
            }.addOnFailureListener {
                Log.e("REMOTE SIGN IN", it.toString())
            }
        return Completable.complete()
    }

    fun getUserById(id: String): Flowable<ApiResponse<ResponseUser>> {
        val result = PublishSubject.create<ApiResponse<ResponseUser>>()

        firebaseFirestore.collection("users").document(id).get(Source.SERVER)
            .addOnSuccessListener { snapshot ->
                result.onNext(
                    if (snapshot != null) ApiResponse.Success(
                        snapshot.toObject(ResponseUser::class.java)!!
                    ) else ApiResponse.Empty
                )
            }
            .addOnFailureListener {
                result.onNext(ApiResponse.Error(it.message.toString()))
                Log.e("REMOTE SIGN IN", it.toString())
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}