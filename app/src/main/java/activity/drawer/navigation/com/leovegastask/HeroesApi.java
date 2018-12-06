package activity.drawer.navigation.com.leovegastask;

import activity.drawer.navigation.com.leovegastask.Pojo.HeroesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HeroesApi {
    @GET("v1/public/characters")
    Call<HeroesResponse> getHeroes(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);

    @GET("v1/public/characters")
    Call<HeroesResponse> findHeroByName(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash, @Query("nameStartsWith") String nameStartsWith);

    @GET("v1/public/characters/{id}")
    Call<HeroesResponse> getHeroById(@Path("id") int id, @Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);
}
