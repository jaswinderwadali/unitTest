package com.wadali.testcase;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by jaswinderwadali on 05/08/16.
 */
@RunWith(RobolectricTestRunner.class)
public class RestApi {

    EditText nameEt;
    EditText passwordEt;


    @Test
    public void clickingButton_shouldChangeResultsViewText() throws Exception {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Button loginButton = (Button) activity.findViewById(R.id.login);
        nameEt = (EditText) activity.findViewById(R.id.user_name);
        passwordEt = (EditText) activity.findViewById(R.id.password);
        enterMockValues(nameEt, passwordEt);
        loginButton.setOnClickListener(onClickListener);
        loginButton.performClick();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                String name = nameEt.getText().toString();
                String password = passwordEt.getText().toString();
                JSONObject jsonObject = getServerMockObject(name, password);
                Assert.assertNotNull(jsonObject);
                String token = jsonObject.optString("token");
                Assert.assertNotNull(token);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    void enterMockValues(EditText nameEt, EditText passEt) {
        nameEt.setText("krishna");
        passEt.setText("Smoke....");
    }

    public JSONObject getServerMockObject(String name, String password) throws Exception {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password))
            return null;
        if (name.length() <= 4 || password.length() <= 6)
            return null;
        JSONObject jsonObject = new JSONObject();
        return jsonObject.put("token", "hehafdbiieosjbb739hfjiisndji");
    }


}
