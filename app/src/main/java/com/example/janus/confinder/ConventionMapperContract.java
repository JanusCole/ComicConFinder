package com.example.janus.confinder;

import com.example.janus.confinder.data.Convention;

// Specifies the contract between the map presenter and view

public interface ConventionMapperContract {

    interface View {
        void mapConvention(Convention conventionLocation);
        void completeMap();
        void displayNetworkError();
    }

    interface Presenter {
        void mapConventions();
    }

}