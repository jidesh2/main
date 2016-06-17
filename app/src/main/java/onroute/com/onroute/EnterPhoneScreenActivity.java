package onroute.com.onroute;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.*;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class EnterPhoneScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_enter_phone_number);
        View personaview = findViewById(R.id.login_personas);
       final ToggleButton tg= (ToggleButton) personaview.findViewById(R.id.persona_airport);
        final ToggleButton tg1=(ToggleButton)personaview.findViewById(R.id.persona_bar);
        final ToggleButton tg2=(ToggleButton)personaview.findViewById(R.id.persona_camera);
        final ToggleButton tg3=(ToggleButton)personaview.findViewById(R.id.persona_office);
        final ToggleButton tg4=(ToggleButton)personaview.findViewById(R.id.persona_other);
        final ToggleButton tg5=(ToggleButton)personaview.findViewById(R.id.persona_rest);
        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tg.isChecked()) {
                    tg.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_airplane_clicked));
                    tg1.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_glass_unclicked));
                    tg2.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_camera_unclicked));
                    tg3.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_briefcase_unclicked));
                    tg4.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_taxi_unclicked));
                    tg5.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_food_unclicked));

                } else {
                    tg.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_destination_airplane_unclicked));
                }
            }
        });


        final TextView textView = (TextView) findViewById(R.id.text_view);



        final View test0View = findViewById(R.id.ph);
        Button bt1= (Button) test0View.findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number;

                number = textView.getText().toString();
                double g = Double.parseDouble(number);
                if (number == null || number.length() < 10 || number.length() > 10) {
                    Intent in = new Intent(getApplicationContext(), OTPactivity.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(), "Enter valid 10 digit phone number!", Toast.LENGTH_LONG).show();

                } else {
                    String val = "" + ((int) (Math.random() * 9000) + 1000);
                    SharedPreferences sp2 = getSharedPreferences("OTPpref", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp2.edit();
                    editor.putString("OTP", val);
                    editor.apply();
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to="+number+"&msg=your%20otp%20is%20"+val+"&msg_type=TEXT&userid=2000159070&auth_scheme=plain&password=d8uQOdggE&v=1.1&format=text";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                            new com.android.volley.Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    String search="success";
                                    if (response.toLowerCase().contains(search.toLowerCase())) {

                                        Intent in = new Intent(getApplicationContext(), OTPactivity.class);
                                        startActivity(in);

                                    } else {

                                        Toast.makeText(getApplicationContext(), response,Toast.LENGTH_LONG).show();

                                    }
                                    // Display the first 500 characters of the response string.

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"no mc randi",Toast.LENGTH_LONG).show();
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);



                }
            }

        });

        View test1View = findViewById(R.id.ph);
        Button bt0= (Button) test1View.findViewById(R.id.one);
        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"1");
            }
        });
        View but=findViewById(R.id.ph);
Button one=(Button)but.findViewById(R.id.delet);
       one.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               int length = textView.getText().length();
               if (length > 0) {
                   String text = textView.getText().toString();
                   textView.setText(text.substring(0, text.length() - 1));
               }

          }
       });
        View butt=findViewById(R.id.ph);
       Button two=(Button)butt.findViewById(R.id.two);
       two.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
              textView.setText(textView.getText().toString()+"2");
               // textView.setText("2");
            }
        });
        View buttu=findViewById(R.id.ph);
        Button three=(Button)buttu.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"3");
                // textView.setText("2");
            }
        });
        View butti=findViewById(R.id.ph);
        Button four=(Button)butti.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"4");
                // textView.setText("2");
            }
        });
        View butto=findViewById(R.id.ph);
        Button five=(Button)butto.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"5");
                // textView.setText("2");
            }
        });
        View buttp=findViewById(R.id.ph);
        Button six=(Button)buttp.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"6");
                // textView.setText("2");
            }
        });
        View butta=findViewById(R.id.ph);
        Button seven=(Button)butta.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"7");
                // textView.setText("2");
            }
        });
        View butts=findViewById(R.id.ph);
        Button eight=(Button)butts.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"8");
                // textView.setText("2");
            }
        });
        View buttf=findViewById(R.id.ph);
        Button nine=(Button)buttf.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"9");
                // textView.setText("2");
            }
        });
        View buttg=findViewById(R.id.ph);
        Button zero=(Button)buttg.findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"0");
                // textView.setText("2");
            }
        });








    }
}
