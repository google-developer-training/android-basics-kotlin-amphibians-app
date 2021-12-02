/*
 * Copyright (C) 2021 The Android Open Source Project.
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
package com.example.amphibians

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.amphibians.ui.AmphibianListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentationTests : BaseTest() {

    @Test
    fun `recycler_view_content`() {
        launchFragmentInContainer<AmphibianListFragment>(themeResId = R.style.Theme_Amphibians)
        waitForView(withText("Great Basin Spadefoot")).check(matches(isDisplayed()))
        waitForView(withText("Tiger Salamander")).check(matches(isDisplayed()))
    }

    @Test
    fun `detail_content`() {
        launchActivity<MainActivity>()
        waitForView(withText("Blue Jeans Frog")).perform(click())
        waitForView(withText("Blue Jeans Frog")).check(matches(isDisplayed()))
        waitForView(withText("Sometimes called the Strawberry Poison-Dart Frog, this little " +
                "amphibian is identifiable by its bright red body and blueish-purple arms and " +
                "legs. The Blue Jeans Frog is not toxic to humans like some of its close " +
                "relatives, but it can be harmful to some predators."))
            .check(matches(isDisplayed()))
    }
}
