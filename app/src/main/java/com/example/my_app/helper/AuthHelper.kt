import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await


class AuthHelper {

    companion object {
        val authHelper = AuthHelper()
    }

    var auth = Firebase.auth

    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.e(TAG, "signUp: Successfully...")
        }.addOnFailureListener {
            Log.e(TAG, "signUp: Failed SignUp")
        }

    }

    // asynchronization
    suspend fun signIn(email:String , password:String) : String? {
        var msg:String? = null
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Log.e(TAG, "signIn: Successfully", )
            msg = "Success"
        }.addOnFailureListener {
            Log.e(TAG, "signIn: Failed", )
            msg = it.message
        }.await()


        return msg
    }

    fun signOut() {
        auth.signOut()
    }
}