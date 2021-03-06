package com.example.toolsharing.Student;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toolsharing.PojoClasses.StatusMessage_Pojo;
import com.example.toolsharing.PojoClasses.ToolsList_Pojo;
import com.example.toolsharing.R;
import com.example.toolsharing.Utils.GetDataServiceInterface;
import com.example.toolsharing.Utils.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentBorrowedReview extends Fragment implements PenaltyDialog.PenaltyDialogListener{
    private View view;
    ToolsListBorrowedRecylerAdapter toolsListBorrowedRecylerAdapter;
    private ArrayList<ToolsList_Pojo> toolsList_pojos;
    int position;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.6F);
    float pen;

    int toolId, bsid, psid, order_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_borrowed, container, false);

        psid = Integer.parseInt(getArguments().getString("SID"));
        return view;
    }

    public void openDialog(){

        PenaltyDialog penaltyDialog = new PenaltyDialog();
        penaltyDialog.setTargetFragment(StudentBorrowedReview.this, 1);
        penaltyDialog.show(getFragmentManager(), null);

    }

    @Override
    public void penaltyAmt(String penalty) {
        if(penalty.isEmpty()){
            pen = Float.parseFloat("0");
        } else{
            pen = Float.parseFloat(penalty);
        }
        System.out.println("URL Penalty: " + String.valueOf(pen));
        System.out.println("URL OrderId: " + order_id);

        borrowToolReturn(String.valueOf(pen));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        borrowToolListRev();
    }

    public void borrowToolListRev()
    {
        GetDataServiceInterface service = RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceInterface.class);
        Call<StatusMessage_Pojo> call = service.getBorrowedToolsListRev(psid);

        System.out.println("URL Tools: " + call);

        call.enqueue(new Callback<StatusMessage_Pojo>() {
            @Override
            public void onResponse(Call<StatusMessage_Pojo> call, Response<StatusMessage_Pojo> response) {
                final StatusMessage_Pojo statusMessage_pojo = response.body();
                String status = statusMessage_pojo.getStatus();
                System.out.println("URL Student recycler Called!: " + status);
                TextView empty_view = getView().findViewById(R.id.borr_empty_view);
                RecyclerView recyclerView = view.findViewById(R.id.recycler_student_borrow_mytools);
                if(!status.equalsIgnoreCase("error")) {
                    toolsList_pojos = new ArrayList<>(statusMessage_pojo.getToolsList());
                    toolsListBorrowedRecylerAdapter = new ToolsListBorrowedRecylerAdapter(toolsList_pojos, getActivity().getApplicationContext());
                    @SuppressLint("WrongConstant") LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);

                    empty_view.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(linearLayout);
                    recyclerView.setAdapter(toolsListBorrowedRecylerAdapter);

                    toolsListBorrowedRecylerAdapter.setOnItemClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            view.startAnimation(buttonClick);
                            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                            int position = viewHolder.getAdapterPosition();

                            toolId = statusMessage_pojo.getToolsList().get(position).getToolId();
                            bsid = statusMessage_pojo.getToolsList().get(position).getBorrowStudentId();
                            order_id = statusMessage_pojo.getToolsList().get(position).getToolOrderId();
                            String return_date = statusMessage_pojo.getToolsList().get(position).getReturnDate();

                            System.out.println("URL toolId: " + toolId);
                            System.out.println("URL bsid: " + bsid);
                            System.out.println("URL return: " + return_date);

                            openDialog();

                        }
                    });

                } else{
                    recyclerView.setVisibility(View.INVISIBLE);
                    empty_view.setText("No Tools for Review");
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<StatusMessage_Pojo> call, Throwable t) {

                System.out.println("URL Failure Called! :" + t.getMessage());

            }
        });
    }

    private void borrowToolReturn(String penal) {

        GetDataServiceInterface service = RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceInterface.class);

        Call<StatusMessage_Pojo> call = service.returnBorrowTool(order_id, penal);


        call.enqueue(new Callback<StatusMessage_Pojo>() {
            @Override
            public void onResponse(Call<StatusMessage_Pojo> call, Response<StatusMessage_Pojo> response) {

                StatusMessage_Pojo statusMessage_pojo = response.body();
                String status = statusMessage_pojo.getStatus();
                System.out.println("URL borrowToolReturn Called!: " + status);

                if(status.equalsIgnoreCase("error")){
                    Toast.makeText(getActivity().getApplicationContext(),statusMessage_pojo.getMessage(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), statusMessage_pojo.getMessage(), Toast.LENGTH_LONG).show();
                }
                borrowToolListRev();

            }

            @Override
            public void onFailure(Call<StatusMessage_Pojo> call, Throwable t) {

                System.out.println("URL Failure Called! :" + t.getMessage());

            }
        });
    }
}