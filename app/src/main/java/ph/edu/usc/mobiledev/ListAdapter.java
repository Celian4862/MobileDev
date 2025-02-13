package ph.edu.usc.mobiledev;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] product_names;
    private final Integer[] prices;
    private final Integer[] img_ids;

    public ListAdapter(@NonNull Activity context, int resource, String[] product_names, Integer[] prices, Integer[] imgids) {
        super(context, resource, product_names);
        this.context = context;
        this.product_names = product_names;
        this.prices = prices;
        this.img_ids = imgids;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_layout, null, true);

        ImageView img = rowView.findViewById(R.id.img);
        TextView name  = rowView.findViewById(R.id.name);
        TextView price = rowView.findViewById(R.id.price);

        img.setImageResource(img_ids[position]);
        name.setText(product_names[position]);
        String price_format = "$ " + prices[position] + ".00";
        price.setText(price_format);

        return rowView;
    }
}
