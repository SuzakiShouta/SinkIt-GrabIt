package b22712.SinkItGrabIt.ui.GetFish

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.text.format.DateFormat
import android.util.Log
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.BuildConfig
import b22712.SinkItGrabIt.FishCreater
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.R
import java.io.File
import java.io.IOException
import java.util.*


class GetFishViewModel(application: MainApplication) : ViewModel(){

    val app = application
    val LOGNAME = "GetFishViewModel"
    val packageName = "b22712.SinkItGrabIt"

    fun createFish(): Drawable? {
        return FishCreater(app).create()
    }

    /// このアプリをSNSシェアできるIntentを起動する
    fun openChooserToShareThisApp(fragment: Fragment) {

        val imageUri = fragment.resources.openRawResource(R.raw.fish_deep01).let { input ->
            val file = File("${fragment.requireActivity().cacheDir}/image.png")
            file.createNewFile()
            file.outputStream().use { output ->
                input.copyTo(output)
            }
            FileProvider.getUriForFile(fragment.requireActivity(), "$packageName.provider", file)
        }

        fragment.requireActivity()!!.grantUriPermission(
            packageName,
            imageUri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        )

        ShareCompat.IntentBuilder(fragment.requireActivity()).apply {
            setChooserTitle("share title")
            setText("share message")
            setStream(imageUri)
            setType("image/png")
            Log.d(LOGNAME,imageUri.toString())
        }.startChooser()
    }
}
