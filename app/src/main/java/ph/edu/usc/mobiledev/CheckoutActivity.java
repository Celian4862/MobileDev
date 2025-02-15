package ph.edu.usc.mobiledev;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheckoutActivity extends AppCompatActivity {
    Button check_out;
    ListView list;
    TextView txt_total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        check_out = findViewById(R.id.check_out);
        list = findViewById(R.id.list);
        txt_total_price = findViewById(R.id.total_price);
        int total_price = 0;
        int[] prices = getIntent().getIntArrayExtra("prices");
        if (prices == null) {
            prices = new int[5];
        }
        int[] qty = getIntent().getIntArrayExtra("qty");
        if (qty == null) {
            qty = new int[5];
        }

        ReviewOrderListAdapter adapter = new ReviewOrderListAdapter(this, R.layout.list_review_order, getIntent().getStringArrayExtra("product_names"), qty, prices);
        list.setAdapter(adapter);

        for (int i = 0; i < 5; i++) {
            total_price += prices[i] * qty[i];
        }
        txt_total_price.setText(getString(R.string.total_price, total_price));
    }
}