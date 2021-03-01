package net.microinvest.smartratewrapper;

import anywheresoftware.b4a.BA;
import guy4444.smartrate.SmartRate;

@BA.Version(0.01F)
@BA.ActivityObject
@BA.DependsOn(values = {"SmartRateLibrary.aar", "DesignSupport.aar"})
@BA.ShortName("SmartRate")
public class SmartRateWrapper {

    public void ShowDialog(final BA ba, String title, String contentText, String continueText, String googlePlayText, String clickHereText, String laterText, String stopText, String cancelText, String thanksForFeedbackText, int color, int openStoreFromXStars, int hoursBetweenCalls, int hoursDelayToActivate) {
        SmartRate.Rate(
                ba.activity
                , title
                , contentText
                , continueText
                , googlePlayText
                , clickHereText
                , laterText
                , stopText
                , cancelText
                , thanksForFeedbackText
                , color
                , openStoreFromXStars
                , hoursBetweenCalls
                , hoursDelayToActivate
        );
    }
}
