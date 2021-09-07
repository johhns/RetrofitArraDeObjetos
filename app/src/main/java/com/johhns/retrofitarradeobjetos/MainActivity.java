package com.johhns.retrofitarradeobjetos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.johhns.retrofitarradeobjetos.Interfase.JsonPlaceHolderApi;
import com.johhns.retrofitarradeobjetos.Modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtJson ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPosts();
    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("https://jsonplaceholder.typícode.com/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build() ;
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create( JsonPlaceHolderApi.class ) ;

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if ( !response.isSuccessful() ){
                    txtJson.setText( "Codigo = " + response.code() );
                }

                List<Posts> listPosts = response.body() ;
                for ( Posts p : listPosts ) {
                    String contenido = "" ;
                    contenido += "userId = " + p.getUserId() + "\n" ;
                    contenido += "title = " + p.getTitle() + "\n" ;
                    contenido += "id = " + p.getId() + "\n" ;
                    contenido += "body = " + p.getBody() + "\n\n" ;
                    //txtJson.setText( contenido );
                    txtJson.setText("Exito");
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
               //txtJson.setText( t.getMessage().toString() );
               txtJson.setText("Ocurrio un error");
            }
        });
    }


}