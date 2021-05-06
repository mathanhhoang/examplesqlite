package my.thanhhoang.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvSinhVien;
    ListSinhVienAdapter adapter;
    List<SinhVien> listSinhVien = new ArrayList<>();
    DatabaseHelper dbHelper;

    Button btnThem, btnSua, btnXoa;
    EditText txtTenSinhVien, txtLop;

    int maSVChon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Anh xa
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        txtTenSinhVien = findViewById(R.id.editTextTenSinhVien);
        txtLop = findViewById(R.id.editTextLop);
        lvSinhVien = findViewById(R.id.listViewSinhVien);

        dbHelper = new DatabaseHelper(this);

        //read data
        List<SinhVien> list = dbHelper.layDanhSachSinhVien();
        listSinhVien.addAll(list);

        //load data
        adapter = new ListSinhVienAdapter(this, R.layout.list_item_sinhvien, listSinhVien);
        lvSinhVien.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSV = txtTenSinhVien.getText().toString();
                String lop = txtLop.getText().toString();
                dbHelper.themSV(tenSV, lop);

                capNhatListView();
                clearViews();

                Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sinhVien = listSinhVien.get(position);
                txtTenSinhVien.setText(sinhVien.getTenSinhVien());
                txtLop.setText(sinhVien.getLop());
                maSVChon = sinhVien.getMaSinhVien();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maSVChon == -1) return;

                String tenSV = txtTenSinhVien.getText().toString();
                String lop = txtLop.getText().toString();

                dbHelper.suaSV(maSVChon, tenSV, lop);

                capNhatListView();
                clearViews();

                Toast.makeText(MainActivity.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maSVChon == -1) return;

                dbHelper.xoaSV(maSVChon);

                capNhatListView();
                clearViews();

                Toast.makeText(MainActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void clearViews() {
        txtTenSinhVien.setText("");
        txtLop.setText("");
        maSVChon = -1;
    }

    private void capNhatListView(){
        listSinhVien.clear();
        listSinhVien.addAll(dbHelper.layDanhSachSinhVien());
        adapter.notifyDataSetChanged();
    }
}