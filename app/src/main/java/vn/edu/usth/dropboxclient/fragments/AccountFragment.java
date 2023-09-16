package vn.edu.usth.dropboxclient.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.material.switchmaterial.SwitchMaterial;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton;

import vn.edu.usth.dropboxclient.R;

/*
not finished yet
at all
 */

public class AccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        ImageView avatarImageView = rootView.findViewById(R.id.avatarImageView);
        TextView usernameTextView = rootView.findViewById(R.id.usernameTextView);
        TextView emailTextView = rootView.findViewById(R.id.emailTextView);
        TextView spaceUsedTextView = rootView.findViewById(R.id.spaceUsedTextView);
        TextView manageDevicesView = rootView.findViewById(R.id.manageDevicesTextView);
        SwitchMaterial cameraUploadsSwitch = rootView.findViewById(R.id.cameraUploadsSwitch);
        SwitchMaterial offlineModeSwitch = rootView.findViewById(R.id.offlineModeSwitch);
        Button signOutButton = rootView.findViewById(R.id.signOutButton);

        manageDevicesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open fragment
            }
        });

        cameraUploadsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle camera uploads switch state change
                if (isChecked) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(cameraIntent);
                } else {
                    stopCameraOperation();
                }
            }
            private void stopCameraOperation() {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });


        offlineModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Offline mode enabled
                } else {
                    // Offline mode disabled
                }
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // blah blah blah
            }
        });

        return rootView;
    }
}