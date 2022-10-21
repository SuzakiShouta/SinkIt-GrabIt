package b22712.SinkItGrabIt.ui.GetFish

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.FishCreator
import b22712.SinkItGrabIt.MainApplication
import java.io.File


class GetFishViewModel(application: MainApplication) : ViewModel(){

    val app = application
    val LOGNAME = "GetFishViewModel"
    val packageName = "b22712.SinkItGrabIt"
    var fishImageId: Int = 0
    var fishImageName: String = ""

    fun createFish(): Drawable? {
        val fishCreator: FishCreator = FishCreator(app)
        fishCreator.create()
        fishImageId = fishCreator.id
        fishImageName = fishCreator.name
        Log.d(LOGNAME, fishImageName)
        return fishCreator.drawable
    }

    /// このアプリをSNSシェアできるIntentを起動する
    fun openChooserToShareThisApp(fragment: Fragment) {

//        val name = "fish_deep01"
        val id: Int = fragment.requireActivity().resources.getIdentifier(fishImageName, "raw", fragment.requireActivity().packageName)

        val imageUri = fragment.resources.openRawResource(id).let { input ->
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
            setChooserTitle("「沈めて掴む」で魚を捕まえた！")
            setText("携帯を水に沈めて強く握って魚を捕まえた！\n#沈めて掴む\n#SinlItGrabIt")
            setStream(imageUri)
            setType("image/png")
            Log.d(LOGNAME,imageUri.toString())
        }.startChooser()
    }
}
