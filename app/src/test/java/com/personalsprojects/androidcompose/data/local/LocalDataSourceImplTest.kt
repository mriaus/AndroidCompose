package com.personalsprojects.androidcompose.data.local

import com.personalsprojects.androidcompose.data.local.dataBase.HeroDao
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import io.mockk.coEvery
import kotlinx.coroutines.flow.Flow


class LocalDataSourceImplTest {

    //SUT
    private lateinit var localDataSource: LocalDataSource

    //Dependencies
    private  var dao: HeroDao = mockk()

    @Before
    fun setUp(){
        localDataSource = LocalDataSourceImpl(dao)
    }

    @Test
    fun `WHEN getHeroById Then success hero`(): Unit = runBlocking {
        // Given
        coEvery  { localDataSource.getHeroById("123") } returns HeroLocal("123", "", "", "", false)

        // When
        val hero = localDataSource.getHeroById("123")

        // Then
        assertNotNull(hero)
    }


    @After
    fun tearDown(){

    }

}