package com.wowwee.chip_android_sampleproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobot;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobotFinder;
import com.wowwee.chip_android_sampleproject.R;
import com.wowwee.chip_android_sampleproject.utils.FragmentHelper;

import java.util.Calendar;

/**
 * Created by davidchan on 22/3/2017.
 */

public class AlarmFragment extends ChipBaseFragment {

    Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null)
            return null;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        handler = new Handler();

        final ChipRobot robot = ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);
        robot.setCallbackInterface(this);

        ListView listView = (ListView)view.findViewById(R.id.menuTable);
        String[] ledNameArr = {"Back", "Set Current Clock", "Get Current Alarm", "Set Alarm", "Cancel Alarm"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ledNameArr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (ChipRobotFinder.getInstance().getChipRobotConnectedList().size() > 0) {



                    switch (position) {
                        case 0:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new MenuFragment(), R.id.view_id_content, false);
                            break;
                        case 1:
                            setCurrentClock();
                            break;
                        case 2:
                            robot.chipGetAlarm();
                            break;
                        case 3:
                            setAlarm();
                            break;
                        case 4:
                            cancelAlarm();
                            break;
                    }
                }
            }
        });

        return view;
    }

    void setCurrentClock() {
        ChipRobot robot = ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);

        Calendar rightNow = Calendar.getInstance();
        final int year = rightNow.get(Calendar.YEAR);
        final int month = rightNow.get(Calendar.MONTH);
        final int day = rightNow.get(Calendar.DATE);
        final int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        final int minute = rightNow.get(Calendar.MINUTE);
        final int seconds = rightNow.get(Calendar.SECOND);
        int dayOfWeek_t = rightNow.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek_t) {
            case Calendar.SUNDAY:
                dayOfWeek_t = 0;
                break;
            case Calendar.MONDAY:
                dayOfWeek_t = 1;
                break;
            case Calendar.TUESDAY:
                dayOfWeek_t = 2;
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek_t = 3;
                break;
            case Calendar.THURSDAY:
                dayOfWeek_t = 4;
                break;
            case Calendar.FRIDAY:
                dayOfWeek_t = 5;
                break;
            case Calendar.SATURDAY:
                break;
        }
        final int dayOfWeek = dayOfWeek_t;
        robot.chipSetCurrentClock(year, (byte) (month + 1), (byte) day, (byte) hour, (byte) minute, (byte) seconds, (byte) dayOfWeek);
    }

    void setAlarm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView hourtext = new TextView(getActivity());
        hourtext.setText("Hour");
        TextView mintext = new TextView(getActivity());
        mintext.setText("Minute");
        final EditText hourinput = new EditText(getActivity());
        hourinput.setInputType(InputType.TYPE_CLASS_NUMBER);
        final EditText minuteinput = new EditText(getActivity());
        minuteinput.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setTitle("Set Alarm");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChipRobot robot = ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);

                Calendar rightNow = Calendar.getInstance();
                final int year = rightNow.get(Calendar.YEAR);
                final int month = rightNow.get(Calendar.MONTH);
                final int day = rightNow.get(Calendar.DATE);
                final int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                final int minute = rightNow.get(Calendar.MINUTE);
                final int seconds = rightNow.get(Calendar.SECOND);
                int dayOfWeek_t = rightNow.get(Calendar.DAY_OF_WEEK);
                switch (dayOfWeek_t) {
                    case Calendar.SUNDAY:
                        dayOfWeek_t = 0;
                        break;
                    case Calendar.MONDAY:
                        dayOfWeek_t = 1;
                        break;
                    case Calendar.TUESDAY:
                        dayOfWeek_t = 2;
                        break;
                    case Calendar.WEDNESDAY:
                        dayOfWeek_t = 3;
                        break;
                    case Calendar.THURSDAY:
                        dayOfWeek_t = 4;
                        break;
                    case Calendar.FRIDAY:
                        dayOfWeek_t = 5;
                        break;
                    case Calendar.SATURDAY:
                        break;
                }
                final int dayOfWeek = dayOfWeek_t;

                int setHour = Integer.valueOf(hourinput.getText().toString());
                int setMin = Integer.valueOf(minuteinput.getText().toString());
                boolean isNextDay = false;
                if ((setHour < hour) || (setHour == hour && setMin < minute)){
                    isNextDay = true;
                }
                long setTime = Calendar.getInstance().getTimeInMillis() + (isNextDay ? (86400*1000):0);
                Calendar setCalendar = Calendar.getInstance();
                setCalendar.setTimeInMillis(setTime);
                int syear = setCalendar.get(Calendar.YEAR);
                int smonth = setCalendar.get(Calendar.MONTH);
                int sday = setCalendar.get(Calendar.DATE);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                robot.chipSetCurrentClock(year, (byte) (month + 1), (byte) day, (byte) hour, (byte) minute, (byte) seconds, (byte) dayOfWeek);
                robot.chipSetAlarm(syear, (byte) (smonth + 1), (byte) sday, (byte) setHour, (byte) setMin);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(hourtext);
        layout.addView(hourinput);
        layout.addView(mintext);
        layout.addView(minuteinput);

        dialog.setView(layout);
        dialog.show();
    }

    void cancelAlarm() {
        ChipRobot robot = ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);
        robot.chipCancelAlarm();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Cancel Alarm");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void chipDidReceiveCurrentAlarm(int year, int month, int day, final int hour, final int minute) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Current Alarm");
                builder.setMessage(hour+":"+minute);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
}
