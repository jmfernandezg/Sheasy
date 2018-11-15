package de.jensklingenberg.sheasy.di

import dagger.Component
import de.jensklingenberg.sheasy.utils.AppsRepository
import de.jensklingenberg.sheasy.utils.FUtils
import de.jensklingenberg.sheasy.utils.NotificationUtils
import de.jensklingenberg.sheasy.network.HTTPServerService
import de.jensklingenberg.sheasy.network.MyHttpServerImpl
import de.jensklingenberg.sheasy.ui.NewCommonViewModel
import de.jensklingenberg.sheasy.ui.ServerFragment
import javax.inject.Singleton

@Component(modules = [(AppModule::class), (UtilsModule::class), (ServiceModule::class)])
@Singleton
interface AppComponent {

    fun inject(commonViewModel: NewCommonViewModel)
    fun inject(httpServerService: HTTPServerService)
    fun inject(myHttpServerImpl: MyHttpServerImpl)
    fun inject(fUtils: FUtils)
    fun inject(appsRepository: AppsRepository)
    fun inject(serverFragment: ServerFragment)
    fun inject(notificationUtils: NotificationUtils)


}
