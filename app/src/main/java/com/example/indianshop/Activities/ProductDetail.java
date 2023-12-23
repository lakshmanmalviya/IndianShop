package com.example.indianshop.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indianshop.Adapters.ProductAdapter;
import com.example.indianshop.Modals.CategoryModal;
import com.example.indianshop.Modals.ProductModal;
import com.example.indianshop.Modals.Users;
import com.example.indianshop.R;
import com.example.indianshop.databinding.ActivityMainBinding;
import com.example.indianshop.databinding.ActivityProductDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDetail extends AppCompatActivity implements PaymentResultListener {
    ActivityProductDetailBinding bnd;
    ArrayList<ProductModal> plist;
    ProductModal pmodal;
    ProductAdapter productAdapter;
    FirebaseDatabase myDatabase;
    DatabaseReference myref;
    final String categories = "categories";
    final String AllProducts = "AllProducts";
      int price;
      int qnt;
      int dbPrice;
      int dbQnt;
      String productName = "productName";
      String productPrice = "productPrice";
      String productColor = "productColor";
      String productCategory = "productCategory";
      String productId = "productId";
      String productBrand = "productBrand";
      String productQnt = "productQnt";
      String productDescription = "productDescription";
      String longDescription = "longDescription";
      String productDiscount = "productDiscount";
      String productImage = "productImage";
      String wishList = "wishList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        Checkout.preload(getApplicationContext());
        plist = new ArrayList<>();
        myDatabase = FirebaseDatabase.getInstance();
        productAdapter =  new ProductAdapter(plist,getApplicationContext());
//        loadProduct();
        bnd.PDproductName.setText(getIntent().getStringExtra(productName));
        bnd.PDproductName.setText(getIntent().getStringExtra(productColor));
        bnd.PDproductName.setText(getIntent().getStringExtra(productBrand));
        bnd.PDproductDes.setText(getIntent().getStringExtra(productDescription));
        bnd.productLongDes.setText(getIntent().getStringExtra(longDescription));
        bnd.PDdiscount.setText(getIntent().getStringExtra(productDiscount) + "%");
        Picasso.get().load(getIntent().getStringExtra(productImage)).placeholder(R.drawable.placeholder).into(bnd.PDproductImage);
        bnd.PDprice.setText(getIntent().getStringExtra(productPrice));
        bnd.PDqnt.setText(getIntent().getStringExtra(productQnt));
        dbPrice = Integer.parseInt(getIntent().getStringExtra(productPrice));
        dbQnt = Integer.parseInt(getIntent().getStringExtra(productQnt));
        price = dbPrice;
        qnt = 1;
        bnd.PDqnt.setText("" + qnt);
        bnd.PDplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qnt <= dbQnt) {
                    qnt++;
                    price = price + dbPrice;
                    bnd.PDprice.setText("" + price);
                    bnd.PDqnt.setText("" + qnt);
                    return;
                }
                Toast.makeText(getApplicationContext(), "No more Quantity", Toast.LENGTH_SHORT).show();

            }
        });
        bnd.PDminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qnt == 1) {
                    bnd.PDprice.setText("" + dbPrice);
                    price = dbPrice;
                    Toast.makeText(getApplicationContext(), "Now We can't decrese it", Toast.LENGTH_SHORT).show();
                    return;
                }
                qnt--;
                price = price - dbPrice;
                bnd.PDprice.setText("" + price);
                bnd.PDqnt.setText("" + qnt);
            }
        });

        bnd.PDlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Action will be taken", Toast.LENGTH_SHORT).show();
            }
        });
        bnd.PDshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String fullText = "Name :"+getIntent().getStringExtra(productName)+"\n"+"Brand : "+
                        getIntent().getStringExtra(productBrand)+"\n"+"Price : "+ getIntent().getStringExtra(productPrice)+"\n"+
                        "Discount : "+ getIntent().getStringExtra(productDiscount)+"\n"+"Description"+ getIntent().getStringExtra(productDescription)+
                        "\n"+"Full description :"+ getIntent().getStringExtra(longDescription)+"\n"+"Image link : "+ getIntent().getStringExtra(productImage);
                intent.putExtra(Intent.EXTRA_TEXT,fullText);
                intent.setType("text/*");
                startActivity(intent);
            }
        });
        bnd.placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ordered Successfully", Toast.LENGTH_SHORT).show();
            }
        });


        }


//    private void startPayment(String amount) {
//        final Activity activity = this;
//         double finalAmount = Float.parseFloat(amount)*100;
//         Checkout checkout = new Checkout();
//         checkout.setKeyID("rzp_test_Ul5FwvQBGJ7D2J");
//         try {
//             JSONObject options = new JSONObject();
//             options.put("name", "Merchant Name");
//             options.put("description", "Reference No. #123456");
//             options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//             options.put("order_id", new Date().getTime());//from response of step 3.
//             options.put("theme.color", "#3399cc");
//             options.put("currency", "INR");
//             options.put("amount", finalAmount);//pass amount in currency subunits
//             options.put("prefill.email", "lslaskhman2024@gmail.com");
//             options.put("prefill.contact","9302362022");
//             JSONObject retryObj = new JSONObject();
//             retryObj.put("enabled", true);
//             retryObj.put("max_count", 4);
//             options.put("retry", retryObj);
//             checkout.open(activity, options);
//
//         }catch (Exception e){
//             Toast.makeText(getApplicationContext(), "Error "+e, Toast.LENGTH_SHORT).show();
//         }
//    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(), "Payment success"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), "Payment Failed"+s, Toast.LENGTH_SHORT).show();
    }
   public void loadProduct(){
       myDatabase.getReference().child(AllProducts)
               .addValueEventListener(new ValueEventListener(){
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists()){
                           plist.clear();
                           for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                               pmodal = dataSnapshot.getValue(ProductModal.class);
                               plist.add(pmodal);
                           }
                           productAdapter.notifyDataSetChanged();
                       }
                       else{
                           Toast.makeText(getApplicationContext(), "Loading Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                   }
               });
   }
}