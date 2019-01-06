package de.jensklingenberg.sheasy.web.ui.files

import de.jensklingenberg.sheasy.web.model.Error
import de.jensklingenberg.sheasy.web.model.response.FileResponse
import de.jensklingenberg.sheasy.web.data.AppsDataSource
import de.jensklingenberg.sheasy.web.model.File
import de.jensklingenberg.sheasy.web.network.ResponseCallback

class FilesPresenter(val view: FilesContract.View, val appsDataSource: AppsDataSource) : FilesContract.Presenter {
    override fun uploadFile(file: File) {


    }

    val defaultPath = "/"
    var folderPath = defaultPath
    var filesResult = listOf<FileResponse>()

    /****************************************** React Lifecycle methods  */
    override fun componentWillUnmount() {}

    override fun componentDidMount() {}

    /****************************************** Presenter methods  */
    override fun navigateUp() {
        folderPath = folderPath.replaceAfterLast("/", "")
        getFiles()
    }

    override fun setPath(path: String) {
        this.folderPath = path
        getFiles()
    }

    override fun getFiles() {
        appsDataSource.getFiles(
            folderPath = folderPath, callback = object : ResponseCallback<List<FileResponse>> {
                override fun onSuccess(data: List<FileResponse>) {
                    filesResult = data
                    view.setData(data)
                }

                override fun onError(error: Error) {
                    view.showError(error)
                }

            }


        )
    }
}