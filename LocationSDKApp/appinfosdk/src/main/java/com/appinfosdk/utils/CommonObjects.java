package com.appinfosdk.utils;

import android.app.Activity;

/**
 * Created by prashant.patel on 5/25/2017.
 */

public class CommonObjects
{
   public static  Activity activityCommon;
   public static  String str1;

   public static void setActivityCommon(Activity aaa) {
      activityCommon = aaa;
   }

   public static Activity getActivityCommon() {
      return activityCommon;
   }
}
