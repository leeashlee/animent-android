/*
 * Copyright 2020 The Matrix.org Foundation C.I.C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.android.sdk.internal.session.profile

import org.matrix.android.sdk.internal.network.GlobalErrorReceiver
import org.matrix.android.sdk.internal.network.executeRequest
import org.matrix.android.sdk.internal.task.Task
import javax.inject.Inject

internal abstract class AddStickerPackTask : Task<AddStickerPackTask.Params, Unit> {
    data class Params(
            val userId: String,
            val url: CharSequence
    )
}

internal class DefaultAddStickerPackTask @Inject constructor(
        private val profileAPI: ProfileAPI,
        private val globalErrorReceiver: GlobalErrorReceiver
) : AddStickerPackTask() {

    override suspend fun execute(params: Params) {
        val body = AddStickerPackBody(
                url = params.url
        )
        return executeRequest(globalErrorReceiver) {
            profileAPI.addStickerPack(params.userId, body)
        }
    }
}
