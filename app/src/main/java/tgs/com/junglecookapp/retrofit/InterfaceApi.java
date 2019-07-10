package tgs.com.junglecookapp.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tgs.com.junglecookapp.CustomerModel;
import tgs.com.junglecookapp.ExpenseModel;
import tgs.com.junglecookapp.LoginModel;
import tgs.com.junglecookapp.MenuModel;
import tgs.com.junglecookapp.ProductModel;
import tgs.com.junglecookapp.ProfitModel;
import tgs.com.junglecookapp.SaleModel;
import tgs.com.junglecookapp.TableModel;


public interface InterfaceApi {


    @POST("/index.php/admin/WEBSERVICES/login")
    @FormUrlEncoded
    Call<LoginModel> Login(@Field("APIKEY") String APIKEY,
                           @Field("USERNAME") String USERNAME,
                           @Field("PASSWORD") String PWD
    );

    @POST("tablestatus_report")
    @FormUrlEncoded
    Call<TableModel> tablestatus_report(@Field("KEY") String APIKEY

    );

    @POST("customer_report")
    @FormUrlEncoded
    Call<CustomerModel> customer_report(@Field("KEY") String APIKEY

    );

    @POST("productwise_report")
    @FormUrlEncoded
    Call<ProductModel> productwise_report(@Field("KEY") String APIKEY, @Field("search") String search

    );


    @POST("get_sale_report")
    @FormUrlEncoded
    Call<SaleModel> get_sale_report(@Field("KEY") String APIKEY, @Field("date") String date

    );

    @POST("menu_report")
    @FormUrlEncoded
    Call<MenuModel> menu_report(@Field("KEY") String APIKEY, @Field("search") String date

    );


    @POST("profit_report")
    @FormUrlEncoded
    Call<ProfitModel> profit_report(@Field("KEY") String APIKEY

    );



    @POST("expense_report")
    @FormUrlEncoded
    Call<ExpenseModel> expense_report(@Field("KEY") String APIKEY, @Field("date") String date

    );


}
