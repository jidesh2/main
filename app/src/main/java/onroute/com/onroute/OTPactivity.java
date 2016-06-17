package onroute.com.onroute;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.widget.TextView;

public class OTPactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        final SharedPreferences sp2 = getSharedPreferences("OTPpref", Activity.MODE_PRIVATE);
        final String marcus = sp2.getString("OTP", null);
        final TextView textView=(TextView)findViewById(R.id.phone_number_field);
        View test0View = findViewById(R.id.ph1);
        Button bt1= (Button) test0View.findViewById(R.id.auth_phone_btn_enter);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String tf=textView.getText().toString();
                String number;

                number = textView.getText().toString();
                int g = Integer.parseInt(number);
                if (number == null || number.length() < 4 || number.length() > 4) {

                    Toast.makeText(getApplicationContext(), "Enter valid 10 digit phone number!", Toast.LENGTH_LONG).show();

                } else {
                    if(number.equals(marcus))
                    {
                        Toast.makeText(getApplicationContext(), marcus, Toast.LENGTH_LONG).show();
                        Intent in = new Intent(getApplicationContext(), PlacesAutoCompleteActivity.class);
                        startActivity(in);
                        sp2.edit().clear();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), marcus, Toast.LENGTH_LONG).show();
                      //  sp2.edit().clear();
                    }


                }
            }
        });
        View test1View = findViewById(R.id.ph1);
        Button bt0= (Button) test1View.findViewById(R.id.auth_phone_btn_1);
        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"1");
            }
        });
        View but=findViewById(R.id.ph1);
        Button one=(Button)but.findViewById(R.id.auth_phone_btn_delete);
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
        View butt=findViewById(R.id.ph1);
        Button two=(Button)butt.findViewById(R.id.auth_phone_btn_2);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"2");
                // textView.setText("2");
            }
        });
        View buttu=findViewById(R.id.ph1);
        Button three=(Button)buttu.findViewById(R.id.auth_phone_btn_3);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"3");
                // textView.setText("2");
            }
        });
        View butti=findViewById(R.id.ph1);
        Button four=(Button)butti.findViewById(R.id.auth_phone_btn_4);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"4");
                // textView.setText("2");
            }
        });
        View butto=findViewById(R.id.ph1);
        Button five=(Button)butto.findViewById(R.id.auth_phone_btn_5);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"5");
                // textView.setText("2");
            }
        });
        View buttp=findViewById(R.id.ph1);
        Button six=(Button)buttp.findViewById(R.id.auth_phone_btn_6);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"6");
                // textView.setText("2");
            }
        });
        View butta=findViewById(R.id.ph1);
        Button seven=(Button)butta.findViewById(R.id.auth_phone_btn_7);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"7");
                // textView.setText("2");
            }
        });
        View butts=findViewById(R.id.ph1);
        Button eight=(Button)butts.findViewById(R.id.auth_phone_btn_8);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"8");
                // textView.setText("2");
            }
        });
        View buttf=findViewById(R.id.ph1);
        Button nine=(Button)buttf.findViewById(R.id.auth_phone_btn_9);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"9");
                // textView.setText("2");
            }
        });
        View buttg=findViewById(R.id.ph1);
        Button zero=(Button)buttg.findViewById(R.id.auth_phone_btn_0);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"0");
                // textView.setText("2");
            }
        });



    }
}
