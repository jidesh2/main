package onroute.com.onroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jidesh on 06-12-2015.
 */
public class fragmentfive extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewroot= inflater.inflate(R.layout.fragment_five_layout,container,false);
        TextView textView = (TextView) viewroot.findViewById(R.id.text5);

        String text = "<font color=#FFFFFF>Tap to start</font> " +
                "<font color=#F5A623> OnRoute</font>";
        textView.setText(Html.fromHtml(text));
        TextView textView2 = (TextView) viewroot.findViewById(R.id.screen_map_connect_to_wifi_text_view);

        String text1 = "<font color=#FFFFFF>Find the best route  to get <br> " +
                "to your destination & </font> " +
                "<font color=#F5A623> more</font>";
        textView2.setText(Html.fromHtml(text1));
        LinearLayout l1=(LinearLayout)viewroot.findViewById(R.id.lin1);
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
