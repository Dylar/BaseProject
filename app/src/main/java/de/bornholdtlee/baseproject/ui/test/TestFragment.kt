package de.bornholdtlee.baseproject.ui.test

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_TEST
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseTab
import de.bornholdtlee.baseproject.database.room.UserData
import de.bornholdtlee.baseproject.database.room.UserDao
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.utils.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

const val USER_NAME = "Ajay"

class TestFragment : BaseFragment(), IInjection, NavigationBaseTab {

    companion object {
        fun createInstance(): TestFragment {
            return TestFragment()
        }
    }

    @Inject
    lateinit var userDao: UserDao

    @BindView(R.id.fragment_first_name)
    lateinit var firstET: EditText
    @BindView(R.id.fragment_last_name)
    lateinit var lastET: EditText
    @BindView(R.id.fragment_db_list)
    lateinit var listView: ListView

    override val navigationPosition: Int = TAB_TEST
    override val layoutId: Int = R.layout.fragment_test

    private val disposable = CompositeDisposable()

    private lateinit var userData: List<UserData>

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        disposable.add(userDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    Logger.error("ONLY ON CREATE")
                    Logger.info("USER COUNT: ${list.size}")
                    updateList(list)
                }, {
                    Logger.error("Unable to check DB")
                    it.printStackTrace()
                }))

        observeDb()
        observeDbByName()
    }

    private fun initViews() {
        listView.adapter = object : BaseAdapter() {
            override fun getView(index: Int, view: View?, parent: ViewGroup?): View {
//                val func = { it: View -> (it as TextView).text = getItem(index).toString() }
//                return view?.also(func) ?: TextView(context).also(func)
                val func = { it: View -> (it as TextView).text = getItem(index).toString() }
                return (view ?: TextView(context)).also(func)
            }

            override fun getItem(index: Int): UserData {
                return userData[index]
            }

            override fun getItemId(index: Int): Long {
                return index.toLong()
            }

            override fun getCount(): Int {
                return if (::userData.isInitialized) userData.size else 0
            }
        }
    }

    private fun updateList(list: List<UserData>) {
        userData = list
        (listView.adapter as BaseAdapter).notifyDataSetChanged()
    }

    private fun observeDb() {
        disposable.add(userDao.observeAllFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCancel { Logger.error("ON CANCEL: FLOWABLE") }
                .doOnNext { Logger.error("ON NEXT: FLOWABLE") }
                .doOnTerminate { Logger.error("ON TERMINATE: FLOWABLE") }
                .doFinally { Logger.error("ON FINALLY: FLOWABLE") }
                .doOnComplete { Logger.error("ON COMPLETE: FLOWABLE") }
                .subscribe({ list ->
                    Logger.error("EACH UPDATE: FLOWABLE")
                    Logger.error("USER COUNT: ${list.size}")
                    updateList(list.reversed())
                }, {
                    Logger.error("Unable to check DB")
                    it.printStackTrace()
                }))

        disposable.add(userDao.observeAllObserver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose { Logger.error("ON DISPOSE: OBSERVER") }
                .doOnNext { Logger.error("ON NEXT: OBSERVER") }
                .doOnTerminate { Logger.error("ON TERMINATE: OBSERVER") }
                .doFinally { Logger.error("ON FINALLY: OBSERVER") }
                .doOnComplete { Logger.error("ON COMPLETE: OBSERVER") }
                .subscribe({ list ->
                    Logger.error("EACH UPDATE: OBSERVER")
                    Logger.error("USER COUNT: ${list.size}")
//                    updateList(list.reversed())
                }, {
                    Logger.error("Unable to check DB")
                    it.printStackTrace()
                }))
    }

    private fun observeDbByName() {
        disposable.add(userDao.observeByName("FIRST")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    Logger.error("LISTEN NAME: ${list.size}")
                    list.forEach { Logger.error("--> ${it.firstName}, ${it.firstName}") }
                }, {
                    Logger.error("Unable to check DB")
                    it.printStackTrace()
                }))
    }

    @OnClick(R.id.fragment_dispose)
    override fun onStop() {
        super.onStop()

        // clear all the subscription
        disposable.clear()
    }

    @OnClick(R.id.fragment_add_user)
    fun addUser() {
        val userName = firstET.text.toString()
        val lastName = lastET.text.toString()
        val age = (Math.random() * 42 + 18).toInt()
        // Disable the update button until the user name update has been done
//        view.isEnabled = false
        // Subscribe to updating the user name.
        // Enable back the button once the user name has been updated

        val user = UserData(UUID.randomUUID().toString(), userName, lastName, age)

        disposable.add(userDao.insertAll(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.error("UserData added $userName, $lastName, $age")
                    checkDB()
                    //                    view.isEnabled = true
                }, {
                    Logger.error("Unable to update username")
                    it.printStackTrace()
                }))
    }

    //    fragment_dispose
    @OnClick(R.id.fragment_delete_user)
    fun deleteUser() {
        val firstName = firstET.text.toString()
        val lastName = lastET.text.toString()

        disposable.add(Single.fromCallable { userDao.deleteByName(firstName, lastName) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.error("UserData deleted $firstName, $lastName")
                    Logger.error("Deleted Rows: $it")
                    checkDB()
                    //                    view.isEnabled = true
                }, {
                    Logger.error("Unable to update username")
                    it.printStackTrace()
                }))
    }

    override fun onResume() {
        super.onResume()
        checkDB()
    }

    private fun checkDB() {
//
//
//        val userIds = ArrayList<String>()
//        userData?.let { list ->
//            list.forEach {
//                userIds.add(it.uuid)
//            }
//        }
//        disposable.add(database.userDao().getAllByIds(*userIds.toTypedArray())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ Logger.error("LOAD IDS: ${it.size}") },
//                        {
//                            Logger.error("Unable to load ids")
//                            it.printStackTrace()
//                        }))
    }
//
//    @OnClick(R.id.fragment_main_counter_button)
//    fun incrementCounter() {
////        welcomeText.text = (++counter).toString()
////        populateWithTestData()
//    }
}