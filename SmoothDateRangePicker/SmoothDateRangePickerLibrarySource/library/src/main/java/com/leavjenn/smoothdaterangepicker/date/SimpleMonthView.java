/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leavjenn.smoothdaterangepicker.date;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Arrays;

public class SimpleMonthView extends MonthView {

    public SimpleMonthView(Context context, AttributeSet attr, SmoothDateRangePickerController controller) {
        super(context, attr, controller);
    }

    @Override
    public void drawMonthDay(Canvas canvas, int year, int month, int day,
            int x, int y, int startX, int stopX, int startY, int stopY) {

        if(isHighlighted(year, month, day)) {

            int newStartX =  startX - 1;
            int newStartY = startY + 1;
            int newStopX = stopX + 1;
            int newStopY = stopY - 1;
            int xMid = newStartX + ((newStopX - newStartX) / 2);
            int yMid = newStartY + ((newStopY - newStartY) / 2);

            if(null != mController.getHighlightedDays()
                    && mController.getHighlightedDays().length == 1) {
                //only single highlighted value show circle
                canvas.drawCircle(xMid , yMid, DAY_SELECTED_CIRCLE_SIZE - 1, mSelectedCirclePaint);
            } else {
                if(isFirstInHighlighted(year, month, day)) {
                    canvas.drawCircle(xMid , yMid, DAY_SELECTED_CIRCLE_SIZE - 1, mSelectedCirclePaint);
                    canvas.drawRect(x, newStartY, newStopX, newStopY, mSelectedCirclePaint);
                } else if(isLastInHighlighted(year, month, day)) {
                    canvas.drawCircle(xMid , yMid, DAY_SELECTED_CIRCLE_SIZE - 1, mSelectedCirclePaint);
                    canvas.drawRect(newStartX, newStartY, x, newStopY, mSelectedCirclePaint);
                } else {
                    canvas.drawRect(newStartX, newStartY, newStopX, newStopY, mSelectedCirclePaint);
                }
            }
            mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            mMonthNumPaint.setColor(mSelectedDayTextColor);
        } else {
            mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        }

        // If we have a mindate or maxdate, gray out the day number if it's outside the range.
        if (isOutOfRange(year, month, day)) {
            mMonthNumPaint.setColor(mDisabledDayTextColor);
        } else {
            mMonthNumPaint.setColor(isHighlighted(year, month, day) ? mSelectedDayTextColor : mDayTextColor);
        }
        canvas.drawText(String.format("%d", day), x, y, mMonthNumPaint);
    }
}
