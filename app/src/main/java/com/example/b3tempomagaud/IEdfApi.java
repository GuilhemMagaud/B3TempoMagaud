package com.example.b3tempomagaud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IEdfApi {
    String EDF_TEMPO_API_ALERT_DATE = "2022-11-21";
    String EDF_TEMPO_API_ALERT_TYPE = "TEMPO";


    @GET("/services/rest/referentiel/getNbTempoDays")
    Call<TempoDaysLeft> getTempoDaysLeft(@Query("TypeAlete") String alerteType);

    //https://particulier.edf.fr/services/rest/referentiel/searchTempoStore?dateRelevant=2022-11-21&TypeAlerte=TEMPO
    @GET("/services/rest/referentiel/searchTempoStore")
    Call<TempoDaysColor> getTempoDaysColor(
            @Query("dateRelevant") String dateBegin,
            @Query("TypeAlete") String alerteType);

    @GET("services/rest/referentiel/historicTEMPOStore")
    Call<TempoHistory> getTempoHistory(
            @Query("dateBegin") String dateBegin,
            @Query("dateEnd") String dateEnd
    );
}