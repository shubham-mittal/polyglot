package in.thegeekybaniya.polyglot;

import in.thegeekybaniya.polyglot.POJO.GetTranslate;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Kabir on 12/04/2017.
 */

public interface ApiInterface {


    @POST("/api/v1.5/tr.json/translate?")
    Call<GetTranslate> getLang(@Query("key") String key,
                               @Query("text") String text,
                               @Query("lang") String lang,
                               @Query("format") String format,
                               @Query("options") int options
    );





}
