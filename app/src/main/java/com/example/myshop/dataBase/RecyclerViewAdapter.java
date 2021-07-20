package com.example.myshop.dataBase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.activities.CustomerProductFullView;
import com.example.myshop.activities.SellerHomePageActivity;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;

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

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapter.MyViewHolder holder, int position)
    {
        holder.tv_row_product_name.setText(productList.get(position).getName());
        holder.tv_row_product_category.setText(productList.get(position).getCategoryString());
        holder.tv_row_product_phoneNumber.setText(productList.get(position).getSeller().getPhoneNumber());
        holder.tv_row_product_price.setText(productList.get(position).getPrice()+ "\t" + "تومان");
        holder.iv_row_prod_pic.setImageBitmap(productList.get(position).getImage());

        if(Seller.getActiveSeller()==null)
        {

            holder.row_product_layout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent=new Intent(context, CustomerProductFullView.class);
                    Product.setWorkingProduct(productList.get(position));
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount()
    {
        return productList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_row_prod_pic;
        TextView tv_row_product_name;
        TextView tv_row_product_category;
        TextView tv_row_product_phoneNumber;
        TextView tv_row_product_price;
        ConstraintLayout row_product_layout;
        public MyViewHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            iv_row_prod_pic=itemView.findViewById(R.id.imageView_row_product_pic);
            tv_row_product_category=itemView.findViewById(R.id.text_view_row_product_category);
            tv_row_product_name=itemView.findViewById(R.id.text_view_row_product_name);
            tv_row_product_phoneNumber=itemView.findViewById(R.id.text_view_row_product_phoneNumber);
            tv_row_product_price=itemView.findViewById(R.id.text_view_row_product_price);
            row_product_layout=itemView.findViewById(R.id.one_row_product);
        }
    }
}
