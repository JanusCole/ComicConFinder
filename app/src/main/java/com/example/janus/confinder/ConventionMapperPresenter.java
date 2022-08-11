package com.example.janus.confinder;

import com.example.janus.confinder.data.Convention;
import com.example.janus.confinder.data.ConventionsEventService;

import java.util.List;

/*
 This is the presenter for the mapping view. It gets a list of upcoming convention events and then obtains their
 respective locations, passing this info back to the mapping view.
*/

public class ConventionMapperPresenter implements ConventionMapperContract.Presenter {

        private ConventionMapperContract.View conventionMapperView;
        private ConventionsEventService conventionsEventService;

        public ConventionMapperPresenter(ConventionMapperContract.View conventionMapperView, ConventionsEventService conventionsEventService) {
            this.conventionMapperView = conventionMapperView;
            this.conventionsEventService = conventionsEventService;
        }

        public void mapConventions() {

            // Call the Conventions Event service first to get the actual events. Then pass them on to the locator service for mapping objects
            conventionsEventService.getConventionEvents(new ConventionsEventService.ConventionEventsCallback() {
                @Override
                public void onConventionsComplete(List<Convention> conventions) {
                    for (int conventionIndex = 0; conventionIndex < conventions.size();conventionIndex++) {
                        conventionMapperView.mapConvention(conventions.get(conventionIndex));
                    }
                    conventionMapperView.completeMap();
                }

                @Override
                public void onNetworkError() {
                    conventionMapperView.displayNetworkError();
                }
            });

        }

}
