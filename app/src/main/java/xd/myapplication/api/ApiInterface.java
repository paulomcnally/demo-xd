package xd.myapplication.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xd.myapplication.models.Category;
import xd.myapplication.models.Complaint;

public interface ApiInterface {

    @GET("Categories")
    Call<List<Category>> getCategories();

    @GET("Complaints")
    Call<List<Complaint>> getComplaints();

}