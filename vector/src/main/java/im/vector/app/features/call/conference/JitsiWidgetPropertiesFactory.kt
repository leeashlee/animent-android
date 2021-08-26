/*
 * Copyright (c) 2021 New Vector Ltd
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

package im.vector.app.features.call.conference

import android.net.Uri
import im.vector.app.R
import im.vector.app.core.resources.StringProvider
import org.matrix.android.sdk.api.extensions.tryOrNull
import java.net.URLDecoder
import javax.inject.Inject

class JitsiWidgetPropertiesFactory @Inject constructor(
        private val stringProvider: StringProvider
) {
    fun create(url: String): JitsiWidgetProperties {
        val configString = tryOrNull { Uri.parse(url) }?.encodedQuery
        val configs = configString?.split("&")
                ?.map { it.split("=") }
                ?.filter { it.size == 2 }
                ?.map { (key, value) -> key to URLDecoder.decode(value, "UTF-8") }
                ?.toMap()
                .orEmpty()

        return JitsiWidgetProperties(
                domain = configs["conferenceDomain"] ?: stringProvider.getString(R.string.preferred_jitsi_domain),
                confId = configs["conferenceId"] ?: configs["confId"],
                displayName = configs["displayName"],
                avatarUrl = configs["avatarUrl"]
        )
    }
}
