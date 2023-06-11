package com.chaturvedi.chatgptlagao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
   EditText t;
    Button btn;

   private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    t=findViewById(R.id.textView2);
    btn=findViewById(R.id.button);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String input = t.getText().toString().trim();

            if (!input.isEmpty()) {
                try {
                    String response = sendRequestToChatGPT(input);
                    // Process the response as per your requirements
                } catch (IOException e) {
                    // Handle any communication errors
                }
            }
        }
    });

    }
    private String sendRequestToChatGPT(String input) throws IOException {
        // Replace the URL below with the actual URL of your deployed backend server
        String url = "https://your-backend-server.com/chat";

        String json = "{\"input\": \"" + input + "\"}";
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected response code: " + response);
            }
        }
    }



}
