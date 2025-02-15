package ph.edu.usc.mobiledev;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

public class ReviewOrderListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final int resource;
    private final int[] prices, qty;
    private final String[] product_names;

    public ReviewOrderListAdapter(@NonNull Activity context, int resource, String[] product_names, int[] qty, int[] prices) {
        super(context, resource, product_names);
        this.product_names = product_names;
        this.qty = qty;
        this.prices = prices;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(resource, null, true);

        TextView product_name = rowView.findViewById(R.id.product_name),
                price = rowView.findViewById(R.id.price),
                txt_qty = rowView.findViewById(R.id.qty);

        product_name.setText(product_names[position]);
        price.setText(context.getString(R.string.price, prices[position] * qty[position]));

        Locale l = new Locale("en");
        txt_qty.setText(String.format(l, "%d", qty[position]));

        return rowView;
    }
}
