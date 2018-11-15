package de.jensklingenberg.sheasy.di

import dagger.Module
import dagger.Provides
import de.jensklingenberg.sheasy.utils.AppsRepository
import de.jensklingenberg.sheasy.utils.FUtils
import de.jensklingenberg.sheasy.utils.NotificationUtils
import javax.inject.Singleton

@Module
class UtilsModule {


    @Provides
    @Singleton
    fun provideAppUtils() = AppsRepository()

    @Provides
    @Singleton
    fun provideNotifUtils() = NotificationUtils()

    @Provides
    @Singleton
    fun provideFUtils() = FUtils()


}