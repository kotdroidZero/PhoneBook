package com.error_found.kotdroid.phonebook.models.networkrequest;

import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by Mukesh on 06-06-2016.
 */
public interface API {


    @FormUrlEncoded
    @POST(WebConstants.LOGIN)
    Call<PojoUserLogin> loginUser(@Field("email") String email,
                                            @Field("password") String password,
                                            @Field("device_type") String deviceType);

    @Multipart
    @POST(WebConstants.ADD_GUEST)
    Call<PojoUserLogin> addGuest(@Part("session_id")    RequestBody sessionId,
                                 @Part MultipartBody.Part image,
                                 @Part("name") RequestBody name,
                                 @Part("gender") RequestBody gender,
                                 @Part("status") RequestBody status,
                                 @Part("timezone") RequestBody timezone);

    @GET(WebConstants.GET_ALL_POST)
    Call<List<PojoLogin>> getPost(@Query(("session_id"))String sessionId,
                                  @Query(("post type"))String postType,
                                  @Query(("filter"))String filter,
                                  @Query(("locale"))String locale,
                                  @Query(("limit"))String limit,
                                  @Query(("page"))String page);

    @GET("guest")
    Call<List<PojoUserLogin>> getAllGuests(@Query("session_id") String sessionId,
                                           @Query("timezone") String timeZone,
                                           @Query("status") String status);

    @Multipart
    @POST(WebConstants.LOGIN)
    Call<PojoLogin> loginToMyndside(@PartMap Map<String,RequestBody> hashMap);







  /*  @Multipart
    @POST("partner/about-me")
    Observable<Response<PojoUserLogin>> saveBasicInfo(@Part("session_id") RequestBody sessionId,
                                                      @Part("service_ids") RequestBody serviceIds,
                                                      @Part MultipartBody.Part picture,
                                                      @Part MultipartBody.Part coverPicture,
                                                      @Part("name") RequestBody fullName,
                                                      @Part("email") RequestBody email,
                                                      @Part("password") RequestBody password,
                                                      @Part("country_code") RequestBody countryCode,
                                                      @Part("phone") RequestBody phone,
                                                      @Part("gender") RequestBody gender,
                                                      @Part("qualification") RequestBody qualification,
                                                      @Part("experience") RequestBody experience,
                                                      @Part("device_id") RequestBody deviceId,
                                                      @Part("device_type") RequestBody deviceType,
                                                      @Part("device_token") RequestBody deviceToken,
                                                      @Part("timezone") RequestBody timezone,
                                                      @Part("locale") RequestBody locale);

    @Multipart
    @POST("partner/work-photos")
    Observable<Response<PojoCommon>> uploadWorkPhotos(@PartMap Map<String, RequestBody> hashMap);*/


}
