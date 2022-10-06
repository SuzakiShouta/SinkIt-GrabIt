package b22712.gasagasa

class Queue {
    val queue:ArrayDeque<Float> = ArrayDeque(arrayListOf(9999F, 9999F, 9999F, 9999F, 9999F, 9999F, 9999F, 9999F, 9999F, 9999F))
    val queueCapacity: Int = 10

    val stringQueue: ArrayDeque<String> = ArrayDeque(30)

    val FishAppearQueue: ArrayDeque<Boolean> = ArrayDeque(arrayListOf(false))
    val FishAppearQueueCapacity: Int = 100

    val basePressureQueue: ArrayDeque<Float> = ArrayDeque(arrayListOf(0F))
    val basePressureQueueCapacity: Int = 45

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

    operator fun get(i: Int): Float {
        return queue[i]
    }
}