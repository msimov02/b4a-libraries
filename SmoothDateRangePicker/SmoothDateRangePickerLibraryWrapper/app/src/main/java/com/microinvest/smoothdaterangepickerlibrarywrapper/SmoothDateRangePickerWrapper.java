package com.microinvest.smoothdaterangepickerlibrarywrapper;

import androidx.fragment.app.FragmentActivity;

import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;

import java.util.Calendar;
import java.util.Locale;

import anywheresoftware.b4a.BA;

@BA.Version(0.01f)
@BA.DependsOn(values={"SmoothDateRangePickerLibrary.aar"})
@BA.ShortName("SmoothDateRangePicker")
@BA.Events(values = {"DateRangeSet (startYear As String, startMonth As String, startDay As String, endYear As String, endMonth As String, endDay As String)"})
@BA.ActivityObject
public class SmoothDateRangePickerWrapper {

    private BA ba;
    private String eventName;

    public void Initialize(final BA ba, final String eventName) {
        this.ba = ba;
        this.eventName = eventName.toLowerCase(BA.cul);
    }

    public void showDialog(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay, int color, String language) {

        Locale.setDefault(new Locale(language));

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.YEAR, startYear);
        startCalendar.set(Calendar.MONTH, startMonth - 1);
        startCalendar.set(Calendar.DAY_OF_MONTH, startDay);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR, endYear);
        endCalendar.set(Calendar.MONTH, endMonth - 1);
        endCalendar.set(Calendar.DAY_OF_MONTH, endDay);

        SmoothDateRangePickerFragment smoothDateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                new SmoothDateRangePickerFragment.OnDateRangeSetListener() {
                    @Override
                    public void onDateRangeSet(SmoothDateRangePickerFragment smoothDateRangePickerFragment, int i, int i1, int i2, int i3, int i4, int i5) {
                        String startYear = String.valueOf(i);
                        String startMonth = FormatValue(i1 + 1);
                        String startDay = FormatValue(i2);

                        String endYear = String.valueOf(i3);
                        String endMonth = FormatValue(i4 + 1);
                        String endDay = FormatValue(i5);

                        ba.raiseEventFromUI(null, eventName + "_daterangeset", startYear, startMonth, startDay, endYear, endMonth, endDay);
                    }
                },
                startCalendar,
                endCalendar
        );
        smoothDateRangePickerFragment.setAccentColor(color);


        FragmentActivity fa = (FragmentActivity) ba.activity;

        smoothDateRangePickerFragment.show(fa.getSupportFragmentManager(), "SmoothDateRangePicker");
    }

    private String FormatValue(int value) {
        if(String.valueOf(value).length() == 1) {
            return  "0" + String.valueOf(value);
        } else {
            return String.valueOf(value);
        }
    }

}
