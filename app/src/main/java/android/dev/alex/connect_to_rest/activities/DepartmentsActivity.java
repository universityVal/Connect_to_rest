package android.dev.alex.connect_to_rest.activities;

import android.dev.alex.connect_to_rest.R;
import android.dev.alex.connect_to_rest.api.DepartmentApi;
import android.dev.alex.connect_to_rest.model.Department;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepartmentsActivity extends AppCompatActivity {

    TextView textviewDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        textviewDepartments=(TextView)findViewById(R.id.textviewDepaetments);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.1.103:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DepartmentApi departmentApi = retrofit.create(DepartmentApi.class);

        Call<List<Department>> call = departmentApi.getDepartments();
        call.enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (!response.isSuccessful())
                {
                    textviewDepartments.setText("Code: "+response.code());
                }
                List<Department> departments =response.body();

                for (Department department: departments)
                {
                    String content=" ";
                    content +="ID: "+ department.getId()+"\n";
                    content +="University_ID "+ department.getUniversity_id()+"\n";
                    content +="Name "+department.getName()+"\n\n";


                    textviewDepartments.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                    textviewDepartments.setText(t.getMessage());
            }
        });
    }
}
