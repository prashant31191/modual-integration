package com.appinfosdk.utils;

/**
 * Created by prashant.patel on 5/25/2017.
 */

public class AppInfoSet
{

    //AppInfoListener appInfoListener;

    ErrorModel errorModel;
    SucessModel sucessModel;





    public void setErrorModel(ErrorModel errorModel) {
        this.errorModel = errorModel;

        if(CommonObjects.getActivityCommon() !=null)
        {
            AppInfoListener listener = (AppInfoListener) CommonObjects.getActivityCommon();
            listener.onFailure(errorModel);
        }
    }

    public void setSucessModel(SucessModel sucessModel) {
        this.sucessModel = sucessModel;
        if(CommonObjects.getActivityCommon() !=null)
        {
            AppInfoListener listener = (AppInfoListener) CommonObjects.getActivityCommon();
            listener.onSuccess(sucessModel);
        }
    }


}
