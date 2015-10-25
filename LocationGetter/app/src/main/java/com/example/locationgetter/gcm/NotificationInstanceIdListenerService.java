package com.example.locationgetter.gcm;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by toothless on 24/10/15.
 */
public class NotificationInstanceIdListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
