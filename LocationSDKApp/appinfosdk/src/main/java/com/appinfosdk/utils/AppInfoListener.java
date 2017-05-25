package com.appinfosdk.utils;

/**
 * Created by prashant.patel on 5/24/2017.
 */

public interface AppInfoListener {

    void onSuccess(SucessModel sucessModel);
    void onFailure(ErrorModel errorModel);
}
