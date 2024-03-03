/* Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Bai 3: Bai tap xoa san pham
 * */
package com.example.tuan5_21110940_bai3;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListViewAdapter extends BaseAdapter {
    final ArrayList<Product> listProduct;

    public ProductListViewAdapter(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        // Tra ve doi tuong tai vi tri position
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Tra ve id cua doi tuong tai vi tri position
        return listProduct.get(position).getProductID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.product_view, null);
        } else {
            viewProduct = convertView;
        }
        Product product = (Product) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.idproduct)).setText(String.format("ID = %d", product.productID));
        ((TextView) viewProduct.findViewById(R.id.idproduct)).setText(String.format("ID = %d", product.productID));
        ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText(String.format("Ten San Pham = %s", product.name));
        ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText(String.format("Gia = %d", product.price));
        return viewProduct;
    }
}

