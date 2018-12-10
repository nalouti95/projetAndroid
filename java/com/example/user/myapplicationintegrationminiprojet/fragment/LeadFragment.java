package com.example.user.myapplicationintegrationminiprojet.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.myapplicationintegrationminiprojet.Models.User;
import com.example.user.myapplicationintegrationminiprojet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.myapplicationintegrationminiprojet.Activities.LoginActivity.IPadress;
import static com.example.user.myapplicationintegrationminiprojet.Activities.LoginActivity.currentUser;

public class LeadFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public String url1 = IPadress+"/miniProjetWebService/Langue/leaderboard/LeaderboardFR.php";
    public String url2 = IPadress+"/miniProjetWebService/Langue/leaderboard/LeaderboardENG.php";
    public String url3 = IPadress+"/miniProjetWebService/Langue/leaderboard/LeaderboardESP.php";
    public String url4 = IPadress+"/miniProjetWebService/Langue/leaderboard/LeaderboardGER.php";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<User> itemsList;
    private UserAdapter mAdapter;
    private RecyclerView recyclerView;
    public static  Typeface OrangeJuce , AgentOrange;

    public LeadFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LeadFragment newInstance(String param1, String param2) {
        LeadFragment fragment = new LeadFragment();
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
        View view = inflater.inflate(R.layout.fragment_lead, container, false);


        AgentOrange = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AgentOrange.ttf");
        OrangeJuce = Typeface.createFromAsset(getActivity().getAssets(), "fonts/orange juice 2.0.ttf");

        recyclerView = view.findViewById(R.id.listEN);
        itemsList = new ArrayList<>();
        mAdapter = new UserAdapter(getActivity() , itemsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getListEnglish();
        fixeData();
        return view;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    //TODO: call 4 api differentes from our server to bring leaderboard in  each language
    //english
    public void getListEnglish() {

           final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new  JsonArrayRequest(url2 , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());


                try {


                    for (int i = 0 ; i<jsonArray.length() ; i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);
                        Log.e("lead" , jObj.getString("username"));
                        User user = new User(jObj.getString("username") , jObj.getString("image"),jObj.getString("scoreAng"));
                        itemsList.add(user);
                          }
        } catch (JSONException e) {
            e.printStackTrace();
        }

                mAdapter = new UserAdapter(getActivity(), itemsList);

                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);


    }
    public void fixeData(){

        itemsList.add(currentUser);
    }


    // TODO: custom spacing between elements in the recycle view

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;


        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    //TODO: contstruire 4 diffrent adapters to remplir our list (not optimizable)
    //TODO: remplir les 4 recycle view
    //TODO: part of the user adapters


}



class UserAdapter extends  RecyclerView.Adapter<UserAdapter.MyViewHolderUser> {
private Context context;
private List<User> leadersList;

public class MyViewHolderUser extends RecyclerView.ViewHolder {
    public ImageView imageUser;
    public TextView user_name;
    public TextView user_score;



    public MyViewHolderUser(View view) {
        super(view);
        imageUser = view.findViewById(R.id.user_img);
        user_name = view.findViewById(R.id.user_name);
        user_score = view.findViewById(R.id.user_score);

        //appel au cadnat
    }


}

    public UserAdapter(Context context, List<User> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    @Override
    public MyViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lead_item_row, parent, false);

        return new MyViewHolderUser(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderUser myViewHolderUser, final int position) {
        //our code
        final  User u = leadersList.get(position);

        myViewHolderUser.user_name.setText(u.getUsername());
        myViewHolderUser.user_score.setText(u.getScoreEng());
        Glide.with(context)
                .load(u.getImgUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(myViewHolderUser.imageUser);

    }

    @Override
    public int getItemCount() {
        return leadersList.size();
    }


}
