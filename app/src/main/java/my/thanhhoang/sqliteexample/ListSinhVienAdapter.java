package my.thanhhoang.sqliteexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListSinhVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SinhVien> listSV;

    public ListSinhVienAdapter(Context context, int layout, List<SinhVien> listSV) {
        this.context = context;
        this.layout = layout;
        this.listSV = listSV;
    }

    @Override
    public int getCount() {
        return listSV.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTenSV;
        TextView txtLop;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtTenSV = convertView.findViewById(R.id.textViewSinhVien);
            holder.txtLop = convertView.findViewById(R.id.textViewLop);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        SinhVien sinhVien = listSV.get(position);
        holder.txtTenSV.setText(sinhVien.getTenSinhVien());
        holder.txtLop.setText(sinhVien.getLop());

        return convertView;
    }
}
