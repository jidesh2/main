package onroute.com.onroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentSimpleLoginButton extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewroot = inflater.inflate(R.layout.fragment_simple_login_button, container, false);
        TextView textView = (TextView) viewroot.findViewById(R.id.text2);

        String text = "<font color=#FFFFFF>Tap to start</font> " +
                "<font color=#F5A623> OnRoute</font>";
        textView.setText(Html.fromHtml(text));
        LinearLayout l1=(LinearLayout)viewroot.findViewById(R.id.lin4);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),EnterPhoneScreenActivity.class);
                startActivity(in);
            }
        });
    return  viewroot;
    }




}
