package kirbstomper.speaktextme;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;

public class ContactFinder {
    /**
     * This class handles looking through the users contacts and matching the number passed to the
     * owner
     */

    static ContactFinder contactFinder;
    static Context context;
    public  ContactFinder(Context context){
        this.context = context;
    }

    public static ContactFinder getContactFinder(Context context){
        if(contactFinder == null){
            contactFinder = new ContactFinder(context);
        }
        return contactFinder;
    }

    public static String getContactName(String phoneNumber){
        String name = "Unknown";
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        ContentResolver contentResolver = context.getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);
        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                //String contactId = contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
            }
        } finally {
            if (contactLookup != null) {
                contactLookup.close();
            }
        }
        return name;
    }

}
