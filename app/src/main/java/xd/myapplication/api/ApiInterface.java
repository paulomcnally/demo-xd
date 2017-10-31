package xd.myapplication.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xd.myapplication.models.AccessToken;
import xd.myapplication.models.Category;
import xd.myapplication.models.Complaint;
import xd.myapplication.models.User;

public interface ApiInterface {

    @GET("Categories")
    Call<List<Category>> getCategories();

    @GET("Complaints")
    Call<List<Complaint>> getComplaints();

    @POST("Complaints")
    Call<Complaint> createComplaint(@Body Complaint complaint);

    @POST("Users/login")
    Call<AccessToken> login(@Body User user);

    @POST("Users")
    Call<User> signUp(@Body User user);


}