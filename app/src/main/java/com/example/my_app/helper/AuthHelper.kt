import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


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

    fun signIn(email:String , password:String) {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Log.e(TAG, "signIn: Successfully", )
        }.addOnFailureListener {
            Log.e(TAG, "signIn: Failed", )
        }
    }
}