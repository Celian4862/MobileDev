package ph.edu.usc.mobiledev;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] img_ids, prices;
    private final String[] product_names, details;

    public ListAdapter(@NonNull Activity context, int resource, Integer[] imgids, Integer[] prices, String[] details, String[] product_names) {
        super(context, resource, product_names);
        this.context = context;
        this.img_ids = imgids;
        this.details = details;
        this.prices = prices;
        this.product_names = product_names;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_layout, null, true);

        Button view_details = rowView.findViewById(R.id.btn);
        ImageView img = rowView.findViewById(R.id.img);
        TextView name  = rowView.findViewById(R.id.name);
        TextView price = rowView.findViewById(R.id.price);

        view_details.setOnClickListener(v -> {
            Intent dtl;
            dtl = new Intent(context, DetailsActivity.class);
            dtl.putExtra("img", img_ids[position]);
            dtl.putExtra("details", details[position]);
            dtl.putExtra("position", position);
            dtl.putExtra("price", prices[position]);
            dtl.putExtra("product_name", product_names[position]);
            context.startActivity(dtl);
        });
        img.setImageResource(img_ids[position]);
        name.setText(product_names[position]);
        String price_format = "$ " + prices[position] + ".00";
        price.setText(price_format);

        return rowView;
    }
}
