package com.threelinksandonedefense.myapplication.mains.business;

import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BusinessContract {
    interface View extends BaseView {

    }

    interface  Presenter extends BasePresenter<View> {

    }
}
