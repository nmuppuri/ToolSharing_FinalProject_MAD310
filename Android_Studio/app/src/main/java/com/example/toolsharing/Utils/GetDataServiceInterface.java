package com.example.toolsharing.Utils;

import com.example.toolsharing.PojoClasses.StatusMessage_Pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataServiceInterface {

    @GET("NewAdmin&{id}&{passwd}&{fname}&{lname}&{email}")//&{phone}&{address}")
    Call<StatusMessage_Pojo> getAdminRegister(@Path("id") int id, @Path("passwd") String pwd, @Path("fname") String fn,
                                              @Path("lname") String ln, @Path("email") String email/*, @Path("phone") Long phn,
                                         @Path("address") String addr*/);


    @GET("NewStudent&{id}&{fname}&{lname}&{email}")
    Call<StatusMessage_Pojo> getStudentRegister(@Path("id") int id, @Path("fname") String fn,
                                              @Path("lname") String ln, @Path("email") String email);


    @GET("Login&{id}&{pwd}")
    Call<StatusMessage_Pojo> getlogin(@Path("id") int id, @Path("pwd") String pwd);

    @GET("StudentRegis")
    Call<StatusMessage_Pojo> getstudentRegisDetails();

    @GET("StudentRegisAccept&{student_id}&{status}")
    Call<StatusMessage_Pojo> getStudentRegisAccept(@Path("student_id") int sid, /*@Path("admin_id") int aid,*/ @Path("status") String decision);

}