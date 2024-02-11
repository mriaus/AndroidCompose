package com.personalsprojects.androidcompose.ui.components.heroCard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.ui.components.navigationBar.CustomNavigationBar
import org.junit.Rule
import org.junit.Test

class HeroCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun whenHeroIsFavoriteIconWithFavoriteAppears(){
    val hero = Hero("","","","",true)
        //Given
        composeRule.setContent {
            HeroCard(hero = hero, onPressHero = { /*TODO*/ }) {
            }
        }
        //When
        //Then
        composeRule.onNodeWithContentDescription("Liked icon").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Like icon").assertIsNotDisplayed()
    }

    @Test
    fun whenHeroIsNOTFavoriteIconWithFavoriteBorderAppears(){
        val hero = Hero("","","","",false)
        //Given
        composeRule.setContent {
            HeroCard(hero = hero, onPressHero = { /*TODO*/ }) {
            }
        }
        //When
        //Then
        composeRule.onNodeWithContentDescription("Liked icon").assertIsNotDisplayed()
        composeRule.onNodeWithContentDescription("Like icon").assertIsDisplayed()
    }

}