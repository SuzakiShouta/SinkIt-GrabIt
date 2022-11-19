package b22712.SinkItGrabIt

class Queue {
    val queue:ArrayDeque<Float> = ArrayDeque(arrayListOf(0F))
    val queueCapacity: Int = 25

    val stringQueue: ArrayDeque<String> = ArrayDeque(30)

    val FishAppearQueue: ArrayDeque<Boolean> = ArrayDeque(arrayListOf(false))
    val FishAppearQueueCapacity: Int = 100

    val basePressureQueue: ArrayDeque<Float> = ArrayDeque(arrayListOf())
    val basePressureQueueCapacity: Int = 75

    val grabQueue:ArrayDeque<Boolean> = ArrayDeque(arrayListOf())
    val grabQueueCapacity: Int = 5

    fun addQueue(value: Float){
//        println(queue)
        while (queue.size > queueCapacity) { queue.removeFirst() }
        queue.add(value)
    }

    fun addFishAppearQueue(value: Boolean){
        while (FishAppearQueue.size > FishAppearQueueCapacity) { FishAppearQueue.removeFirst() }
        FishAppearQueue.add(value)
    }

    fun addBasePressureQueue(value: Float){
        while (basePressureQueue.size > basePressureQueueCapacity) {basePressureQueue.removeFirst()}
        basePressureQueue.add(value)
    }

    fun addGrabQueue(value: Boolean){
        while (grabQueue.size > grabQueueCapacity) {grabQueue.removeFirst()}
        grabQueue.add(value)
    }

    operator fun get(i: Int): Float {
        return queue[i]
    }
}