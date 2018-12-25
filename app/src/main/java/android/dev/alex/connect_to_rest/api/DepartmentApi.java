package android.dev.alex.connect_to_rest.api;

import android.dev.alex.connect_to_rest.model.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface DepartmentApi {

    @GET("/departments")
    Call<List<Department>> getDepartments();

    @GET("/departments/1")
    Call<Department> getOneDepartment();
}
