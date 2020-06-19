package com.example.splash.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.splash.ClientesDetalleActivity;
import com.example.splash.MainActivity;
import com.example.splash.helpers.QueueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Clientes {
    public int id;
    public String phone;
    public String nickname;
    public  String urlImage;

    public Clientes(int _id, String _phone, String _nickname, String _urlImage) {
        this.id = _id;
        this.phone = _phone;
        this.nickname = _nickname;
        this.urlImage = _urlImage;
    }

    public static ArrayList getCollection() {
        ArrayList<Clientes> collection = new ArrayList<>();
        collection.add(new Clientes(0, "981999923", "Bichito", ""));
        collection.add(new Clientes(0,"9859913923", "Plaga", ""));
        collection.add(new Clientes(0,"981914213", "Libelula", ""));
        collection.add(new Clientes(0,"981914213", "Alcachofa", ""));
        //collection.add(new Clientes("981914213", "Alcachofa", _urlImage: ""));   ERROR NO CARGABA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        return collection;
    }
    public static void injectContactFromCloud(final QueueUtils.QueueObject o,
                                              final Clientes clientes,
                                              final ClientesDetalleActivity _interface){
    String url = "https://reqres.in/api/users/" + clientes.id;

    //AQUI ME QUEDE

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.has("data")) {

                                    try {
                                        JSONObject objecto = response.getJSONObject("data");
                                        clientes.phone = objecto.getString("first_name");
                                        clientes.nickname = objecto.getString("last_name");
                                        clientes.urlImage = objecto.getString("avatar");
                                    } catch (JSONException e) {
                                        //llega aca cuando hay un error dentro Try
                                        e.printStackTrace();
                                    }
                                    _interface.refresh(); // Esta función debemos implementarla
                                    // en nuestro activity
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }


    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Clientes> clientes,
                                               final MainActivity _interface) {
        String url = "http://fipo.equisd.com/api/users.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.has("objects")) {

                                    try {
                                        JSONArray list = response.getJSONArray("objects");
                                        for (int i=0; i < list.length(); i++) {
                                            JSONObject o = list.getJSONObject(i);
                                            clientes.add(new Clientes(o.getInt("id"),o.getString("first_name"),
                                                    o.getString("last_name"), o.getString("avatar")));
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    _interface.refreshList(); // Esta función debemos implementarla
                                    // en nuestro activity
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }


}