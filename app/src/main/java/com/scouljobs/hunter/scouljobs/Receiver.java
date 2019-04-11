package com.scouljobs.hunter.scouljobs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

    Context context;
    ProgressDialog progressDialog;
    private CustomToast customToast;
    private SharedPreferences sharedPreferences;

    private String server_msg, email, user_firstname, user_lastname, dob, nationality, phone, education, address;
    private int id, access_level;
    private RecyclerView recyclerView;
    AdapterConnector adapterConnector;

    public Receiver(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;

        customToast = new CustomToast(context);
    }

    public Receiver(Context context, ProgressDialog progressDialog, RecyclerView recyclerView) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.recyclerView = recyclerView;
    }

    public void userLogin(final String user_email, final String password) {

        customToast = new CustomToast(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    id = result.getInt("user_id");
                    server_msg = result.getString("success");
                    user_firstname = result.getString("firstname");
                    user_lastname = result.getString("lastname");
                    email = result.getString("email");
                    dob = result.getString("dob");
                    access_level = result.getInt("access_level");
                    phone = result.getString("phone");
                    nationality = result.getString("nationality");
                    address = result.getString("address");
                    education = result.getString("education");
                }catch (JSONException e){
                    progressDialog.dismiss();
                    customToast.setToast(Config.NO_SERVER_RESPONSE);

                }
                progressDialog.dismiss();
                if(id > 0) {
                    customToast.setPrefs(id,user_firstname,user_lastname,email,access_level,dob,nationality,phone,address,education);
                    Intent intent = new Intent(context, Home.class);
                    context.startActivity(intent);
                }else {
                    customToast.setToast(server_msg);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.EMAIL, user_email);
                params.put(Config.PASSWORD, password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }



    public void getJobs() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.GET_JOBS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //jsonBooks(response);
                progressDialog.dismiss();
                if(! (response == null || response.equals("")) ){

                    adapterConnector = new AdapterConnector(recyclerView,response,context);
                    adapterConnector.populateList();

                }else {
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }



    public void deleteJobs(final int id) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DELETE_JOB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //jsonBooks(response);
                progressDialog.dismiss();
                if(! (response == null || response.equals("")) ){

                    adapterConnector = new AdapterConnector(recyclerView,response,context);
                    adapterConnector.populateList();

                }else {
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.JOB_ID, Integer.toString(id));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


  /*

    public void getUserProducts(final int id) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.GET_USER_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //jsonBooks(response);
                progressDialog.dismiss();
                if(! (response == null || response.equals("")) ){

                    //Intent i = new Intent(context, Home.class);
                    sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("USERDATA", response);
                    Log.d("RESPONSE: ", response);
                    editor.apply();
                   // context.startActivity(i);

                }else {
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.USER_ID, Integer.toString(id));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


    public void getRequests() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.GET_ACCOUNTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //jsonBooks(response);
                progressDialog.dismiss();
                if(! (response == null || response.equals("")) ){
                    sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("REQUEST_DATA", response);
                    editor.apply();

                }else {
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
    */
}
