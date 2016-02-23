package com.mtuity.contacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtuity.contacts.model.ContactBean;
import com.mtuity.contacts.model.EmailBean;
import com.mtuity.contacts.model.PhoneNumberBean;
import com.mtuity.contacts.model.PostalAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalyani on 12/2/16.
 */
public class ContactAdapter extends BaseAdapter {
    private final Context mContext;
    private final int layoutId;
    private final List<ContactBean> mList;
    private final LayoutInflater inflater;

    public ContactAdapter(Context context, int resource, List<ContactBean> list) {
        mContext = context;
        layoutId = resource;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ContactBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;
        ContentHolder holder;
        if (mView == null) {
            holder = new ContentHolder();
            mView = inflater.inflate(layoutId, null);
            holder.contactName = (TextView) mView.findViewById(R.id.contact_name);
            holder.contactNumber = (TextView) mView.findViewById(R.id.phone);
            holder.photo = (ImageView) mView.findViewById(R.id.contact_image);
            holder.email = (TextView) mView.findViewById(R.id.email);
            holder.address = (TextView) mView.findViewById(R.id.address);
            mView.setTag(holder);

        } else {
            holder = (ContentHolder) mView.getTag();
        }


        String str = mContext.getString(R.string.not_specified);
        holder.contactName.setText(mList.get(position).getContactName() == null ? str : mList.get(position).getContactName());

        List<PhoneNumberBean> phoneList = mList.get(position).getPhoneList() == null ? new ArrayList<PhoneNumberBean>() : mList.get(position).getPhoneList();
        StringBuilder phoneString = new StringBuilder();
        for (PhoneNumberBean bean : phoneList) {
            if (bean.getNumber() != null) {
                phoneString.append(bean.getNumber());
                phoneString.append("\n");
            }
        }
        holder.contactNumber.setText(phoneString);

      //  holder.contactName.setText(mList.get(position).getName());
        List<EmailBean> emailList = mList.get(position).getEmailList() == null ? new ArrayList<EmailBean>() : mList.get(position).getEmailList();
        StringBuilder emailString = new StringBuilder();
        for (EmailBean bean : emailList) {
            if (bean.getEmailId() != null) {
                emailString.append(bean.getEmailId());
                emailString.append("\n");
            }
        }
        holder.email.setText(emailString);


        List<PostalAddressBean> addressList = mList.get(position).getPostalAddressList() == null ? new ArrayList<PostalAddressBean>() : mList.get(position).getPostalAddressList();
        StringBuilder addressString = new StringBuilder();
        for (PostalAddressBean bean : addressList) {
            if (bean.getFormattedAddress() != null) {
                addressString.append(bean.getFormattedAddress());
                addressString.append("\n");
            }
        }
        holder.address.setText(addressString);


        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                R.mipmap.ic_launcher);
        holder.photo.setImageBitmap(mList.get(position).getContactPicture() == null ? icon : mList.get(position).getContactPicture());
        return mView;
    }

    public static class ContentHolder {
        public TextView contactName;
        public TextView contactNumber;
        public ImageView photo;
        public TextView email;
        public TextView address;

        private ContentHolder() {
            //add constructor..
        }

    }
}
