package jp.keipro2013.grame;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PackageAdapter extends ArrayAdapter<PackageData>
{
    private Context context;
    private LayoutInflater inflater;
    private int layout;

    private ImageButton firstButton;
    private ImageButton continueButton;

    public PackageAdapter(Context context,int textViewResourceId, ArrayList<PackageData> objects){
        super(context, 0, textViewResourceId, objects);

        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(convertView == null){
            view = this.inflater.inflate(this.layout, null);
        }

        final PackageData pd = this.getItem(position);
        ((TextView)view.findViewById(R.id.date_textview)).setText(pd.getDate());
        ((TextView)view.findViewById(R.id.name_textview)).setText(pd.getName());

        firstButton = (ImageButton)view.findViewById(R.id.first_button);
        firstButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "最初から："+ pd.getName(), Toast.LENGTH_LONG).show();

            }
        });
        continueButton = (ImageButton) view.findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "途中から："+ pd.getDate(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
