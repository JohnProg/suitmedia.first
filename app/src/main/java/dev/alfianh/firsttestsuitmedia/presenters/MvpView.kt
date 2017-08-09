package dev.alfianh.firsttestsuitmedia.presenters


interface MvpView {

    fun onShowLoading()

    fun onDismissLoading()

    fun onFailed(message: String)

}
