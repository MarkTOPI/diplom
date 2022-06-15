package com.example.healthtracking.bottomBtnNavigation.profilePage;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ProfileFragment extends Fragment {
    private ImageView imageView;
    private Dialog dialog;
    private SharedPreferences.Editor editorPhotoUser;
    private SharedPreferences sharedPreferencesToken, sharedPreferencesUserName, sharedPreferencesPhotoUser;
    static final int GALLERY_REQUEST = 1;
    private String token, username, userPhoto, TAG = "Привет!";
    private Button btnChangeUserPhoto;
    TextView textUserName;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesToken = getContext().getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferencesToken.getString("token", "");
        sharedPreferencesUserName = getContext().getSharedPreferences("username", MODE_PRIVATE);
        username = sharedPreferencesUserName.getString("username", "");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = view.findViewById(R.id.imageViewUserProfile);
        btnChangeUserPhoto = view.findViewById(R.id.btnChangeUserPhoto);
        textUserName = view.findViewById(R.id.textUserName);
        textUserName.setText(username);

        btnChangeUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });


        view.findViewById(R.id.btnExit).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Вы успешно вышли из аккаунта", Toast.LENGTH_SHORT).show();
            sharedPreferencesToken = this.getActivity().getSharedPreferences("token", MODE_PRIVATE);
            sharedPreferencesToken.edit().remove("token").commit();
            startActivity(new Intent(getContext(), first_page.class));
        });
        return view;
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null ) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Загрузка фото...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(getActivity().findViewById(android.R.id.content), "Фото загружено", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Ошибка загрузки фото пользователя", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Прогресс: " + (int) progressPercent + "%");
                    }
                });

    }
}