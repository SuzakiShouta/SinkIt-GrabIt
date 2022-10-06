package b22712.gasagasa

import android.content.Context
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter
import java.time.LocalDateTime

class ExternalStorage(context: Context, fileName: String = LocalDateTime.now().toString()) {

    private val CLASSNAME: String = "ExternalStorage"

    // ファイル関連
    private val fileAppend : Boolean = true //true=追記, false=上書き
    private val fileNameBase : String = "pressure"
    private var fullFileName: String = fileNameBase.plus(fileName)
    private val extension : String = ".csv"
    //内部ストレージのDocumentのURL
    private val filePath: String = context.applicationContext
        .getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString()
        .plus("/").plus(fullFileName).plus(extension)

    // 保存するデータ
    private var queue: ArrayDeque<String> = ArrayDeque(listOf())

    // Handler のオブジェクトを生成
    private var delayMills: Long = 1000
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object: Runnable {
        override fun run() {
            if (queue != null) {
                Log.d(CLASSNAME, "Queue size is ".plus(queue!!.size.toString()))
                val copy = queue!!.toArray()
                queue!!.clear()
                saveArrayDeque(copy)
                handler.postDelayed(this, delayMills)
            } else { Log.d(CLASSNAME,"Queue is null") }
        }
    }

    fun fileWrite(text: String){
        val fil = FileWriter(filePath,fileAppend)
        val pw = PrintWriter(BufferedWriter(fil))
        pw.println(text)
        pw.close()
    }

    fun setQueue (queue: Queue) {
        this.queue = queue.stringQueue
    }

    // 別スレッドを Runnable で作成
    fun autoSave() {
        // 別スレッドを実行
        handler.post(runnable)
    }

    fun autoSaveStop() {
        // 別スレッドを停止
        handler.removeCallbacks(runnable)
    }

    private fun saveArrayDeque(copy: Array<Any?>) {
        val fil = FileWriter(filePath,fileAppend)
        val pw = PrintWriter(BufferedWriter(fil))
        for (item in copy) {
            pw.println(item.toString())
        }
        pw.close()
    }

}