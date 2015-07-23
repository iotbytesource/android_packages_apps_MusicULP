/*
 * Copyright (C) 2007 The Android Open Source Project
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

package com.android.music;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetSAControl extends Activity implements RadioGroup.OnCheckedChangeListener
{
    public interface Effect {
        public final static int NONE = 0;
        public final static int THREED = 1;
        public final static int REV = 2;
        public final static int MC = 3;
        public final static int BE = 4;
        public final static int EXT = 5;
        public final static int MTMV = 6;
    }
    private Button mSaveButton;
    private static int mTempSActrl = 0;
    private static int mRadioName = 0;
    RadioGroup mGroup;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sa_ctrl);
        mGroup = (RadioGroup)findViewById(R.id.sa_ctrl);
        mGroup.setOnCheckedChangeListener(this);

        switch (mRadioName) {
        case Effect.NONE:
            mGroup.check(R.id.radio_none);
            break;
        case Effect.THREED:
            mGroup.check(R.id.radio_3d);
            break;
        case Effect.REV:
            mGroup.check(R.id.radio_rev);
            break;
        case Effect.MC:
            mGroup.check(R.id.radio_mc);
            break;
        case Effect.BE:
            mGroup.check(R.id.radio_be);
            break;
        case Effect.EXT:
            mGroup.check(R.id.radio_ext);
            break;
        case Effect.MTMV:
            mGroup.check(R.id.radio_mtmv);
            break;
        }
        mSaveButton = (Button) findViewById(R.id.button_set);
        mSaveButton.setOnClickListener(mOpenClicked);
    }

    public void onCheckedChanged(RadioGroup group, int checkedid)
    {
        if (group == mGroup) {
            if (checkedid == R.id.radio_none)
                mTempSActrl = Effect.NONE;
            else if (checkedid == R.id.radio_3d)
                mTempSActrl = Effect.THREED;
            else if (checkedid == R.id.radio_rev)
                mTempSActrl = Effect.REV;
            else if (checkedid == R.id.radio_mc)
                mTempSActrl = Effect.MC;
            else if (checkedid == R.id.radio_be)
                mTempSActrl = Effect.BE;
            else if (checkedid == R.id.radio_ext)
                mTempSActrl = Effect.EXT;
            else if (checkedid == R.id.radio_mtmv)
                mTempSActrl = Effect.MTMV;
        }
        mRadioName = mTempSActrl;
    }

    private View.OnClickListener mOpenClicked = new View.OnClickListener() {
        public void onClick(View v)
        {
            SetSACtrlJNI.set(mTempSActrl);
            Intent intent = new Intent();
            intent.putExtra("com.android.music.sa_ctrl", mRadioName);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
