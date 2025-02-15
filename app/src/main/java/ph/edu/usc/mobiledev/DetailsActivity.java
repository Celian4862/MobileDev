package ph.edu.usc.mobiledev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {
    Button confirm, minus, plus;
    ImageView img;
    int count, position;
    int[] counts;
    TextView details, price, product_name, qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        confirm = findViewById(R.id.confirm);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        img = findViewById(R.id.img);
        position = getIntent().getIntExtra("position", 4);
        counts = getIntent().getIntArrayExtra("qty");
        if (counts == null) {
            counts = new int[5];
        }
        details = findViewById(R.id.details);
        price = findViewById(R.id.price);
        product_name = findViewById(R.id.product_name);
        qty = findViewById(R.id.qty);

        count = counts[position];

        confirm.setOnClickListener(v -> {
            Intent confirm = new Intent(DetailsActivity.this, AppActivity.class);
            counts[position] = count;
            confirm.putExtra("qty", counts);
            startActivity(confirm);
        });
        minus.setOnClickListener(v -> {
            if (count > 0) {
                count--;
                Locale l = new Locale("en", "PH");
                qty.setText(String.format(l, "%d", count));
            }
        });
        plus.setOnClickListener(v -> {
            count++;
            Locale l = new Locale("en", "PH");
            qty.setText(String.format(l, "%d", count));
        });
        img.setImageResource(getIntent().getIntExtra("img", 0));
        details.setText(getIntent().getStringExtra("details"));
        price.setText(getString(R.string.price, getIntent().getIntExtra("price", 0)));
        product_name.setText(getIntent().getStringExtra("product_name"));
        Locale l = new Locale("en");
        qty.setText(String.format(l, "%d", count));
    }
}