/*
 * Copyright 2019 n-Track S.r.l.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.audioroute.delay

import android.util.Log
import com.ntrack.audioroute.AudioModule


class EffectModule : AudioModule() {

    var ptr: Long = 0
    var channels: Int = 2

    init {
        System.loadLibrary("delayEffect")
    }

    override fun configure_module(name: String, handle: Long): Boolean {
        if (ptr != 0L) {
            throw IllegalStateException("Module has already been configured.")
        }
        ptr = configureNativeComponents(handle, channels)
        return ptr != 0L
    }

    override fun getInputChannels(): Int {
        return 2
    }

    override fun getOutputChannels(): Int {
        return 2
    }

    override fun release() {

    }

    fun setParameter(feedback: Float, time: Float) {
        if (ptr == 0L) {
            Log.d("Audioroute effect", "Module is not configured.")
            return
        }

        setParameter(ptr, feedback, time)
    }



    external fun configureNativeComponents(handle: Long, channels: Int): Long
    external fun setParameter(ptr: Long, feedback: Float, time: Float)

}