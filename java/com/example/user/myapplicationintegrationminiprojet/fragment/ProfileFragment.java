package com.example.user.myapplicationintegrationminiprojet.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.myapplicationintegrationminiprojet.R;

import static com.example.user.myapplicationintegrationminiprojet.Activities.LoginActivity.currentUser;
import static com.example.user.myapplicationintegrationminiprojet.Activities.LoginActivity.user;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView thumbnail;
    private Context context;
    private TextView username_prof , score_eng , score_fr , score_Sp , score_Ger ;
    Typeface OrangeJuce , AgentOrange;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        thumbnail = view.findViewById(R.id.img_profile);
        username_prof = view.findViewById(R.id.username_prof);
        score_eng = view.findViewById(R.id.score_eng);
        score_fr = view.findViewById(R.id.score_fr);
        score_Sp = view.findViewById(R.id.score_Sp);
        score_Ger = view.findViewById(R.id.score_Ger);
        AgentOrange = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AgentOrange.ttf");
        OrangeJuce = Typeface.createFromAsset(getActivity().getAssets(), "fonts/orange juice 2.0.ttf");

     //Uri uriImg = user.getProfilePictureUri(75,75);
      //Log.e("facebook : image" , String.valueOf(uriImg));
        //"http://192.168.1.8/miniProjetRessources/englishflag.jpg"
  String imgTest = "https://graph.facebook.com/v3.2/10212541082352959/picture?height=150&width=150&migration_overrides=%7Boctober_2012%3Atrue%7D";
        Glide.with(view.getContext())
                .load(currentUser.getImgUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(thumbnail);

        //username_prof.setText(user.getName());
     username_prof.setText(currentUser.getUsername());
         username_prof.setTypeface(AgentOrange);
     score_eng.setText(currentUser.getScoreEng());
        score_eng.setTypeface(OrangeJuce);
     score_fr.setText(currentUser.getScoreFr());
        score_fr.setTypeface(OrangeJuce);
     score_Sp.setText(currentUser.getScoreSpan());
        score_Sp.setTypeface(OrangeJuce);
     score_Ger.setText(currentUser.getScoreGer());
        score_Ger.setTypeface(OrangeJuce);


        return view ;
    }


}
