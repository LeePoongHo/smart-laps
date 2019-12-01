package com.example.leeph.smartlaps;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.leeph.smartlaps.Service.ServerService;
import com.example.leeph.smartlaps.Service.ServerServiceClass;
import com.example.leeph.smartlaps.Service.RegisterBean;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private String userID;
    private String userPassword;
    private String userGender;
    private String userMajor;
    private String userEmail;
    private String userName;
    private AlertDialog dialog;
    private boolean validate = false;
    private EditText txtId, txtPasswd, txtEmail,txtName;
    private Button btnValidate, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.majorSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        txtId = findViewById(R.id.txtId);
        txtPasswd = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.emailText);
        txtName = findViewById(R.id.txtName);

        btnValidate = findViewById(R.id.btnValidate);

        btnRegister = findViewById(R.id.registerButton);


        RadioGroup genderGroup = findViewById(R.id.genderGroup);
        int genderGroupID = genderGroup.getCheckedRadioButtonId();
        userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton genderButton = findViewById(i);
                userGender = genderButton.getText().toString();
            }
        });

        txtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionValidateId();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionRegister();
            }
        });
    }

    private void actionRegister() {
        userPassword = txtPasswd.getText().toString();
        userMajor = spinner.getSelectedItem().toString();
        userName = txtName.getText().toString();
        userEmail = txtEmail.getText().toString();

        if (!validate) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("먼저 중복 체크를 해주세요.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
            return;
        }

        if (userID.equals("") || userPassword.equals("") || userMajor.equals("") || userEmail.equals("") || userGender.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("빈칸없이 입력해주세요.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
            return;
        }

        RegisterBean registerBean = new RegisterBean();
        registerBean.setMem_id(userID);
        registerBean.setMem_passwd(userPassword);
        registerBean.setMem_email(userEmail);
        registerBean.setMem_name(userName);
        registerBean.setMem_sex(userGender);
        registerBean.setMem_major(userMajor);

        ServerService serverService = ServerServiceClass.retrofit.create(ServerService.class);
        Call<String> call = serverService.postRegister(registerBean.getMem_id(),registerBean.getMem_passwd(),registerBean.getMem_email(),
                registerBean.getMem_sex(),registerBean.getMem_name());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == HTTP_OK) {
                    String result = response.body();
                    Log.i("리턴 값", response.message() + "/" + result);
                    if (result.equals("true")) {
                        Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "서버에러", Toast.LENGTH_SHORT).show();
                        validate = true;
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actionValidateId() {
        userID = txtId.getText().toString();
        if (validate) {
            return;
        }

        if (userID.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("아이디를 입력해주세요.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
            return;
        }

        ServerService serverService = ServerServiceClass.retrofit.create(ServerService.class);
        Call<ResponseBody> call = serverService.postIdCheck(userID);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("리턴 값", response.code() + " " + response.message());
                if (response.code() == HTTP_OK) {
                    try {
                        String result = response.body().string();
                        Log.i("리턴 값", response.message() + "/" + result);
                        if (result.equals("true")) {
                            Toast.makeText(RegisterActivity.this, "중복된 아이디가 있습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "사용해도 좋습니다", Toast.LENGTH_SHORT).show();
                            validate = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "중복체크 실패", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
