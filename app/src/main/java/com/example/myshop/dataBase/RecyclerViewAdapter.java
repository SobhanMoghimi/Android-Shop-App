package com.example.myshop.dataBase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.model.Product;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    List<Product> productList;
    Context context;
    public RecyclerViewAdapter(List<Product> productList, Context context)
    {
        this.productList=productList;
        this.context=context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_row_prod_pic;
        TextView tv_row_product_name;
        TextView tv_row_product_seller_name;
        TextView tv_row_product_category;
        TextView tv_row_product_phoneNumber;
        public MyViewHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            iv_row_prod_pic=itemView.findViewById(R.id.imageView_row_product_pic);
            tv_row_product_category=itemView.findViewById(R.id.text_view_row_product_category);
            tv_row_product_name=itemView.findViewById(R.id.text_view_row_product_name);
            tv_row_product_phoneNumber=itemView.findViewById(R.id.text_view_row_product_phoneNumber);
            tv_row_product_seller_name = itemView.findViewById(R.id.text_view_row_product_seller);
        }
    }
}
