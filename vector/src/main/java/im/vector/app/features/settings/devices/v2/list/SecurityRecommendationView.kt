/*
 * Copyright (c) 2022 New Vector Ltd
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

package im.vector.app.features.settings.devices.v2.list

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import im.vector.app.R
import im.vector.app.databinding.ViewSecurityRecommendationBinding

class SecurityRecommendationView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val views: ViewSecurityRecommendationBinding

    init {
        inflate(context, R.layout.view_security_recommendation, this)
        views = ViewSecurityRecommendationBinding.bind(this)

        context.obtainStyledAttributes(
                attrs,
                R.styleable.SecurityRecommendationView,
                0,
                0
        ).use {
            setTitle(it)
            setDescription(it)
            setImage(it)
        }
    }

    private fun setTitle(typedArray: TypedArray) {
        val title = typedArray.getString(R.styleable.SecurityRecommendationView_recommendationTitle)
        views.recommendationTitleTextView.text = title
    }

    private fun setDescription(typedArray: TypedArray) {
        val description = typedArray.getString(R.styleable.SecurityRecommendationView_recommendationDescription)
        views.recommendationDescriptionTextView.text = description
    }

    private fun setImage(typedArray: TypedArray) {
        val imageResource = typedArray.getResourceId(R.styleable.SecurityRecommendationView_recommendationImageResource, 0)
        val backgroundTint = typedArray.getColor(R.styleable.SecurityRecommendationView_recommendationImageBackgroundTint, 0)
        views.recommendationShieldImageView.setImageResource(imageResource)
        views.recommendationShieldImageView.backgroundTintList = ColorStateList.valueOf(backgroundTint)
    }
}
