package kirbstomper.speaktextme;

import android.content.ContentResolver;
import android.content.Context;

public  class SpeakerService {

    /***
     * Contains the speaker object used by the application and the contact matcher
     */
    Speaker speaker;

    static SpeakerService speakerService;
    static ContactFinder contactFinder;

    public  SpeakerService(Context context){
        speaker = new Speaker(context);
        contactFinder = ContactFinder.getContactFinder(context);
    }

    public static SpeakerService getSpeakerService(Context context){
        if(speakerService == null){
            speakerService = new SpeakerService(context);
        }
        return speakerService;
    }

    /***
     * Passes what needs to be spoken to the speaker
     * @param name
     * @return
     */
    public boolean speak(CharSequence name){
        speaker.speak(name);
        return true;
    }

    /**
     * Searches through contacts to retrieve a contact name when passed a phone number as input
     * @return
     */
    public boolean speakFromNumber(String phoneNumber){
        String name;
        name = ContactFinder.getContactName(phoneNumber);
        this.speak(name);
        return true;
    }
}
