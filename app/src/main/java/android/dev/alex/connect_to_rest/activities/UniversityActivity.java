package android.dev.alex.connect_to_rest.activities;

import android.dev.alex.connect_to_rest.R;
import android.dev.alex.connect_to_rest.api.GsonApiConnect;
import android.dev.alex.connect_to_rest.model.University;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UniversityActivity extends AppCompatActivity {

    private TextView textviewDataUniversity;
    private GsonApiConnect gsonApiConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        textviewDataUniversity=(TextView)findViewById(R.id.textviewData_University);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.1.103:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();




            gsonApiConnect = retrofit.create(GsonApiConnect.class);


        try {
            getUniversity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getSpecificUniversity();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getUniversity() {

        Call<List<University>> call = gsonApiConnect.getUniversities();
        call.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {

                if (!response.isSuccessful())
                {
                    textviewDataUniversity.setText("Code: "+response.code());
                    return;
                }
                List<University> universities = response.body();
                for (University university : universities)
                {
                    String content=" ";
                    content +="ID: "+ university.getId()+"\n";
                    content +="Name: "+ university.getName()+"\n";
                    content +="Accreditation Level"+university.getAccreditation_level()+"\n";
                    content +="Creation Date"+university.getCreation_date()+"\n";
                    content +="Address"+university.getAddress()+"\n";
                    content +="Phone"+university.getPhone()+"\n\n";

                    textviewDataUniversity.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                textviewDataUniversity.setText(t.getMessage());
            }
        });
    }

        private void getSpecificUniversity()
        {
            Call<University> call = gsonApiConnect.getSpecificUniversity(2);
            call.enqueue(new Callback<University>() {
                @Override
                public void onResponse(Call<University> call, Response<University> response) {
                    if (!response.isSuccessful())
                    {
                        textviewDataUniversity.setText("Code: "+response.code());
                    }
                    University university= response.body();
                    String content="";
                    content+="ID: "+university.getId()+"\n";
                    content+="Name: "+university.getName()+"\n";
                    content+="Accreditation Level: "+university.getAccreditation_level()+"\n";
                    content+="Creation Date: "+university.getCreation_date()+"\n";
                    content+="Address: "+university.getAddress()+"\n";
                    content+="Phone: "+university.getPhone()+"\n";

                    textviewDataUniversity.append(content);
                }

                @Override
                public void onFailure(Call<University> call, Throwable t) {

                }
            });
        }
}
