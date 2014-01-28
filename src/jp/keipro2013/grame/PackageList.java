package jp.keipro2013.grame;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class PackageList extends Activity {

    private ListView lv;
    private PackageAdapter myadapter;
    private ArrayList<PackageData> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        //ダミーデータ
        mylist = new ArrayList<PackageData>();
        for (int i = 0; i < 10; i++) {
            mylist.add(new PackageData("文字列"+i, ""+i));
        }
        myadapter = new PackageAdapter(this, R.layout.package_row, mylist);

        mylist.add(new PackageData("2014/1/14", "寺内茜"));
        //アダプターを登録
        lv = (ListView)this.findViewById(R.id.listView1);
        lv.setAdapter(myadapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.activity_package_list, menu);
        return true;
    }

}
