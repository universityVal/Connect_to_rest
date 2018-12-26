package android.dev.alex.connect_to_rest.api;

import android.dev.alex.connect_to_rest.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GsonApiConnect {

    @GET("/universities")
    Call<List<University>> getUniversities();

    @GET("/universities/{universityId}")
    Call<University> getSpecificUniversity(@Path("universityId") long id);
}
