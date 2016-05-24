package younggeeks.com.methystake2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import younggeeks.com.methystake2.MethysApi.MethysApi;

import java.io.IOException;

public class LoginActivity extends Activity {


    /**
     * Samwel Charles
     *
     *Defining instance variables
     */

    private Button loginBtn;

    private EditText username;

    private EditText password;

    private String usernameTxt;
    private String passwordTxt;

    private TextView warningTxt;

    private ProgressDialog progressDialog;

    private String TAG=LoginActivity.class.getSimpleName();

    private  SharedPreferences sharedpreferences;

    private  SharedPreferences.Editor editor;

    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting saved sharedPreference file by the name MethysAuth
        sharedpreferences= getSharedPreferences("MethysAuth", Context.MODE_PRIVATE);

        //retrieving boolean with key logged in
        loggedIn=sharedpreferences.getBoolean("loggedIn",false);

       //checking if user is loggedIn
        //if yes then it will redirect user to the CarListActivity
        if (loggedIn){
            goHome();
        }

        //progress dialog will be visible as long as the retrofit request is
        //still in progress as soon as response from the server is received
        //the dialog is dismissed

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging In.....");
        progressDialog.setMessage("Please Wait........");
        progressDialog.getProgress();


        //defining fields which will contain user crecentials

        username=(EditText)findViewById(R.id.input_username);
        password=(EditText)findViewById(R.id.input_password);

        warningTxt=(TextView)findViewById(R.id.warning);


        //this is login button
        loginBtn=(Button)findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //when user clicks the login Button
                //we get username and password text from
                //edit text and storing them in these variables ie usernameTxt and passwordTxt

                usernameTxt=username.getText().toString();
                passwordTxt=password.getText().toString();

                //progress dialog is shown immediately after login button is pressed
                progressDialog.show();

                //this will show Awesome Dialog when User Credentials are Wrong

                final LovelyInfoDialog dialog= new LovelyInfoDialog(LoginActivity.this)
                        .setTopColorRes(R.color.colorAccent)
                        .setIcon(R.drawable.key);

                //Defining BaseUrl in retrofit and adding Json Parser(Gson)
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://test.s.methysdigital.co.za/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //getting MethysApi Interface in which we defined out path and parameters
                MethysApi service=retrofit.create(MethysApi.class);

                //passing usernameTxt, passwordTxt variables as parameter in login method which was defined
                //in our Api Service Interface
                //they will be used to send POST request
                service.login(usernameTxt,passwordTxt).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //when response from server is received
                        //first thing is first Closing progressDialog
                        progressDialog.cancel();

                        //defining variable which will carry our response from server
                        String resonseTxt= null;

                        //because it will throw IOException if it fails
                        //we try and catch the Exception
                        //we store response in responseTxt Variable
                        try {
                            resonseTxt = response.body().string().toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //checking if credentials were accepted or rejected
                        //if response contains the word success
                        //we are in
                        //Otherwise It will show dialog with "failed:" text
                            if (resonseTxt.contains("success")){
                                //if credentials were ok
                                //save loggedIn variable into SharedPreference for
                                //persistance, as in user will be seen as loggedIn
                                //as long as they dont signout

                               editor=sharedpreferences.edit();
                                editor.putBoolean("loggedIn",true);
                                editor.commit();

                                //then Redirect Logged In User to CarListActivity
                                goHome();

                            }else{
                                //if for some reasons the credentials were not accepted
                                //then we'll print the message from server
                                dialog.setTitle(resonseTxt).show();
                            }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //if the request fail for any reason be it network or anything
                        //we'll print stacktrace
                        t.printStackTrace();
                    }
                });





            }
        });
    }


    /**
     * Samwel Charles
     *
     * Method is responsible for creating an intent(CarListActivity) then starting
     * activity and finally close the current activity using finish() method
     */
    public void goHome(){
        Intent carListIntent=new Intent(getApplicationContext(),CarListActivity.class);
        startActivity(carListIntent);
        finish();
    }
}
