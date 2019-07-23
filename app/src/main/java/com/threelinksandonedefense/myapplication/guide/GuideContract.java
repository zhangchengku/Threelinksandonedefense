package com.threelinksandonedefense.myapplication.guide;

import android.content.Context;

import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GuideContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
