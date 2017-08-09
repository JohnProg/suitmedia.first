package dev.alfianh.firsttestsuitmedia.presenters


interface Presenter<V> {

    fun attachView(mvpView: V)

    fun detachView()

}
