package fourthweek.myapplication;

import fourthweek.myapplication.Model.StackOverflowModel;
import retrofit2.Call;
import retrofit2.http.GET;


public interface RetrofitService {

    @GET("questions?page=1&pagesize=10&order=desc&sort=activity&site=stackoverflow")
    Call<StackOverflowModel> getStackModel();
}
