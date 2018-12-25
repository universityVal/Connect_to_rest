package android.dev.alex.connect_to_rest.api;

import android.dev.alex.connect_to_rest.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPost();
}
