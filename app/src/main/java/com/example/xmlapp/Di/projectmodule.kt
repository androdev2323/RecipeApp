package com.example.xmlapp.Di

import com.example.xmlapp.Data.categoryrepoImpl
import com.example.xmlapp.domain.repository.categroy_repository
import com.example.xmlapp.domain.usecases.Usecase
import com.example.xmlapp.domain.usecases.getRecipieacctoCat
import com.example.xmlapp.domain.usecases.getcategorylist
import com.example.xmlapp.domain.usecases.getrecipedetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object appmodule{
    @Provides
    @Singleton
    fun getclient():HttpClient
    {
      return HttpClient(Android){
                 install(Logging){
        level=LogLevel.ALL
    }
    install(ContentNegotiation){
   json()
    }
          }

    }

    @Provides
    @Singleton
    fun getrepo(client: HttpClient):categroy_repository{
     return categoryrepoImpl(client)
    }

    @Provides
    @Singleton
    fun getusecase(repository:categroy_repository):Usecase{
 return Usecase(getcategorylist(repository), getRecipieacctoCat(repository), getrecipedetails(repository))
    }
}
