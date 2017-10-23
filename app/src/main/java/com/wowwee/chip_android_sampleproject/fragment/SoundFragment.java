package com.wowwee.chip_android_sampleproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wowwee.bluetoothrobotcontrollib.chip.ChipCommandValues;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobot;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobotFinder;
import com.wowwee.chip_android_sampleproject.R;
import com.wowwee.chip_android_sampleproject.utils.FragmentHelper;

/**
 * Created by davidchan on 22/3/2017.
 */

public class SoundFragment extends ChipBaseFragment {

    Handler handler;
    ListView listView;

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

        if (ChipRobotFinder.getInstance().getChipRobotConnectedList().size() > 0) {
            ChipRobot robot = (ChipRobot) ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);
            robot.setCallbackInterface(SoundFragment.this);
        }
        listView = (ListView)view.findViewById(R.id.menuTable);
        String[] listArr = {"Back", "Stop", "kChipSoundFile_BARK_X1_ANGRY_A34", "kChipSoundFile_BARK_X1_CURIOUS_PLAYFUL_HAPPY_A34", "kChipSoundFile_BARK_X1_NEUTRAL_A34", "kChipSoundFile_BARK_X1_SCARED_A34", "kChipSoundFile_BARK_X2_ANGRY_A34", "kChipSoundFile_BARK_X2_CURIOUS_PLAYFUL_HAPPY_A34", "kChipSoundFile_BARK_X2_NEUTRAL_A34", "kChipSoundFile_BARK_X2_SCARED_A34", "kChipSoundFile_CRY_A34", "kChipSoundFile_GROWL_A_A34", "kChipSoundFile_GROWL_B_A34", "kChipSoundFile_GROWL_C_A34", "kChipSoundFile_HUH_LONG_A34", "kChipSoundFile_HUH_SHORT_A34", "kChipSoundFile_LICK_1_A34", "kChipSoundFile_LICK_2_A34", "kChipSoundFile_PANT_FAST_A34", "kChipSoundFile_PANT_MEDIUM_A34", "kChipSoundFile_PANT_SLOW_A34", "kChipSoundFile_SNIFF_1_A34", "kChipSoundFile_SNIFF_2_A34", "kChipSoundFile_YAWN_A_A34", "kChipSoundFile_YAWN_B_A34", "kChipSoundFile_ONE_A34", "kChipSoundFile_TWO_A34", "kChipSoundFile_THREE_A34", "kChipSoundFile_FOUR_A34", "kChipSoundFile_FIVE_A34", "kChipSoundFile_SIX_A34", "kChipSoundFile_SEVEN_A34", "kChipSoundFile_EIGHT_A34", "kChipSoundFile_NIGHT_A34", "kChipSoundFile_TEN_A34", "kChipSoundFile_ZERO_A34", "kChipSoundFile_CHIP_DOG_COUGH_2_A34", "kChipSoundFile_CHIP_DOG_CRY_2_A34", "kChipSoundFile_CHIP_DOG_CRY_3_A34", "kChipSoundFile_CHIP_DOG_CRY_4_A34", "kChipSoundFile_CHIP_DOG_CRY_5_A34", "kChipSoundFile_CHIP_DOG_EMO_CURIOUS_1_A34", "kChipSoundFile_CHIP_DOG_EMO_CURIOUS_2_A34", "kChipSoundFile_CHIP_DOG_EMO_CURIOUS_3_A34", "kChipSoundFile_CHIP_DOG_EMO_EXCITED_1_A34", "kChipSoundFile_CHIP_DOG_EMO_EXCITED_2_A34", "kChipSoundFile_CHIP_DOG_EMO_EXCITED_3_A34", "kChipSoundFile_CHIP_DOG_EMO_LAZY_1_A34", "kChipSoundFile_CHIP_DOG_EMO_LAZY_2_A34", "kChipSoundFile_CHIP_DOG_EMO_LAZY_3_A34", "kChipSoundFile_CHIP_DOG_EMO_RESPONSE_1_A34", "kChipSoundFile_CHIP_DOG_EMO_RESPONSE_2_A34", "kChipSoundFile_CHIP_DOG_EMO_RESPONSE_3_A34", "kChipSoundFile_CHIP_DOG_EMO_SCARED_YIP_2_A34", "kChipSoundFile_CHIP_DOG_EMO_SCARED_YIP_3_A34", "kChipSoundFile_CHIP_DOG_FART_1_A34", "kChipSoundFile_CHIP_DOG_FART_2_A34", "kChipSoundFile_CHIP_DOG_FART_3_A34", "kChipSoundFile_CHIP_DOG_GROWL_1_A34", "kChipSoundFile_CHIP_DOG_GROWL_2_A34", "kChipSoundFile_CHIP_DOG_GROWL_4_A34", "kChipSoundFile_CHIP_DOG_GROWL_5_A34", "kChipSoundFile_CHIP_DOG_HICCUP_1_A34", "kChipSoundFile_CHIP_DOG_HICCUP_2_A34", "kChipSoundFile_CHIP_DOG_HOWL_1_A34", "kChipSoundFile_CHIP_DOG_HOWL_2_A34", "kChipSoundFile_CHIP_DOG_HOWL_3_A34", "kChipSoundFile_CHIP_DOG_HOWL_4_A34", "kChipSoundFile_CHIP_DOG_HOWL_5_A34", "kChipSoundFile_CHIP_DOG_LICK_2_A34", "kChipSoundFile_CHIP_DOG_LICK_3_A34", "kChipSoundFile_CHIP_DOG_LOWBATTERY_1_A34", "kChipSoundFile_CHIP_DOG_LOWBATTERY_2_A34", "kChipSoundFile_CHIP_DOG_MUFFLE_1_A34", "kChipSoundFile_CHIP_DOG_MUFFLE_2_A34", "kChipSoundFile_CHIP_DOG_MUFFLE_3_A34", "kChipSoundFile_CHIP_DOG_PANT_1_A34", "kChipSoundFile_CHIP_DOG_PANT_2_A34", "kChipSoundFile_CHIP_DOG_PANT_3_A34", "kChipSoundFile_CHIP_DOG_PANT_4_A34", "kChipSoundFile_CHIP_DOG_PANT_5_L_A34", "kChipSoundFile_CHIP_DOG_SMOOCH_1_A34", "kChipSoundFile_CHIP_DOG_SMOOCH_2_A34", "kChipSoundFile_CHIP_DOG_SMOOCH_3_A34", "kChipSoundFile_CHIP_DOG_SNEEZE_1_A34", "kChipSoundFile_CHIP_DOG_SNEEZE_2_A34", "kChipSoundFile_CHIP_DOG_SNEEZE_3_A34", "kChipSoundFile_CHIP_DOG_SNIFF_1_A34", "kChipSoundFile_CHIP_DOG_SNIFF_2_A34", "kChipSoundFile_CHIP_DOG_SNIFF_4_A34", "kChipSoundFile_CHIP_DOG_SNORE_1_A34", "kChipSoundFile_CHIP_DOG_SNORE_2_A34", "kChipSoundFile_CHIP_DOG_SPECIAL_1_A34", "kChipSoundFile_CHIP_SING_DO1_SHORT_A34", "kChipSoundFile_CHIP_SING_DO2_SHORT_A34", "kChipSoundFile_CHIP_SING_FA_SHORT_A34", "kChipSoundFile_CHIP_SING_LA_SHORT_A34", "kChipSoundFile_CHIP_SING_MI_SHORT_A34", "kChipSoundFile_CHIP_SING_RE_SHORT_A34", "kChipSoundFile_CHIP_SING_SO_SHORT_A34", "kChipSoundFile_CHIP_SING_TI_SHORT_A34", "kChipSoundFile_CHIP_DOG_BARK_3_A34", "kChipSoundFile_CHIP_DOG_BARK_4_A34", "kChipSoundFile_CHIP_DOG_BARK_5_A34", "kChipSoundFile_CHIP_DOG_BARK_MULTI_3_A34 ", "kChipSoundFile_CHIP_DOG_BARK_MULTI_4_A34", "kChipSoundFile_CHIP_DOG_BARK_MULTI_5_A34", "kChipSoundFile_CHIP_DOG_BURP_1_A34", "kChipSoundFile_CHIP_DOG_BURP_2_A34", "kChipSoundFile_CHIP_DOG_COUGH_1_A34", "kChipSoundFile_CHIO_DOG_EMO_RESPONSE_3A", "kChipSoundFile_CHIP_DEMO_MUSIC_2", "kChipSoundFile_CHIP_DEMO_MUSIC_3", "kChipSoundFile_CHIP_DOG_BARK_1", "kChipSoundFile_CHIP_DOG_BARK_2", "kChipSoundFile_CHIP_DOG_BARK_MULTI_1", "kChipSoundFile_CHIP_DOG_BARK_MULTI_2", "kChipSoundFile_CHIP_DOG_CRY_1", "kChipSoundFile_CHIP_DOG_EMO_CURIOUS_2A", "kChipSoundFile_CHIP_DOG_EMO_EXCITED_3A", "kChipSoundFile_CHIP_DOG_EMO_LAZY_1A", "kChipSoundFile_CHIP_DOG_EMO_LAZY_2A", "kChipSoundFile_CHIP_DOG_EMO_LAZY_3A", "kChipSoundFile_CHIP_DOG_GROWL_3", "kChipSoundFile_CHIP_DOG_HOWL_1A", "kChipSoundFile_CHIP_DOG_HOWL_3A", "kChipSoundFile_CHIP_DOG_HOWL_4A", "kChipSoundFile_CHIP_DOG_HOWL_5A", "kChipSoundFile_CHIP_DOG_LICK_1", "kChipSoundFile_CHIP_DOG_LOWBATTERY_1A", "kChipSoundFile_CHIP_DOG_LOWBATTERY_2A", "kChipSoundFile_CHIP_DOG_MUFFLE_1A", "kChipSoundFile_CHIP_DOG_SMOOCH_3A", "kChipSoundFile_CHIP_DOG_SNEEZE_1A", "kChipSoundFile_CHIP_DOG_SNIFF_3", "kChipSoundFile_CHIP_DOG_SNIFF_4A", "kChipSoundFile_CHIP_MUSIC_DEMO_1", "kChipSoundFile_CHIO_DOG_EMO_RESPONSE_1A", "kChipSoundFile_CHIO_DOG_EMO_RESPONSE_2A", "kChipSoundFile_SHORT_MUTE_FOR_STOP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listArr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (ChipRobotFinder.getInstance().getChipRobotConnectedList().size() > 0) {
                    ChipRobot robot = (ChipRobot)ChipRobotFinder.getInstance().getChipRobotConnectedList().get(0);
                    switch (position) {
                        case 0:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new PlayingFragment(), R.id.view_id_content, false);
                            break;
                        case 1:
                            robot.chipStopSound();
                            break;
                        default:
                            ChipCommandValues.kChipSoundFileValue value = ChipCommandValues.kChipSoundFileValue.kChipSoundFile_None;
                            value.setValue(position-1);
                            robot.chipPlaySound(value);
                            break;
                    }
                }
            }
        });

        handler = new Handler();

        return view;
    }

}
