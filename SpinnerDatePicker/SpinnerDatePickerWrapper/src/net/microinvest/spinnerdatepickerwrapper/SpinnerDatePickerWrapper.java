package net.microinvest.spinnerdatepickerwrapper;

import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import anywheresoftware.b4a.BA;

@BA.ActivityObject
@BA.Version(0.01f)
@BA.ShortName("SpinnerDatePicker")
@BA.Events(values = {"DateSelected (Year As Int, Month As Int, Day As Int)", "DateSelectionCanceled"})
@BA.DependsOn(values={"SpinnerDatePickerLibrary.aar"})
public class SpinnerDatePickerWrapper {
    private BA ba;
    private String eventName;

    public void Initialize(final BA ba, String EventName)
    {
        this.ba = ba;
        eventName = EventName.toLowerCase(BA.cul);
    }

    public void ShowDatePickDialog(int year, int monthOfYear, int dayOfMonth, String title) {
        showDate(year, monthOfYear, dayOfMonth, title);
    }

    private void showDate(int year, int monthOfYear, int dayOfMonth, String title) {
        new SpinnerDatePickerDialogBuilder()
                .context(ba.context)
                .callback(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker datePicker, int i, int i1, int i2) {
                        ba.raiseEventFromUI(datePicker, eventName + "_dateselected", i, i1, i2);
                    }
                })
                .onCancel(new DatePickerDialog.OnDateCancelListener() {
                    @Override
                    public void onCancelled(com.tsongkha.spinnerdatepicker.DatePicker datePicker) {
                        ba.raiseEventFromUI(datePicker, eventName + "_dateselectioncanceled");
                    }
                })
                .customTitle(title)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }
}
