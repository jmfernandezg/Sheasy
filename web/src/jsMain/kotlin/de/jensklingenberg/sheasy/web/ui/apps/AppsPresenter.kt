package de.jensklingenberg.sheasy.web.ui.apps

import de.jensklingenberg.sheasy.web.data.FileDataSource
import de.jensklingenberg.sheasy.web.model.Error
import de.jensklingenberg.sheasy.web.model.response.App
import de.jensklingenberg.sheasy.web.network.ResponseCallback

class AppsPresenter(private val view: AppsContract.View, val fileDataSource : FileDataSource) :
    AppsContract.Presenter {


    var appsResult = listOf<App>()

    /****************************************** React Lifecycle methods  */
    override fun componentWillUnmount() {}

    override fun componentDidMount() {}

    /****************************************** Presenter methods  */
    override fun onSearch(query: String) {
        appsResult
            .filter {
                it.name.contains(query, true)
            }
            .run(view::setData)
    }


    override fun getApps() {

        fileDataSource.getApps( callback = object : ResponseCallback<List<App>> {
         override fun onSuccess(data: List<App>) {
             appsResult = data
             view.setData(appsResult)
         }

         override fun onError(error: Error) {
             view.showError(error)
         }


     })
    }

    override fun downloadApk(app:App?) {
       fileDataSource.downloadApk(app)
    }
}
