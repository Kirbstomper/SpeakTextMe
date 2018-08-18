package kirbstomper.speaktextme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageInterceptor extends BroadcastReceiver {

    /***
     * This class intercepts incoming messages and passes them to the SpeakerService to have the
     * contact read
     *@
     * */

    SpeakerService speakerService;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";


   public MessageInterceptor(){
   };
    @Override
    public void onReceive(Context context, Intent intent) {
        if (speakerService == null){
            speakerService = SpeakerService.getSpeakerService(context);
        }
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                speakerService.speakFromNumber(sender);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                // prevent any other broadcast receivers from receiving broadcast
                // abortBroadcast();
            }
        }
    }






}
