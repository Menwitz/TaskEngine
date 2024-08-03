
package com.menwitz.taskengine.di

import android.content.Context
import com.menwitz.taskengine.TaskEngineApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TaskEngineApplication {
        return app as TaskEngineApplication
    }

}
