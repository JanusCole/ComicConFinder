package com.example.janus.confinder.data;

import java.util.List;

public interface ConventionsEventService {

    interface ConventionEventsCallback {
        void onConventionsComplete(List<Convention> conventions);
        void onNetworkError();
    }

    void getConventionEvents(ConventionEventsCallback conventionEventsCallback);

}
