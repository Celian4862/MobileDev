package ph.edu.usc.mobiledev;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    Button cancel, submit;
    EditText email, username, password;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        error = findViewById(R.id.error);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cancel = findViewById(R.id.cancel);
        submit = findViewById(R.id.submit);

        cancel.setOnClickListener(v -> {
            Intent back = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(back);
        });

        submit.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                if (email.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty()) {
                    error.setText(getString(R.string.invalid_field));
                } else {
                    Intent signup = new Intent(SignupActivity.this, AppActivity.class);
                    startActivity(signup);
                }
            }
        });
    }
}