/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package za.co.tbt.mydining.location;

import com.google.android.gms.maps.model.LatLng;

import za.co.tbt.mydining.R;
import android.content.Context;
import android.location.Location;

/**
 * Defines app-wide constants and utilities
 */
public final class LocationUtils {

    // Name of shared preferences repository that stores persistent state
    public static final String SHARED_PREFERENCES =
            "com.example.android.location.SHARED_PREFERENCES";

    // Key for storing the "updates requested" flag in shared preferences
    public static final String KEY_UPDATES_REQUESTED =
            "com.example.android.location.KEY_UPDATES_REQUESTED";

    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    /**
     * Get the latitude and longitude from the Location object returned by
     * Location Services.
     *
     * @param currentLocation A Location object containing the current location
     * @return The latitude and longitude of the current location, or null if no
     * location is available.
     */
    public static LatLng getLatLng(Context context, Location currentLocation) {
        // If the location is valid
        if (currentLocation != null) {
        	// Return the latitude and longitude as strings
            return new LatLng( currentLocation.getLatitude(),currentLocation.getLongitude());
        } else {
            // Otherwise, return the empty string
            return new LatLng(-26.204, 28.045);
        }
    }
}
