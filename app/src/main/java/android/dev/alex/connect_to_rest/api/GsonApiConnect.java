package android.dev.alex.connect_to_rest.api;

import android.dev.alex.connect_to_rest.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GsonApiConnect {

    @GET("/universities")
    Call<List<University>> getUniversities();
}
