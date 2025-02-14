package ph.edu.usc.mobiledev;

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

    private final Integer[] prices = {25, 30, 15, 40, 45};

    private final Integer[] imgids = {
            R.drawable.bouquet1,
            R.drawable.bouquet2,
            R.drawable.bouquet3,
            R.drawable.bouquet4,
            R.drawable.bouquet5,
    };

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

        check_out = findViewById(R.id.checkout);
        list = findViewById(R.id.list);

        check_out.setText(getString(R.string.check_out, getIntent().getIntExtra("checkout", 0)));

        ListAdapter adapter = new ListAdapter(this, R.layout.list_layout, imgids, prices, details, product_names);
        list.setAdapter(adapter);
    }
}