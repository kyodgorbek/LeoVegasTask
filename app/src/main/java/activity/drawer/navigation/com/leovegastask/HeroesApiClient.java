package activity.drawer.navigation.com.leovegastask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroesApiClient {
    public static final String publicKey = "4089ee37331d8211b080b047c58f6970";
    public static final String privateKey = "18ccbcb58fdd12c22821f8b633a06f2b433d16c3";
    private static final String ROOT_URL = "https://gateway.marvel.com";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static HeroesApi getApiService() {
        return getRetrofitInstance().create(HeroesApi.class);
    }
}


