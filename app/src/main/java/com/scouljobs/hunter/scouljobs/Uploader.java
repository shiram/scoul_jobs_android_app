package com.scouljobs.hunter.scouljobs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Uploader {

    Context context;
    ProgressDialog progressDialog;
    private CustomToast customToast;

    private String server_msg;
    private int id;
    private int user_id;
    private SharedPreferences sharedPreferences;

    public Uploader(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
        customToast = new CustomToast(context);
    }


    public void register(final String firstname, final String lastname, final String dob, final String nationality, final String user_email, final String phone, final String education, final String address, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");
                    id = result.getInt("user_id");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                    Log.d("REGISTER ERROR: ",e.getMessage());
                }
                progressDialog.dismiss();
                if(id > 0) {
                    customToast.setToast(server_msg);
                    Intent intent = new Intent(context, MainActivity.class);
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
                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.FIRSTNAME, firstname);
                params.put(Config.LASTNAME, lastname);
                params.put(Config.DOB, dob);
                params.put(Config.NATIONALITY, nationality);
                params.put(Config.EMAIL, user_email);
                params.put(Config.PHONE, phone);
                params.put(Config.EDUCATION, education);
                params.put(Config.ADDRESS, address);
                params.put(Config.PASSWORD, password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


    public void addJobs(final String title, final String category, final String description, final String qualifications, final String requirements) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADD_JOBS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                    Log.d("JSON ERROR: ",e.getMessage());

                }
                progressDialog.dismiss();
                customToast.setToast(server_msg);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
//                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.TITLE, title);
                params.put(Config.CATEGORY, category);
                params.put(Config.DESCRIPTION, description);
                params.put(Config.QUALIFICATIONS, qualifications);
                params.put(Config.REQUIREMENTS, requirements);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public void updateJobs(final int id, final String title, final String category, final String description, final String qualifications, final String requirements) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.UPDATE_JOB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    customToast.setToast(Config.NO_SERVER_RESPONSE);
                    Log.d("JSON ERROR: ",e.getMessage());

                }
                progressDialog.dismiss();
                customToast.setToast(server_msg);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
//                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.JOB_ID, Integer.toString(id));
                params.put(Config.TITLE, title);
                params.put(Config.CATEGORY, category);
                params.put(Config.DESCRIPTION, description);
                params.put(Config.QUALIFICATIONS, qualifications);
                params.put(Config.REQUIREMENTS, requirements);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



    /*
        public void makeMarketer() {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADD_MARKETER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject result = new JSONObject(response);
                        server_msg = result.getString("success");

                    }catch (JSONException e){
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                        Log.d("JSON ERROR: ",e.getMessage());

                    }
                    progressDialog.dismiss();
                    customToast.setToast(server_msg);

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            customToast.setToast(Config.NO_SERVER_RESPONSE);
    //                        Log.d("SERVER ERROR: ",error.getMessage());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.USER_ID, Integer.toString(getUserId()));

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }


        public void requestRights() {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REQUEST_RIGHTS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject result = new JSONObject(response);
                        server_msg = result.getString("success");

                    }catch (JSONException e){
                        progressDialog.dismiss();
                        customToast.setToast(Config.NO_SERVER_RESPONSE);
                        Log.d("JSON ERROR: ",e.getMessage());

                    }
                    progressDialog.dismiss();
                    customToast.setToast(server_msg);

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            customToast.setToast(Config.NO_SERVER_RESPONSE);
    //                        Log.d("SERVER ERROR: ",error.getMessage());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.USER_ID, Integer.toString(getUserId()));

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    */
 public int getUserId(){

     sharedPreferences = context.getSharedPreferences(Config.MYPREFERENCES, context.MODE_PRIVATE);
      user_id = sharedPreferences.getInt("session_id", 0);

      return user_id;
 }

}
