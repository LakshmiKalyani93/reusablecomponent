package com.mtuity.contacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by kalyani on 10/2/16.
 */
public class ImageLoader {


    private static final String[] PHOTO_ID_PROJECTION = new String[]{
            ContactsContract.Contacts.PHOTO_ID
    };

    private static final String[] PHOTO_BITMAP_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Photo.PHOTO
    };

    private final String phoneNumber;

    private final ContentResolver contentResolver;

    public ImageLoader(final Context context, final String phoneNumber) {

        this.phoneNumber = phoneNumber;
        contentResolver = context.getContentResolver();

    }

    /**
     *
     * @return returns the thumbnail of the contact image
     */
    protected Bitmap addThumbnail() {

        final Integer thumbnailId = fetchThumbnailId();
        if (thumbnailId != null) {
            final Bitmap thumbnail = fetchThumbnail(thumbnailId);
            if (thumbnail != null) {
                return thumbnail;
            }
        }

        return null;
    }

    /**
     *
     * @return thumbnailId
     */
    private Integer fetchThumbnailId() {

        final Uri uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        final Cursor cursor = contentResolver.query(uri, PHOTO_ID_PROJECTION, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        try {
            Integer thumbnailId = null;
            if (cursor.moveToFirst()) {
                thumbnailId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
            }
            return thumbnailId;
        } finally {
            cursor.close();
        }

    }

    /**
     *
     * @param thumbnailId
     * @return it returns the queried thumb nail of the contact image if exists
     */
    final Bitmap fetchThumbnail(final int thumbnailId) {

        final Uri uri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, thumbnailId);
        final Cursor cursor = contentResolver.query(uri, PHOTO_BITMAP_PROJECTION, null, null, null);

        try {
            Bitmap thumbnail = null;
            if (cursor.moveToFirst()) {
                final byte[] thumbnailBytes = cursor.getBlob(0);
                if (thumbnailBytes != null) {
                    thumbnail = BitmapFactory.decodeByteArray(thumbnailBytes, 0, thumbnailBytes.length);
                }
            }
            return thumbnail;
        } finally {
            cursor.close();
        }

    }

}
