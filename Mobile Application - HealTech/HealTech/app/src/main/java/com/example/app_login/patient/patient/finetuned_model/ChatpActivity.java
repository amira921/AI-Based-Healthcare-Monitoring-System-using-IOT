package com.example.app_login.patient.patient.finetuned_model;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_login.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ChatpActivity extends AppCompatActivity {

    RecyclerView recyclerViewM;
    TextView welcomeTextViewM;
    EditText messageEditTextM;
    ImageView sendButtonM;
    List<MessageModel> messageListM;
    MessageModelAdapter messageAdapterM;
    ArrayList<String> arrayListM = new ArrayList<>();
    String FullTextM = "";



    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatp);


        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Teal5)));

        /////////////////////////////////////////////////////////////////////////////////////

        messageListM = new ArrayList<>();
        recyclerViewM = findViewById(R.id.chat_recycle_view_model);
        welcomeTextViewM = findViewById(R.id.welcome_text_model);
        messageEditTextM = findViewById(R.id.message_edit_model);
        sendButtonM = findViewById(R.id.send_btn_model);


        messageAdapterM = new MessageModelAdapter(messageListM);
        recyclerViewM.setAdapter(messageAdapterM);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerViewM.setLayoutManager(llm);

        sendButtonM.setOnClickListener((v)->
        {
            String question = messageEditTextM.getText().toString();
            addToChat(question,MessageModel.SENT_BY_ME);

            System.out.println("quesstionnnnnnnnnnnnnnnn"+question);
            messageEditTextM.setText("");
            try {

                callAPI(question);
            } catch (ProtocolException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            welcomeTextViewM.setVisibility(View.GONE);
        });

    }

    ///////////////////////////////////////////////////////////////////////////
    void addToChat(String messageModel,String sentByModel)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                messageListM.add(new MessageModel(messageModel,sentByModel));
                messageAdapterM.notifyDataSetChanged();
                recyclerViewM.smoothScrollToPosition(messageAdapterM.getItemCount());
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    void addResponse(String response)
    {
        messageListM.remove(messageListM.size()-1);
        addToChat(response,MessageModel.SENT_BY_BOT);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
//api

    void callAPI(String question) throws ProtocolException {
        //okhttp
        System.out.println("quesvvvvvvvvvv"+question);
        messageListM.add(new MessageModel("Typing... ",MessageModel.SENT_BY_BOT));
        JSONObject jsonBody = new JSONObject();
        try
        {

            jsonBody.put("prompt",question);
            jsonBody.put("max_tokens",4000);
            jsonBody.put("temperature",0);
            System.out.println("jsonzzz"+jsonBody.toString());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }


        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api-inference.huggingface.co/models/Amira2045/BioGPT-Finetuned")
                .header( "Authorization", "Bearer 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")
                .post(body)
                .build();
        System.out.println("requestcccc"+request.toString());

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {
                System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
                addResponse("Failed to load response due to "+e.getMessage().toString());
            }


            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("responsssssssssszzzzzzzzzzzzzzzzz:"+response.toString());
                if (response.isSuccessful()) {
                    System.out.println("responsssssssssseeeeee:"+response.toString());
                    try {
                        String responseBody = response.body().string(); // Convert response body to string
                        System.out.println("responssssssssss4444444:"+response.toString());
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("errrrrrrzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
                    addResponse("Failed to load response due to " + response.body().string());
                }
            }
        });
    }




//
//    public class ModelAPIRequest extends AsyncTask<Void, Void, String> {
//
//        private static final String apiUrl = "https://api-inference.huggingface.co/models/Amira2045/BioGPT-Finetuned";
//        private static final String token = "Bearer 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";
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            String result = null;
//            HttpURLConnection urlConnection = null;
//            try {
//                URL url = new URL(apiUrl);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.setRequestProperty("Authorization", token);
//
//                int responseCode = urlConnection.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    InputStream inputStream = urlConnection.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    result = response.toString();
//                } else {
//                    Log.e("ModelAPIRequest", "HTTP error code: " + responseCode);
//                }
//            } catch (Exception e) {
//                Log.e("ModelAPIRequest", "Error fetching data from API: " + e.getMessage());
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            // Handle the result here
//            Log.d("ModelAPIRequest", "Result: " + result);
//            // You can parse the result or pass it to another method for further processing
//        }
//    }
}