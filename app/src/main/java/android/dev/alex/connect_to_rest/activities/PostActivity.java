package android.dev.alex.connect_to_rest.activities;

import android.dev.alex.connect_to_rest.R;
import android.dev.alex.connect_to_rest.api.PostApi;
import android.dev.alex.connect_to_rest.model.Post;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    TextView textviewPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        textviewPost=(TextView)findViewById(R.id.textviewPost);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi postApi = retrofit.create(PostApi.class);
        Call<List<Post>> call = postApi.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful())
                {
                    textviewPost.setText("Code: "+response.code());
                }
                List<Post> posts = response.body();
                for (Post post:posts)
                {
                    String content="";
                    content +="ID: "+ post.getId()+"\n";
                    content +="User ID:" + post.getUserId()+"\n";
                    content +="Title: "+ post.getTitle()+"\n";
                    content +="Text: "+post.getText()+"\n\n";

                    textviewPost.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textviewPost.setText(t.getMessage());
            }
        });

    }
}
