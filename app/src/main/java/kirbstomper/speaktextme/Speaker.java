package kirbstomper.speaktextme;

import android.content.Context;
import android.speech.tts.TextToSpeech;

public class Speaker extends TextToSpeech{
    /**
     * This is the class that handles most of the work for translating text to speech
     *
     */

    final CharSequence intro = "You have received a new message from";
    public Speaker(Context context){
       super(context,new TextToSpeech.OnInitListener() {
           @Override
           public void onInit(int status) {

           }
       });
    }

    /**
     * This method simply speaks whatever is passed to it
     * @param message
     */
    public void speak(CharSequence name){
        super.speak(intro +" " + name,TextToSpeech.QUEUE_FLUSH,null,"textToSpeak" );
    }


}
