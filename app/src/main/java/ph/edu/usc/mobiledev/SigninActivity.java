package ph.edu.usc.mobiledev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SigninActivity extends AppCompatActivity {
    Button cancel, submit;
    EditText username, password;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cancel = findViewById(R.id.cancel);
        submit = findViewById(R.id.submit);
        error = findViewById(R.id.error);

        cancel.setOnClickListener(v -> {
            Intent back = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(back);
        });

        submit.setOnClickListener(v -> {
            String sname = username.getText().toString(),
                    spass = password.getText().toString();
            if (sname.equals("johndoe") && spass.equals("johndoe")) {
                error.setText("");
                Intent app = new Intent(SigninActivity.this, AppActivity.class);
                startActivity(app);
            } else {
                error.setText(getString(R.string.incorrect_credentials));
            }
        });
    }
}