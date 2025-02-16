package ph.edu.usc.mobiledev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AppActivity extends AppCompatActivity {
    Button check_out;
    ListView list;

    private final int[] img_ids = {
            R.drawable.bouquet1,
            R.drawable.bouquet2,
            R.drawable.bouquet3,
            R.drawable.bouquet4,
            R.drawable.bouquet5,
    };

    private final int[] prices = {25, 30, 15, 40, 45};

    private final String[] details = {
            "Bouquet with a colour theme of pink.",
            "Bouquet of roses with a colour theme of black and red.",
            "Pink roses with a light-coloured wrapper.",
            "Bouquet with a lighter colour theme.",
            "Red roses with a brownish wrapper."
    };

    private final String[] product_names = {
            "Bouquet 1",
            "Bouquet 2",
            "Bouquet 3",
            "Bouquet 4",
            "Bouquet 5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int total_count = 0;
        int[] qty = getIntent().getIntArrayExtra("qty");

        check_out = findViewById(R.id.checkout);
        list = findViewById(R.id.list);

        if (qty != null) {
            for (int count : qty) {
                total_count += count;
            }
        }

        check_out.setText(getString(R.string.check_out, total_count));
        int finalTotal_count = total_count;
        check_out.setOnClickListener(v -> {
            if (finalTotal_count > 0) {
                Intent check_out = new Intent(AppActivity.this, CheckoutActivity.class);
                check_out.putExtra("prices", prices);
                check_out.putExtra("product_names", product_names);
                check_out.putExtra("qty", getIntent().getIntArrayExtra("qty"));
                check_out.putExtra("total", finalTotal_count);
                startActivity(check_out);
            }
        });

        ListAdapter adapter = new ListAdapter(this, R.layout.list_layout, img_ids, prices, details, product_names);
        list.setAdapter(adapter);
    }
}