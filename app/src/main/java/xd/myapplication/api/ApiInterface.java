package xd.myapplication.api;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xd.myapplication.models.AccessToken;
import xd.myapplication.models.Category;
import xd.myapplication.models.Complaint;
import xd.myapplication.models.User;

public interface ApiInterface {

    String ACCESS_TOKEN = "10JHg2HD9AjO9g0sZ7VZOWePyfQU2FUBWHFAfHTqAnKuCTnT8O9WZaotbFvQgzJc";

    @Headers("Authorization: " + ACCESS_TOKEN)
    @GET("Categories")
    Call<List<Category>> getCategories();

    @Headers("Authorization: " + ACCESS_TOKEN)
    @GET("Complaints")
    Call<List<Complaint>> getComplaints();

    @Headers("Authorization: " + ACCESS_TOKEN)
    @POST("Complaints")
    Call<Complaint> createComplaint(@Body Complaint complaint);

    @POST("Users/login")
    Call<AccessToken> login(@Body User user);

    @POST("Users")
    Call<User> signUp(@Body User user);

    @GET("Complaints/{id}?filter={\"include\": \"pictures\"}")
    Call<Complaint> complaint(@Path("id") int complaintId);


}