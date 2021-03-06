package com.example.toolsharing.Student;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.toolsharing.R;


public class RatingDialog extends DialogFragment {

    RatingBar ratingBar;
    EditText comment;
    private RatingDialogListener listener;
    String rat, coment;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater= getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.rating_dialog, null);

        builder.setView(view)
                .setTitle("Rate Tool")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rat = String.valueOf(ratingBar.getRating());
                        if(String.valueOf(comment.getText()).isEmpty()){
                            coment = "No Comments";
                        } else {
                            coment = String.valueOf(comment.getText());
                        }
                        //System.out.println("URL Rating: " + rat);

                        listener.ratingVal(rat, coment);
                    }
                });

        ratingBar = view.findViewById(R.id.rating_dialog);
        comment = view.findViewById(R.id.comment);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RatingDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement RatingDialogListener");
        }
    }

    public interface RatingDialogListener{
        void ratingVal(String rating, String comments);
    }
}
