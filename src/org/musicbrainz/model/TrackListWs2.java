package org.musicbrainz.model;

import org.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>A List of Track Info (Track & catalog number)referred by a release</p>
 * 
 */
public class TrackListWs2 extends ListElement
{
    private Log log = LogFactory.getLog(TrackListWs2.class);

    private List<TrackWs2> tracks
               = new ArrayList<TrackWs2>();

        public TrackListWs2(List<TrackWs2> tracks)
    {
        if (tracks!=null)
        {       
            for (TrackWs2 element : tracks)
            {
                addTrack(element);
            }
        }
    }
    
    public List<TrackWs2>  getTracks(){
        return tracks;
    }
    
    private void addTrack(TrackWs2 element){

        tracks.add(element);

    }
    public Long getDurationInMillis(){
        
        long dur=0L;
        if  (getTracks()==null) return 0L;
        for (TrackWs2 t : getTracks())
        {
            if (t.getRecording()==null ||
                 t.getRecording().getDurationInMillis()==null)
            {continue;}
            
            dur = dur+t.getRecording().getDurationInMillis();
        }
        return dur;
    }
    public String getDuration(){

        if (this.getDurationInMillis()==null)
            return "";
        
        Calendar cal = Calendar.getInstance();

        long durms = this.getDurationInMillis();
        String dur;

        cal.setTimeInMillis(durms);

        if (durms>3600000) //1 h.
        {
             dur = String.format("%1$tH:%1$tM:%1$tS", cal);
        }
        else
        {
            dur = String.format("%1$tM:%1$tS", cal);
        }

        return dur;
    }
}