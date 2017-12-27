package com.example.dell.dathangan.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dathangan.Model.Cart;
import com.example.dell.dathangan.Model.Dish;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseBundle;
import com.example.dell.dathangan.Utils.BaseImagePreprocessing;
import com.example.dell.dathangan.Utils.MySQLite;

/**
 * A simple {@link Fragment} subclass.
 * TODO: Màn hình Chi tiết Món ăn
 */
public class DetailOrderFragment extends Fragment {

    TextView nameItemDetail;
    TextView priceItemDetail;
    TextView proPriceItemDetail;
    ImageView iconItemDeatil;
    TextView desItemDetail;
    Button btnAddItemToCartItemDetail;
    private MySQLite db;

    public DetailOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_order, container, false);

        nameItemDetail = (TextView)view.findViewById(R.id.name_item_detail);
        priceItemDetail = (TextView)view.findViewById(R.id.price_item_detail);
        proPriceItemDetail = (TextView)view.findViewById(R.id.proPrice_item_detail);
        iconItemDeatil = (ImageView)view.findViewById(R.id.img_item_detail);
        desItemDetail = (TextView)view.findViewById(R.id.des_item_detail);
        btnAddItemToCartItemDetail = (Button) view.findViewById(R.id.btn_adđ_item_to_cart_detail);

        db = new MySQLite(getContext());

        String nameItemOrder = "";
        String priceItemOrder = "";
        String proPriceItemOrder = "";
        String desItemOrder = "";
        String iconItemOrder = "";
        int id = 0;
        Bundle args = getArguments();
        if (args != null) {
            id = args.getInt(BaseBundle.ID_ITEM_ORDER);
            nameItemOrder = args.getString(BaseBundle.NAME_ITEM_ORDER);
            priceItemOrder= args.getString(BaseBundle.PRICE_ITEM_ORDER);
            proPriceItemOrder = args.getString(BaseBundle.PRO_PRICE_ITEM_ORDER);
            desItemOrder= args.getString(BaseBundle.DES_ITEM_ORDER);
            iconItemOrder = args.getString(BaseBundle.ICON_ITEM_ORDER);
        }

        final Dish dish = new Dish(id,nameItemOrder, desItemOrder, priceItemOrder, proPriceItemOrder, iconItemOrder);

        //String data = extras.getString(Intent.EXTRA_TEXT);
        nameItemDetail.setText(nameItemOrder);
        priceItemDetail.setText(priceItemOrder);
        proPriceItemDetail.setText(proPriceItemOrder);
        desItemDetail.setText(desItemOrder);
        // TODO: Lấy ID của Ảnh theo tên lưu trong mipmap
        int imageId = BaseImagePreprocessing.getMipmapResIdByName(getContext(), iconItemOrder);
        iconItemDeatil.setImageResource(imageId);

        final String finalIconItemOrder = iconItemOrder;
        final String finalNameItemOrder = nameItemOrder;
        final String finalProPriceItemOrder = proPriceItemOrder;
        btnAddItemToCartItemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View mView = layoutInflater.inflate(R.layout.dialog_add_item_to_cart, null);

                ImageView imgItem = (ImageView) mView.findViewById(R.id.img_add_item_to_cart);
                TextView proPriceItemAddToCart = (TextView) mView.findViewById(R.id.pro_price_add_item_to_cart);
                TextView nameItemAddToCart = (TextView) mView.findViewById(R.id.name_add_item_to_cart);
                Button btnIncrease = (Button) mView.findViewById(R.id.btn_add_number_item_to_cart);
                Button btnDecrease = (Button) mView.findViewById(R.id.btn_sub_number_item_to_cart);
                Button btnClose = (Button) mView.findViewById(R.id.btn_close_item_to_cart);

                int imageId = BaseImagePreprocessing.getMipmapResIdByName(getContext(), dish.SrcImg);
                imgItem.setImageResource(imageId);
                nameItemAddToCart.setText(dish.TenMonAn);
                proPriceItemAddToCart.setText(dish.GiaKhuyenMai);

                Button btnAddItemToCart = (Button) mView.findViewById(R.id.btn_add_item_to_cart);

                btnIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView txtNumber = (TextView) v.getRootView().findViewById(R.id.txt_number_item_to_cart);
                        String strNumber = (String) txtNumber.getText();
                        //String strNumber = (String) holder.txtOrderProductNumber.getText();
                        int a = Integer.parseInt(strNumber);
                        a++;
                        txtNumber.setText(""+a);
                    }
                });

                btnDecrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView txtNumber = (TextView) v.getRootView().findViewById(R.id.txt_number_item_to_cart);
                        String strNumber = (String) txtNumber.getText();
                        //String strNumber = (String) holder.txtOrderProductNumber.getText();
                        int a = Integer.parseInt(strNumber);
                        if(a > 0){
                            a--;
                        }
                        txtNumber.setText(""+a);
                    }
                });

                mBuilder.setView(mView);
                final AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                btnAddItemToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Bắt đầu connect Http
                        // Inserting row in users table
                        TextView txtNumber = (TextView) v.getRootView().findViewById(R.id.txt_number_item_to_cart);
                        int number = Integer.parseInt((String) txtNumber.getText());
                        Cart a = new Cart(dish.ID_MonAn,dish.TenMonAn,dish.MoTa,dish.GiaBan,dish.GiaKhuyenMai,dish.SrcImg,number);
                        Cart getOldItem = db.getDataFromId(dish.ID_MonAn);
                        if(getOldItem.getID_MonAn() != -1){
                            //Cart getOldItem = db.getDataFromId(dish.ID_MonAn);
                            int numberUpdate = getOldItem.getNumberItem() + number;
                            Cart itemUpdate = new Cart(dish.ID_MonAn,dish.TenMonAn,dish.MoTa,dish.GiaBan,dish.GiaKhuyenMai,dish.SrcImg, numberUpdate);
                            db.updateNote(itemUpdate);
                        } else {
                            db.addItemToCart(a);
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });

        return view;
    }

}
